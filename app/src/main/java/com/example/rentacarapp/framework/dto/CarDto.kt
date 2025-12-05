package com.example.rentacarapp.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarDto (
    @Json(name = "fuel_type") val fuelType:String?,
    @Json(name="transmission")val transmission: String?,
    @Json(name="displacement") val displacement:Double?
)