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
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if(!email.matches(emailRegex)){
            return Result.failure(Exception("Form is incorrect"))
        }
        return repository.login(email,password)
    }
}