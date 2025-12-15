package com.example.rentacarapp.usecase.cars

import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.domain.repository.CarsRepository
import javax.inject.Inject

class RemoveRentalUseCase @Inject constructor(
    private val repository: CarsRepository
) {
    suspend operator fun invoke(car: CarRentItem) {
        repository.deleteRental(car)
    }
}