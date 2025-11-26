package com.example.rentacarapp.data.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "cars_rental")
data class CarEntity (
    @PrimaryKey val id :String = UUID.randomUUID().toString(),
    val make : String,
    val model : String,
    val year : Int,
    val fuelType: String,
    @DrawableRes val imageResourceId: Int,
    val price:Double,
    val days: Int,
    val date: Long,
    val transmission:String
)