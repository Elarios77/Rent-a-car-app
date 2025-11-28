package com.example.rentacarapp.usecase.login

import com.example.rentacarapp.domain.repository.AuthRepository
import javax.inject.Inject

class CustomerLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email:String,password: String): Result<Unit>{
        if(email.isBlank() || password.isBlank()){
            return Result.failure(Exception("Fields cannot be empty"))
        }
        return repository.login(email,password)
    }
}