package com.example.rentacarapp.domain.repository

import com.example.rentacarapp.domain.model.CarRentItem
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    suspend fun fetchCarSpecs(existingCar: CarRentItem): CarRentItem

    fun getAllRentals(): Flow<List<CarRentItem>>
    suspend fun saveRental(car: CarRentItem, days: Int)
    suspend fun deleteRental(car: CarRentItem)
    suspend fun getRentalById(id: String): CarRentItem?

}