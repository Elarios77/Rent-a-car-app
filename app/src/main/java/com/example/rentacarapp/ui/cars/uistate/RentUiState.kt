package com.example.rentacarapp.ui.cars.uistate

import com.example.rentacarapp.domain.model.CarRentItem

data class RentUiState (
    val cars: List<CarRentItem> = emptyList(),
    val expandedCarId: String? = null,
    val selectedDays:Int = 1,
    val currentTotalCost:Double = 0.0,
    val dateSelectionStart:Long?=null,
    val dateSelectionEnd:Long?=null,
    val isDatePickerVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error:String?=null,
    val rentSuccess:Boolean = false
)
