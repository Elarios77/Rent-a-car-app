package com.example.rentacarapp.data.carsrepository

import com.example.rentacarapp.data.datasource.LocalDataSource
import com.example.rentacarapp.data.datasource.RemoteDataSource
import com.example.rentacarapp.data.mapper.CarDtoMapper
import com.example.rentacarapp.data.mapper.CarEntityMapper
import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.domain.repository.CarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dtoMapper: CarDtoMapper,
    private val entityMapper: CarEntityMapper
): CarsRepository {
    override suspend fun fetchCarSpecs(existingCar: CarRentItem): CarRentItem {
        val dto = remoteDataSource.getCarSpecs(make = existingCar.make, model = existingCar.model)
        return dtoMapper(dto,existingCar)
    }

    override fun getAllRentals(): Flow<List<CarRentItem>> {
        return localDataSource.getAllRentals().map { entities ->
            entities.mapNotNull { entity ->
                entityMapper(entity)
            }
        }
    }

    override suspend fun saveRental(
        car: CarRentItem,
        days: Int
    ) {
        val entity = entityMapper(car,days)
        entity?.let{
            localDataSource.insertRental(entity)
        }
    }

    override suspend fun deleteRental(car: CarRentItem) {
        val entity = entityMapper(car)
        entity?.let {
            localDataSource.deleteRental(entity)
        }
    }

    override suspend fun getRentalById(id: String): CarRentItem? {
        val entity = localDataSource.getRentalById(id)
        return entityMapper(entity)
    }
}