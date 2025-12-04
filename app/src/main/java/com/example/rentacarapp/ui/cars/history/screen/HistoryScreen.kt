package com.example.rentacarapp.ui.cars.history.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rentacarapp.R
import com.example.rentacarapp.ui.cars.history.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()



}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview(){
    HistoryScreen()
}