package com.example.rentacarapp.usecase.cars

import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.domain.repository.CarsRepository
import javax.inject.Inject

class GetRentalDetailsUseCase @Inject constructor(
    private val repository: CarsRepository
) {
    suspend operator fun invoke(id: String): CarRentItem? {
        return try {
            repository.getRentalById(id)
        } catch (e: Exception) {
            null
        }
    }
}