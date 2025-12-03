package com.example.rentacarapp.ui.cars.history.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rentacarapp.R

@Composable
fun HistoryScreen() {
    Surface(modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.mainColor)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview(){
    HistoryScreen()
}