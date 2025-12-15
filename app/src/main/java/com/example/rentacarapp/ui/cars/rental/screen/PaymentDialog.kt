package com.example.rentacarapp.ui.cars.rental.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rentacarapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentDialog(
    totalAmount: Double,
    isProcessing: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        )
        {
            PaymentSheetContent(
                totalAmount = totalAmount,
                isProcessing = isProcessing,
                onConfirm = onConfirm
            )
        }

    }

}

@Composable
fun PaymentSheetContent(
    totalAmount: Double,
    isProcessing: Boolean,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(
            painter = painterResource(R.drawable.credit_card),
            contentDescription = null,
            modifier = Modifier.size(85.dp)
        )
        Text(
            text = stringResource(R.string.checkout),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.total),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = totalAmount.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = colorResource(R.color.mainColor)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = stringResource(R.string.card_info),
            textStyle = TextStyle(fontSize = 20.sp),
            onValueChange = {},
            label = {
                Text(
                    text = stringResource(R.string.cardNumber),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.CreditCard,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            readOnly = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row()
        {
            OutlinedTextField(
                value = stringResource(R.string.cvv_code),
                textStyle = TextStyle(fontSize = 20.sp),
                onValueChange = {},
                label = {
                    Text(
                        text = stringResource(R.string.cvv),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                },
                modifier = Modifier.weight(1f),
                readOnly = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = stringResource(R.string.expiryDate),
                textStyle = TextStyle(fontSize = 20.sp),
                onValueChange = {},
                label = {
                    Text(
                        text = stringResource(R.string.expiry),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                },
                modifier = Modifier.weight(1f),
                readOnly = true
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            CreditCardIcons()
        }
        Spacer(modifier = Modifier.height(12.dp))
        if (isProcessing) {
            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = colorResource(R.color.darker_mainColor),
                strokeWidth = 3.dp
            )
        } else {
            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = colorResource(R.color.darker_mainColor)
                )
            )
            {
                Text(
                    text = stringResource(R.string.pay_and_book),
                    fontSize = 20.sp
                )
            }
        }
        //Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun CreditCardIcons() {
    val cardIcons = listOf(
        R.drawable.visa,
        R.drawable.mastercard,
        R.drawable.american_express,
        R.drawable.skrill
    )

    cardIcons.forEach { iconRes ->
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(50.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PaymentContentPreview() {
    PaymentSheetContent(
        totalAmount = 150.0,
        isProcessing = false,
        onConfirm = {}
    )
}