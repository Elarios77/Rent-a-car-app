package com.example.rentacarapp.di.modules

import com.example.rentacarapp.data.datasource.LocalDataSource
import com.example.rentacarapp.data.datasource.RemoteDataSource
import com.example.rentacarapp.framework.datasource.LocalDataSourceImpl
import com.example.rentacarapp.framework.datasource.RemoteDataSourceImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        impl: RemoteDataSourceImplementation
    ): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource

}