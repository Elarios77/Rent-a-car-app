package com.example.rentacarapp.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rentacarapp.data.entity.CarEntity
import com.example.rentacarapp.framework.dao.CarDao


@Database(
entities = [CarEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun carDao(): CarDao
}