package com.example.rentacarapp.data.mapper

import com.example.rentacarapp.data.entity.CarEntity
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
            date = entity.date,
            year = null,
            fuelType = null,
            transmission = null
        )
    }

    //Reverse Mapping
    operator fun invoke(item: CarRentItem,days: Int=0): CarEntity{
        return CarEntity(
            make = item.make,
            model = item.model,
            imageResourceId = item.imageResourceId,
            price =item.price*days,
            date = System.currentTimeMillis()
        )
    }
}