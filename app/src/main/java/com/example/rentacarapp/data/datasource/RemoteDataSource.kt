package com.example.rentacarapp.data.datasource

import com.example.rentacarapp.framework.dto.CarDto

interface RemoteDataSource {

    suspend fun getCarSpecs(make: String, model: String): CarDto?

}