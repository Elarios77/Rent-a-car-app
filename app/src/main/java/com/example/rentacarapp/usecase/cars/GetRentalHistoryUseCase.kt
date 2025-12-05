package com.example.rentacarapp.usecase.cars

import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.domain.repository.CarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRentalHistoryUseCase @Inject constructor(
    private val repository: CarsRepository
) {
    operator fun invoke(): Flow<List<CarRentItem>> {
        return repository.getAllRentals()
    }
}