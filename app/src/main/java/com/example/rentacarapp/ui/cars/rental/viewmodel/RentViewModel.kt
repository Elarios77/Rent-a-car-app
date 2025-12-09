package com.example.rentacarapp.ui.cars.rental.viewmodel

import android.app.ProgressDialog.show
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentacarapp.data.local.CarData
import com.example.rentacarapp.domain.model.CarCategory
import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.usecase.cars.FetchCarSpecsUseCase
import com.example.rentacarapp.usecase.cars.RentCarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(
    private val fetchCarSpecsUseCase: FetchCarSpecsUseCase,
    private val rentCarUseCase: RentCarUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RentUiState())
    val uiState: StateFlow<RentUiState> = _uiState.asStateFlow()

    init {
        loadCars()
    }

    private fun loadCars() {
        val allCars = CarData.initialCars
        val grouped = allCars.groupBy { it.category }
        _uiState.update {
            it.copy(cars = allCars,
                groupedCars = grouped)
        }
    }

    fun onToggleCarExpand(car: CarRentItem) {
        val isExpanded = _uiState.value.expandedCarId == car.id
        if (isExpanded) {
            _uiState.update {
                it.copy(
                    expandedCarId = null, selectedDays = 1, currentTotalCost = 0.0,
                    dateSelectionStart = null, dateSelectionEnd = null
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    expandedCarId = car.id, selectedDays = 1,
                    currentTotalCost = car.price, dateSelectionStart = null, dateSelectionEnd = null
                )
            }
            fetchSpecsForCar(car)
        }
    }

    private fun fetchSpecsForCar(car: CarRentItem) {
        if (car.fuelType != null && car.displacement!=null) return
        viewModelScope.launch {
            val updatedCar = fetchCarSpecsUseCase(car)
            _uiState.update { state ->
                val newCarList = state.cars.map {
                    if (it.id == car.id) updatedCar else it
                }
                state.copy(cars = newCarList)
            }
        }
    }

    fun toggleDatePicker(show:Boolean){
        _uiState.update { it.copy(isDatePickerVisible = show) }
    }

    fun onDaysSelected(startMillis:Long? , endMillis:Long?) {
        if (startMillis==null) return
        val finalEnd = endMillis ?:startMillis
        val difference = finalEnd - startMillis
        val days = TimeUnit.MILLISECONDS.toDays(difference).toInt()+1
        val currentExpandedId = _uiState.value.expandedCarId
        val currentCar = _uiState.value.cars.find { it.id == currentExpandedId }

        val pricePerDay = currentCar?.price?:0.0

        _uiState.update {
            it.copy(
                selectedDays = days,
                currentTotalCost = pricePerDay * days,
                dateSelectionStart = startMillis,
                dateSelectionEnd = finalEnd,
                isDatePickerVisible = false

            )
        }
    }

    fun onRentClick(car: CarRentItem) {
        _uiState.update { it.copy(
            isPaymentSheetVisible = true
        ) }
    }

    fun resetMessages() {
        _uiState.update { it.copy(error = null, rentSuccess = false) }
    }

    fun onConfirmPayment(){
        val days = _uiState.value.selectedDays
        val currentCarId = _uiState.value.expandedCarId ?: return
        val currentCar = _uiState.value.cars.find { it.id == currentCarId } ?:return
        viewModelScope.launch {
            _uiState.update { it.copy(isPaymentProcessing = true) }
            delay((2000))

            try{
                rentCarUseCase(currentCar,days)
                _uiState.update {
                    it.copy(
                        isPaymentProcessing = false,
                        isPaymentSheetVisible = false,
                        rentSuccess = true,
                        expandedCarId = null
                    )
                }
            }catch(e: Exception){
                _uiState.update { it.copy(
                    isPaymentProcessing = false, error = e.message
                ) }
            }
        }
    }

    fun onDismissPayment(){
        _uiState.update { it.copy(isPaymentSheetVisible = false) }
    }

    fun onToggleCategory(category: CarCategory){
        _uiState.update { currentState->
            val currentSet = currentState.expandedCategories

            val newSet = if(currentSet.contains(category)){
                currentSet - category
            }else{
                currentSet + category
            }
            currentState.copy(expandedCategories = newSet)
        }
    }
}