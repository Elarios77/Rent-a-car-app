package com.example.rentacarapp.ui.cars.rental.viewmodel

import com.example.rentacarapp.domain.model.CarCategory
import com.example.rentacarapp.domain.model.CarRentItem

data class RentUiState (
    val cars: List<CarRentItem> = emptyList(),
    val selectedDays:Int = 1,
    val currentTotalCost:Double = 0.0,
    val dateSelectionStart:Long?=null,
    val dateSelectionEnd:Long?=null,
    val isDatePickerVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error:String?=null,
    val rentSuccess:Boolean = false,
    val isPaymentSheetVisible: Boolean = false,
    val isPaymentProcessing: Boolean = false,
    val groupedCars:Map<CarCategory?,List<CarRentItem>> = emptyMap(),
    val expandedCategories:Set<CarCategory> = emptySet(),
    val selectedCarForDetails: CarRentItem?=null
)