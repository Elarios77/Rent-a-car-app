package com.example.rentacarapp.data.datasource

import com.example.rentacarapp.framework.CarDto

interface RemoteDataSource {

    suspend fun getCarSpecs(make:String,model: String): CarDto?

}