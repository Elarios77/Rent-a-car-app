package com.example.rentacarapp.data.authenticationRepository

import com.example.rentacarapp.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        delay(1000L)
        val validEmail = "user@example.com"
        val validPass = "user1234"

        return if(email == validEmail && password == validPass){
            Result.success(Unit)
        }else{
            Result.failure(Exception("Wrong email or password"))
        }
    }
}