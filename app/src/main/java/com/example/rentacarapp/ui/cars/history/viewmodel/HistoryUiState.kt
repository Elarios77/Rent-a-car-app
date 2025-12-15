package com.example.rentacarapp.ui.cars.history.viewmodel

import com.example.rentacarapp.domain.model.CarRentItem

data class HistoryUiState(
    val rentals: List<CarRentItem> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)