package com.example.rentacarapp.usecase.cars

import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.domain.repository.CarsRepository
import javax.inject.Inject

class RentCarUseCase @Inject constructor(
    private val repository: CarsRepository
) {
    suspend operator fun invoke(car: CarRentItem, days: Int) {
        if (days > 0) {
            repository.saveRental(car, days)
        }
    }
}