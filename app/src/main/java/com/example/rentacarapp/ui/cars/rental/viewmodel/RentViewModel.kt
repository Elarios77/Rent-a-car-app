package com.example.rentacarapp.ui.cars.rental.viewmodel

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
        _uiState.update {
            it.copy(
                cars = CarData.initialCars,
                groupedCars = CarData.initialCars.groupBy { it.category })
        }
    }

    private fun fetchSpecsForCar(car: CarRentItem) {
        if (car.fuelType != null && car.displacement != null) return
        viewModelScope.launch {
            val updatedCar = fetchCarSpecsUseCase(car)
            _uiState.update { state ->
                if (state.selectedCarForDetails?.id == updatedCar.id) {
                    state.copy(selectedCarForDetails = updatedCar)
                } else {
                    state
                }
            }
        }
    }

    fun toggleDatePicker(show: Boolean) {
        _uiState.update { it.copy(isDatePickerVisible = show) }
    }

    fun onDaysSelected(startMillis: Long?, endMillis: Long?) {
        if (startMillis == null) return
        val finalEnd = endMillis ?: startMillis
        val difference = finalEnd - startMillis
        val days = TimeUnit.MILLISECONDS.toDays(difference).toInt() + 1

        val currentCar = _uiState.value.selectedCarForDetails ?: return

        val pricePerDay = currentCar.price

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

    fun onRentClick() {
        if (_uiState.value.selectedCarForDetails == null) return
        _uiState.update {
            it.copy(
                isPaymentSheetVisible = true
            )
        }
    }

    fun resetMessages() {
        _uiState.update { it.copy(error = null, rentSuccess = false) }
    }

    fun onConfirmPayment() {
        val days = _uiState.value.selectedDays
        val currentCar = _uiState.value.selectedCarForDetails ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isPaymentProcessing = true) }
            delay((2000))

            try {
                rentCarUseCase(currentCar, days)
                _uiState.update {
                    it.copy(
                        isPaymentProcessing = false,
                        isPaymentSheetVisible = false,
                        rentSuccess = true,
                        selectedCarForDetails = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isPaymentProcessing = false, error = e.message
                    )
                }
            }
        }
    }

    fun onDismissPayment() {
        _uiState.update { it.copy(isPaymentSheetVisible = false) }
    }

    fun onToggleCategory(category: CarCategory) {
        _uiState.update { currentState ->
            val currentSet = currentState.expandedCategories

            val newSet = if (currentSet.contains(category)) {
                currentSet - category
            } else {
                currentSet + category
            }
            currentState.copy(expandedCategories = newSet)
        }
    }

    fun selectCarForDetails(car: CarRentItem) {
        _uiState.update {
            it.copy(
                selectedCarForDetails = car,
                selectedDays = 1,
                currentTotalCost = car.price,
                dateSelectionStart = null,
                dateSelectionEnd = null

            )
        }
        fetchSpecsForCar(car)
    }

    fun closeCarDetails() {
        _uiState.update {
            it.copy(
                selectedCarForDetails = null,
                currentTotalCost = 0.0,
                dateSelectionStart = null,
                dateSelectionEnd = null
            )
        }
    }
}