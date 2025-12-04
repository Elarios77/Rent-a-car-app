package com.example.rentacarapp.ui.cars.history.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.ui.cars.history.viewmodel.HistoryUiState
import com.example.rentacarapp.ui.cars.history.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HistoryScreenContent(
        uiState = uiState,
        onDeleteClick = {car -> viewModel.onDeleteClick(car)}
    )

}

@Composable
fun HistoryScreenContent(
    uiState: HistoryUiState,
    onDeleteClick : (CarRentItem) -> Unit
){
    Box(modifier = Modifier.fillMaxSize()
        .background(colorResource(R.color.mainColor)))
    {
        when{
            uiState.isLoading ->{
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }

            uiState.errorMessage!=null->{
                ErrorMessage(uiState)
            }

            uiState.rentals.isEmpty() ->{
                EmptyList()
            }

            else ->{
                FilledList(uiState,onDeleteClick)
            }

        }
    }
}

@Composable
fun EmptyList(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Icon(
            imageVector = Icons.Default.History,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.no_rental_message),
            fontSize = 24.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ErrorMessage(
    uiState: HistoryUiState
){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.error_msg),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = uiState.errorMessage.toString(),
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun FilledList(
    uiState: HistoryUiState,
    onDeleteClick: (CarRentItem) -> Unit
){
    LazyColumn(
        contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp)
    ) { items(uiState.rentals , key= {it.id }){car->
        HistoryItemCard(car = car,
            onDeleteClick = {onDeleteClick(car)})
    }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryPreview(){

    val mockRentals = listOf(
        CarRentItem(
            id = "1", make = "Toyota", model = "Yaris",
            imageResourceId = R.drawable.yaris,
            price = 105.0, date = System.currentTimeMillis()
        ),
        CarRentItem(
            id = "2", make = "Audi", model = "RS Q8",
            imageResourceId = R.drawable.rsq8,
            price = 450.0, date = System.currentTimeMillis() - 86400000
        )
    )
    val mockState = HistoryUiState(
        rentals = mockRentals,
        isLoading = false,
        errorMessage = null
    )
    HistoryScreenContent(
        uiState =mockState,
        onDeleteClick = {}
    )
}