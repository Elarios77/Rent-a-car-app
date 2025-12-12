package com.example.rentacarapp.ui.reusablecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentacarapp.R

@Composable
fun CustomFooter(
    color: Color
){
    Column(modifier = Modifier.fillMaxSize())
    {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(color = color)
                .fillMaxWidth()
                .padding(12.dp)
        )
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            )
            {
                Text(
                    text = stringResource(R.string.company_Name),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
                Text(
                    text = stringResource(R.string.est_),
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterEnd),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun footerPreview(){
    CustomFooter(color = colorResource(R.color.mainColor))
}