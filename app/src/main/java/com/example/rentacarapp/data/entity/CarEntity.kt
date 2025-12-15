package com.example.rentacarapp.data.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

//Room entity
@Entity(tableName = "cars_rental")
data class CarEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val make: String,
    val model: String,
    @DrawableRes val imageResourceId: Int,
    val price: Double,
    val date: Long,
    val days: Int
)