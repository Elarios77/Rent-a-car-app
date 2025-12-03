package com.example.rentacarapp.domain.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class CarRentItem(
    val id: String = UUID.randomUUID().toString(),
    val make: String,
    val model: String,
    val displacement: String?=null,
    @DrawableRes val imageResourceId: Int,
    val price: Double,

    val year: Int? = null,
    val fuelType: String? = null,
    val transmission: String? = null
)