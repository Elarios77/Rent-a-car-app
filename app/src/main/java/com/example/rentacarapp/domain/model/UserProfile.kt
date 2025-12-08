package com.example.rentacarapp.domain.model

data class UserProfile(
    val fName: String,
    val lName: String,
    val email: String,
    val phone: String,
    val address: String,
    val memberSince: String
)