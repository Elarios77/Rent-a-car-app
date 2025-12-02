package com.example.rentacarapp.usecase.cars

import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.domain.repository.CarsRepository
import javax.inject.Inject

class FetchCarSpecsUseCase @Inject constructor(
    private val repository: CarsRepository
) {
    suspend operator fun invoke(existingCar: CarRentItem): CarRentItem {
        return try {
            repository.fetchCarSpecs(existingCar)
        } catch (e: Exception) {
            e.printStackTrace()
            existingCar
        }
    }

}