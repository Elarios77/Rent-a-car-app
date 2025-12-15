package com.example.rentacarapp.usecase.login

import com.example.rentacarapp.framework.datasource.local.UserPreferences
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userPreferences: UserPreferences
) {
    suspend operator fun invoke() {
        userPreferences.clear()
    }
}