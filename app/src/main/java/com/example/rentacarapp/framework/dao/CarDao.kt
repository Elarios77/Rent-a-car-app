package com.example.rentacarapp.framework.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rentacarapp.data.entity.CarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRental(rental: CarEntity)

    @Query("SELECT * FROM cars_rental WHERE id=:rentId")
    suspend fun getRentalById(rentId: String): CarEntity?

    @Query("SELECT * FROM cars_rental ORDER BY date DESC")
    fun getAllRentals(): Flow<List<CarEntity>>

    @Delete
    suspend fun deleteRental(rental: CarEntity)

}