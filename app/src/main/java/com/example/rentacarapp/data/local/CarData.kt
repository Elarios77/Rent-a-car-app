package com.example.rentacarapp.data.local

import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.CarCategory
import com.example.rentacarapp.domain.model.CarRentItem

object CarData {

    val initialCars = listOf(
        CarRentItem(make = "Toyota",model = "Yaris", year = 2019,imageResourceId = R.drawable.yaris,price = 43.0, category = CarCategory.SMALL),
        CarRentItem(make = "Mini",model = "Cooper",year = 2017,imageResourceId = R.drawable.mini, price = 57.0, category = CarCategory.SMALL),
        CarRentItem(make = "Ford",model = "Fiesta",year = 2018,imageResourceId = R.drawable.fiesta,price = 72.0, category = CarCategory.SMALL),
        CarRentItem(make = "Volkswagen", model = "Golf",year =2018, imageResourceId = R.drawable.golf, price = 66.0, category = CarCategory.SMALL),
        CarRentItem(make = "Toyota", model = "Corolla",year =2016, imageResourceId = R.drawable.corolla, price =55.0 , category = CarCategory.SMALL),
        CarRentItem(make = "Honda",model = "Fit",year = 2018, imageResourceId = R.drawable.fit, price = 50.0, category = CarCategory.SMALL),
        CarRentItem(make = "Jeep",model = "Wrangler",year = 2016, imageResourceId = R.drawable.wrangler, price = 232.0, category = CarCategory.SUV),
        CarRentItem(make = "Audi",model = "RS Q8",year = 2020,imageResourceId = R.drawable.rsq8,price = 320.0, category = CarCategory.SUV),
        CarRentItem(make = "Toyota",model = "Rav4", year = 2016, imageResourceId = R.drawable.rav4, price = 199.0, category = CarCategory.SUV),
        CarRentItem(make = "Tesla",model = "model Y", year = 2020, imageResourceId = R.drawable.modely, price = 158.0, category = CarCategory.SUV),
        CarRentItem(make = "Land Rover",model = "Range Rover",year=2021, imageResourceId = R.drawable.range_rover,price = 300.0, category = CarCategory.SUV),
        CarRentItem(make = "Ford",model = "Explorer", year = 2019, imageResourceId = R.drawable.explorer, price = 223.0, category = CarCategory.SUV),
        CarRentItem(make = "BMW",model = "M3", year = 2020, imageResourceId = R.drawable.m3, price = 212.0, category = CarCategory.SPORT),
        CarRentItem(make = "Porsche",model = "911", year = 2021, imageResourceId = R.drawable.p_911, price = 335.0, category = CarCategory.SPORT),
        CarRentItem(make ="Ferrari",model = "SF90", year = 2021, imageResourceId = R.drawable.sf90, price = 355.0, category = CarCategory.SPORT),
        CarRentItem(make = "Chevrolet",model = "Camaro", year = 2021, imageResourceId = R.drawable.camaro, price = 200.0, category = CarCategory.SPORT),
        CarRentItem(make = "Dodge",model = "Challenger", year = 2014, imageResourceId = R.drawable.challenger, price = 225.0, category = CarCategory.SPORT),
        CarRentItem(make = "Audi",model = "R8", year = 2024, imageResourceId = R.drawable.r8, price = 215.0, category = CarCategory.SPORT),
        CarRentItem(make = "Audi", model = "A4", year = 2018, imageResourceId = R.drawable.a4, price = 189.0, category = CarCategory.LUXURY),
        CarRentItem( make = "Lexus", model = "GX 460",year = 2020 ,imageResourceId = R.drawable.lexusgx460,price = 244.0, category = CarCategory.LUXURY),
        CarRentItem( make = "BMW", model = "7 series",year = 2016 ,imageResourceId = R.drawable._7series,price = 188.0, category = CarCategory.LUXURY),
        CarRentItem( make = "Porsche", model = "Panamera",year = 2022 ,imageResourceId = R.drawable.panamera,price = 212.0, category = CarCategory.LUXURY),
        CarRentItem( make = "Genesis", model = "G80",year = 2020 ,imageResourceId = R.drawable.g80,price = 199.0, category = CarCategory.LUXURY),
        CarRentItem( make = "Tesla", model = "model S",year = 2020 ,imageResourceId = R.drawable.models,price = 188.0, category = CarCategory.LUXURY)
    )
}