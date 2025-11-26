package com.example.rentacarapp.di.modules

import com.example.rentacarapp.data.carsrepository.CarsRepositoryImpl
import com.example.rentacarapp.domain.repository.CarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCarRentalRepository(impl: CarsRepositoryImpl): CarsRepository = impl
}