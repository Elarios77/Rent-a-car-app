package com.example.rentacarapp.data.datasource

import com.example.rentacarapp.data.entity.CarEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllRentals(): Flow<List<CarEntity>>
    suspend fun insertRental(car: CarEntity)
    suspend fun deleteRental(car: CarEntity)
    suspend fun getRentalById(id: String): CarEntity?

}