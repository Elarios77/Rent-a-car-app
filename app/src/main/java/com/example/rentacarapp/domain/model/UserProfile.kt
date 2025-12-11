package com.example.rentacarapp.domain.model

data class UserProfile(
    val fName: String = "Fnamidis",
    val lName: String = "Unamidis",
    val email: String = "user@example.com",
    val phone: String = "694******",
    val address: String = "Str.8, Athens",
    val memberSince: String = "March 2023"
)