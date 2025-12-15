package com.example.rentacarapp.data.mapper

import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.framework.dto.CarDto
import javax.inject.Inject

class CarDtoMapper @Inject constructor() {

    operator fun invoke(dto: CarDto?, existingCar: CarRentItem): CarRentItem {
        if (dto == null) {
            return existingCar.copy(
                fuelType = "N/A",
                transmission = "N/A",
                displacement = "N/A"
            )
        }
        return existingCar.copy(
            fuelType = dto.fuelType?.replaceFirstChar { it.uppercase() } ?: "N/A",
            transmission = when (dto.transmission) {
                "a" -> "Automatic"
                "m" -> "Manual"
                else -> dto.transmission ?: "N/A"
            },
            displacement = dto.displacement?.let { "$it L" } ?: "N/A"
        )
    }
}