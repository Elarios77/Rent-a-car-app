package com.example.rentacarapp.ui.cars.screen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rentacarapp.R
import com.example.rentacarapp.ui.cars.viewmodel.RentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentScreen(
    viewModel: RentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.rentSuccess,uiState.error) {
        if(uiState.rentSuccess){
            Toast.makeText(context,"Car rented! Check History tab.", Toast.LENGTH_SHORT).show()
            viewModel.resetMessages()
        }
        if(uiState.error!=null){
            Toast.makeText(context,"Error: ${uiState.error}", Toast.LENGTH_LONG).show()
            viewModel.resetMessages()
        }
    }

    if(uiState.isDatePickerVisible){
        val datePickerState = rememberDateRangePickerState(
            initialSelectedStartDateMillis = uiState.dateSelectionStart,
            initialSelectedEndDateMillis = uiState.dateSelectionEnd
        )

        DatePickerDialog(
            onDismissRequest = { viewModel.toggleDatePicker(false)},
            confirmButton = {
                TextButton(
                    onClick = {viewModel.onDaysSelected(
                        datePickerState.selectedStartDateMillis,
                        datePickerState.selectedEndDateMillis
                    )
                    }
                ) { Text(stringResource(R.string.ok)) }
            },
            dismissButton = {
                TextButton(onClick = {viewModel.toggleDatePicker(false)}) {
                    Text(stringResource(R.string.cancel))
                }
            }
        ) {
            DateRangePicker(
                state = datePickerState,
                modifier = Modifier.height(500.dp),
                title = {
                    Text(
                        text = stringResource(R.string.selectDates),
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(colorResource(R.color.mainColor))
    ){
        LazyColumn(contentPadding = PaddingValues(bottom = 80.dp,top = 16.dp))
        {
            items(uiState.cars,key = {it.id}) {car ->
                val isExpanded = uiState.expandedCarId == car.id
                val displayPrice = if (isExpanded) uiState.currentTotalCost else car.price
                CarItemCard(
                    car = car,
                    isExpanded = isExpanded,
                    selectedDays = uiState.selectedDays,
                    currentTotalCost = displayPrice,
                    onExpandClick = {viewModel.onToggleCarExpand(car)},
                    onDateClick = {viewModel.toggleDatePicker(true)},
                    onRentClick = {viewModel.onRentClick(car)}
                )
            }
        }
        if(uiState.isLoading && uiState.expandedCarId == null){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

