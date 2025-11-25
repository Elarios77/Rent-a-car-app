package com.example.rentacarapp.data.mapper

import com.example.rentacarapp.data.CarEntity
import com.example.rentacarapp.domain.model.CarRentItem
import javax.inject.Inject

class CarEntityMapper @Inject constructor() {

    operator fun invoke(entity: CarEntity?): CarRentItem?{
        if(entity==null)return null
        return CarRentItem(
            id = entity.id,
            make = entity.make,
            model = entity.model,
            imageResourceId = entity.imageResourceId,
            price = entity.price,
            year = entity.year,
            fuelType = entity.fuelType,
            transmission = entity.transmission
        )
    }

    //Reverse Mapping
    operator fun invoke(item: CarRentItem?,days: Int): CarEntity?{
        if(item==null)return null
        return CarEntity(
            id = item.id,
            make = item.make,
            model = item.model,
            imageResourceId = item.imageResourceId,
            price =item.price*days,
            year = item.year ?: 2024,
            fuelType = item.fuelType ?: "N/A",
            transmission = item.transmission?:"Automatic",
            days = days,
            date = System.currentTimeMillis()
        )
    }
}