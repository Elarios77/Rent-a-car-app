package com.example.rentacarapp.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarDto (
    @Json(name = "make") val make:String,
    @Json(name = "model") val model:String,
    @Json(name = "fuel_type") val fuelType:String,
    @Json(name = "year") val year:Int,
    @Json(name="transmission")val transmission: String
)