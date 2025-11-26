package com.example.rentacarapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.rentacarapp.framework.AppDatabase
import com.example.rentacarapp.framework.dao.CarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            klass = AppDatabase::class.java,
            name = "car_rental_database"
        ).build()
    }

    @Provides
    fun provideCarDao(database: AppDatabase): CarDao{
        return database.carDao()
    }
}