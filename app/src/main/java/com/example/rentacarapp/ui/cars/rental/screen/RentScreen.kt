package com.example.rentacarapp.ui.cars.rental.screen


import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.CarCategory
import com.example.rentacarapp.domain.model.CarRentItem
import com.example.rentacarapp.ui.cars.rental.viewmodel.RentViewModel

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
            items(uiState.groupedCars.entries.toList()){entry->
                val category = entry.key
                val carsInCategory = entry.value
                CategorySelection(
                    category = category!!,
                    cars = carsInCategory,
                    isExpanded = uiState.expandedCategories.contains(category),
                    onToggle = {viewModel.onToggleCategory(category)},
                    onCarClick = {car ->
                        viewModel.selectCarForDetails(car)
                    }
                )
            }
        }
        if(uiState.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    if(uiState.selectedCarForDetails!=null){
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onDismissRequest = {viewModel.closeCarDetails()},
            content = {
                val selectedCar = uiState.selectedCarForDetails!!
                CarItemCard(
                    car = selectedCar,
                    isExpanded = true,
                    selectedDays = uiState.selectedDays,
                    currentTotalCost = uiState.currentTotalCost,
                    onExpandClick = {},
                    onDateClick = {viewModel.toggleDatePicker(true)},
                    onRentClick = {viewModel.onRentClick()}
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        )
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

@Composable
fun CategorySelection(
    category: CarCategory,
    cars:List<CarRentItem>,
    isExpanded:Boolean,
    onToggle:()-> Unit,
    onCarClick:(CarRentItem)-> Unit
){
    val rotationState by animateFloatAsState(
        targetValue = if(isExpanded)180F else 0f
    )
        Column(modifier = Modifier.fillMaxWidth()
            .padding(vertical = 4.dp))
        {
            Row(modifier = Modifier.fillMaxWidth()
                .clickable{onToggle()}
                .padding(16.dp))
            {
                Text(text = category.displayName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotationState),
                    tint = Color.Gray
                )
            }
            AnimatedVisibility(visible = isExpanded)
            {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(cars){car->
                        CarItemSmallCard(car = car,
                            onClick = {onCarClick(car)})
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 2.dp)
        }
}
