package com.example.rentacarapp.framework

import com.example.rentacarapp.framework.CarDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface CarApiService {

    @GET("v1/cars")
    suspend fun getCarDetails(
        @Query("make") make:String,
        @Query("model") model:String,
        @Header("X-Api-Key") apiKey: String
    ): Response<List<CarDto>>
}