package com.example.rentacarapp.framework.datasource

import com.example.rentacarapp.data.datasource.LocalDataSource
import com.example.rentacarapp.data.entity.CarEntity
import com.example.rentacarapp.framework.dao.CarDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: CarDao
): LocalDataSource {
    override fun getAllRentals(): Flow<List<CarEntity>> {
        return dao.getAllRentals()
    }

    override suspend fun insertRental(car: CarEntity) {

        dao.insertRental(car)
    }

    override suspend fun deleteRental(car: CarEntity) {
        dao.deleteRental(car)
    }

    override suspend fun getRentalById(id: String): CarEntity? {
        return dao.getRentalById(id)
    }
}