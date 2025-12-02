package com.example.rentacarapp.ui.cars.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.CarRentItem

@Composable
fun CarItemCard(
    car: CarRentItem,
    isExpanded: Boolean,
    selectedDays: Int,
    currentTotalCost: Double,
    onExpandClick: () -> Unit,
    onDateClick: () -> Unit,
    onRentClick: () -> Unit
) {

    val rotationState by animateFloatAsState(
        targetValue = if(isExpanded)180f else 0f,
        label = "ArrowRotation"
    )

    Card(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .animateContentSize(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onExpandClick
    )
    {
        Column(modifier = Modifier.padding(12.dp)){
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth())
            {
                Image(painter = painterResource(id = car.imageResourceId),
                    contentDescription = car.model,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                        .clip(RoundedCornerShape(12.dp)))
                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = car.make,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold)
                    Text(text = car.model,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                }
            }

            Column(horizontalAlignment = Alignment.End){
                Text(
                    text = "${car.price.toInt()}€",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.darker_mainColor)
                )
                Text(
                    text = "/day",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier= Modifier.size(24.dp)
                        .rotate(rotationState),
                    tint = Color.Gray
                )
            }
        }

        if(isExpanded){
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(12.dp))

            if(car.year == null){
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center){
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp),
                        color = colorResource(R.color.darker_mainColor),
                        strokeWidth = 3.dp
                    )
                }
            }else{
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    SpecItem(icon = Icons.Default.CalendarMonth,text = car.year.toString())
                    SpecItem(icon = Icons.Default.LocalGasStation, text = car.fuelType.toString())
                    SpecItem(icon = Icons.Default.SettingsSuggest,text = car.transmission.toString())
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = onDateClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$selectedDays Days Selected (Tap to change)",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(
                        text = stringResource(R.string.totalPrice),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${currentTotalCost.toInt()}€",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(R.color.darker_mainColor)
                    )
                }
                Button(
                    onClick = onRentClick,
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.mainColor)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.rentNow),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun SpecItem(icon: ImageVector,text:String){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Icon(imageVector = icon,contentDescription = null,
            tint = Color.Gray,modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, fontSize = 12.sp, fontWeight = FontWeight.SemiBold,color = Color.DarkGray)
    }
}

@Preview
@Composable
fun CarItemPreview(){
    MaterialTheme{
        CarItemCard(
            car = CarRentItem(
                id= "1",make = "Audi",model="A3",
                imageResourceId = R.drawable.rsq8,
                price = 50.0,
                year = 2022,
                fuelType = "Petrol",
                transmission = "Auto"
            ),
            isExpanded = true,
            selectedDays = 3,
            currentTotalCost = 150.0,
            onExpandClick = {},
            onDateClick = {},
            onRentClick = {}
        )
    }
}

