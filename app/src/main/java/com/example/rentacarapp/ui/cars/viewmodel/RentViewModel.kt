package com.example.rentacarapp.ui.cars.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.ui.cars.uistate.RentUiState
import com.example.rentacarapp.usecase.cars.FetchCarSpecsUseCase
import com.example.rentacarapp.usecase.cars.RentCarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(
    private val fetchCarSpecsUseCase: FetchCarSpecsUseCase,
    private val rentCarUseCase: RentCarUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(RentUiState())
    val uiState: StateFlow<RentUiState> = _uiState.asStateFlow()

    init{
        loadCars()
    }

    private fun loadCars(){
        val initialCars = listOf(
            CarRentItem(
                make = "Toyota",
                model = "Yaris",
                imageResourceId = R.drawable.yaris,
                price = 35.0
            ),
            CarRentItem(
                make = "Renault",
                model = "Clio",
                imageResourceId = R.drawable.clio,
                price = 40.0
            ),
            CarRentItem(
                make = "Opel",
                model = "Corsa",
                imageResourceId = R.drawable.corsa,
                price = 67.0
            ),
            CarRentItem(
                make = "Audi",
                model = "RS Q8",
                imageResourceId = R.drawable.rsq8,
                price = 250.0
            ),
            CarRentItem(
                make = "Lexus",
                model = "GX 460",
                imageResourceId = R.drawable.lexusgx460,
                price = 180.0
            )
        )
        _uiState.update { it.copy(cars = initialCars) }
    }

    fun onToggleCarExpand(car: CarRentItem){
        val isExpanded = _uiState.value.expandedCarId == car.id
        if(isExpanded){
            _uiState.update { it.copy(expandedCarId = null, selectedDays = 1, currentTotalCost = 0.0) }
        }else{
            _uiState.update { it.copy(expandedCarId = car.id, selectedDays = 1,
                currentTotalCost = car.price) }
            fetchSpecsForCar(car)
        }
    }

    private fun fetchSpecsForCar(car: CarRentItem){
        if(car.year!=null) return
        viewModelScope.launch {
            val updatedCar = fetchCarSpecsUseCase(car)
            _uiState.update { state ->
                val newCarList = state.cars.map {
                    if(it.id == car.id) updatedCar else it
                }
                state.copy(cars = newCarList)
            }
        }
    }

    fun onDaysChange(newDays:Int){
        if(newDays < 1)return
        val finalDays = if(newDays > 31) 1 else newDays
        val currentExpandedId = _uiState.value.expandedCarId
        val currentCar = _uiState.value.cars.find { it.id == currentExpandedId }

        if(currentCar != null){
            val newtotal = currentCar.price * finalDays

            _uiState.update {
                it.copy(
                    selectedDays = finalDays,
                    currentTotalCost = newtotal
                )
            }
        }
    }

    fun onRentClick(car: CarRentItem){
        val days = _uiState.value.selectedDays
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try{
                rentCarUseCase(car,days)
                _uiState.update { it.copy(isLoading = false, rentSuccess = true, expandedCarId = null) }
            }catch (e: Exception){
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun resetMessages(){
        _uiState.update { it.copy(error = null , rentSuccess = false) }
    }
}