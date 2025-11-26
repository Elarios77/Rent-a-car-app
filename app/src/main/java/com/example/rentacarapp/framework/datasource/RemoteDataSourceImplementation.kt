package com.example.rentacarapp.framework.datasource

import com.example.rentacarapp.data.datasource.RemoteDataSource
import com.example.rentacarapp.framework.dto.CarDto
import com.example.rentacarapp.framework.api.CarApiService
import javax.inject.Inject

class RemoteDataSourceImplementation @Inject constructor(
    private val carApi: CarApiService,
    private val apiKey:String
) : RemoteDataSource{

    override suspend fun getCarSpecs(make: String, model: String): CarDto? {
        return try{
            val response = carApi.getCarDetails(make = make,model=model, apiKey = apiKey)
            if(response.isSuccessful){
                response.body()?.firstOrNull()
            }else{
                null
            }
        }catch(e:Exception){
            e.printStackTrace()
            null
        }
    }
}