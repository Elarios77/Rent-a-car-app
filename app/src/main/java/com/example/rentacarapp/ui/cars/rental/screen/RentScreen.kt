package com.example.rentacarapp.ui.cars.rental.screen


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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rentacarapp.R
import com.example.rentacarapp.ui.cars.rental.viewmodel.RentViewModel


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

    if(uiState.isPaymentSheetVisible){
        PaymentSheet(
            totalAmount = uiState.currentTotalCost,
            isProcessing = uiState.isPaymentProcessing,
            onDismiss = {viewModel.onDismissPayment()},
            onConfirm = {viewModel.onConfirmPayment()}
        )
    }

    DatePickerPopUp(
        isVisible = uiState.isDatePickerVisible,
        dateSelectionStart = uiState.dateSelectionStart,
        dateSelectionEnd = uiState.dateSelectionEnd,
        onConfirm = {start,end -> viewModel.onDaysSelected(start,end)},
        onDismiss = {viewModel.toggleDatePicker(false)}
    )

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
                    onExpandClick = { viewModel.onToggleCarExpand(car) },
                    onDateClick = { viewModel.toggleDatePicker(true) },
                    onRentClick = { viewModel.onRentClick(car) }
                )
            }
        }
        if(uiState.isLoading && uiState.expandedCarId == null){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerPopUp(
    isVisible: Boolean,
    dateSelectionStart:Long?,
    dateSelectionEnd:Long?,
    onConfirm:(Long?,Long?) -> Unit,
    onDismiss:()-> Unit

){
    if(isVisible){
        val datePickerState = rememberDateRangePickerState(
            initialSelectedStartDateMillis = dateSelectionStart,
            initialSelectedEndDateMillis = dateSelectionEnd
        )

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {onConfirm(
                        datePickerState.selectedStartDateMillis,
                        datePickerState.selectedEndDateMillis
                    )}
                ) { Text(stringResource(R.string.ok)) }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
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
}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview(){
    DatePickerPopUp(
        isVisible = true,
        dateSelectionStart = System.currentTimeMillis(),
        dateSelectionEnd = System.currentTimeMillis() + 86400000L,
        onConfirm = {_,_->},
        onDismiss = {}
    )
}
