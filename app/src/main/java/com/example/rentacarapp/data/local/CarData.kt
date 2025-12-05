package com.example.rentacarapp.data.local

import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.CarRentItem

object CarData {

    val initialCars = listOf(
        CarRentItem(make = "Toyota",model = "Yaris", year = 2019,imageResourceId = R.drawable.yaris,price = 50.0),
        CarRentItem(make = "Mini",model = "Cooper",year = 2017,imageResourceId = R.drawable.mini, price = 57.0),
        CarRentItem(make = "Ford",model = "Fiesta",year = 2018,imageResourceId = R.drawable.fiesta,price = 72.0),
        CarRentItem(make = "BMW",model = "M3", year = 2020, imageResourceId = R.drawable.m3, price = 212.0),
        CarRentItem(make = "Audi", model = "A4", year = 2018, imageResourceId = R.drawable.a4, price = 189.0),
        CarRentItem(make = "Audi",model = "RS Q8",year = 2020,imageResourceId = R.drawable.rsq8,price = 320.0),
        CarRentItem(make = "Land Rover",model = "Range Rover",year=2021, imageResourceId = R.drawable.range_rover,price = 300.0),
        CarRentItem( make = "Lexus", model = "GX 460",year = 2020 ,imageResourceId = R.drawable.lexusgx460,price = 244.0),
        CarRentItem(make = "Jeep",model = "Wrangler",year = 2016, imageResourceId = R.drawable.wrangler, price = 232.0),
        CarRentItem(make = "Porsche",model = "911", year = 2021, imageResourceId = R.drawable.p_911, price = 335.0),
        CarRentItem(make ="Ferrari",model = "SF90", year = 2021, imageResourceId = R.drawable.sf90, price = 355.0)
    )
}