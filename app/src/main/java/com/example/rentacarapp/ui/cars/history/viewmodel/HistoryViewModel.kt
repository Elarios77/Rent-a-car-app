package com.example.rentacarapp.ui.cars.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.usecase.cars.GetRentalHistoryUseCase
import com.example.rentacarapp.usecase.cars.RemoveRentalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val deleteUseCase: RemoveRentalUseCase,
    private val getRentalHistoryUseCase: GetRentalHistoryUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState : StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init{
        observeRentals()
    }

    private fun observeRentals(){
        viewModelScope.launch {
            getRentalHistoryUseCase().catch {exception ->
                _uiState.update { it.copy(
                    isLoading = false,
                    errorMessage = exception.message
                ) }
            }.collect { list ->
                _uiState.update { it.copy(
                    rentals = list,
                    isLoading = false,
                    errorMessage = null
                ) }
            }
        }
    }

    fun onDeleteClick(car: CarRentItem){
        viewModelScope.launch {
            try{
                deleteUseCase(car)
            }catch(e: Exception){
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

}