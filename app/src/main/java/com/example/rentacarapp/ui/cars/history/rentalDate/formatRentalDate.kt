package com.example.rentacarapp.ui.cars.history.rentalDate

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatRentalDate(timestamp: Long?): String {
    if (timestamp == null) return ""
    val date = Date(timestamp)
    val formattedDate = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
    return formattedDate.format(date)
}