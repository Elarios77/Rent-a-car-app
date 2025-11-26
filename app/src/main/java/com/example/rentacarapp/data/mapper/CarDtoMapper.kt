package com.example.rentacarapp.data.mapper

import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.framework.dto.CarDto
import javax.inject.Inject

class CarDtoMapper @Inject constructor() {

    operator fun invoke(dto: CarDto?,existingCar: CarRentItem): CarRentItem{
        if(dto==null)return existingCar
        return existingCar.copy(
            year = dto.year,
            fuelType = dto.fuelType,
            transmission = dto.transmission
        )
    }
}