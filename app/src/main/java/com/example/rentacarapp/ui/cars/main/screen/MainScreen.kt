package com.example.rentacarapp.ui.cars.main.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCarFilled
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentacarapp.R

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.grayformainScreen))
            .verticalScroll(rememberScrollState())
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xff084a6e), Color(0xff0e72a6)),

                        )
                ),
            contentAlignment = Alignment.Center
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.DirectionsCarFilled,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.company_Name),
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(R.string.subslogan),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .offset(y = (-30).dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            InfoCard(title = stringResource(R.string.About_header),
                content = stringResource(R.string.About_body))
            Spacer(modifier = Modifier.padding(20.dp))
            InfoCard(title = stringResource(R.string.vision_header),
                content = stringResource(R.string.vision_body))
            Spacer(modifier = Modifier.padding(20.dp))
            Text(text = stringResource(R.string.why_to_choose),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(R.color.darker_mainColor),
                modifier = Modifier.padding(bottom = 12.dp,start = 4.dp))

            FeatureRow(icon = Icons.Default.VerifiedUser,title = stringResource(R.string.features1_title),body = stringResource(R.string.features1_body))
            FeatureRow(icon = Icons.Default.Schedule,title = stringResource(R.string.features2_title),body = stringResource(R.string.features2_body))
            FeatureRow(icon = Icons.Default.SupportAgent,title = stringResource(R.string.features3_title), body = stringResource(R.string.features3_body))

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.darker_mainColor)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = stringResource(R.string.contact),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    ContactRow(icon = Icons.Default.Phone,text = stringResource(R.string.phone))
                    ContactRow(icon = Icons.Default.Mail, text = stringResource(R.string.email_flexdrive))
                    ContactRow(icon = Icons.Default.LocationOn, text = stringResource(R.string.location))
                }
            }
        }
    }
}

@Composable
fun InfoCard(title:String,content: String){
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =colorResource(R.color.mainColor),)
            HorizontalDivider(thickness = 2.dp,
                color = Color.LightGray)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = content,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.mainColor),
                textAlign = TextAlign.Justify)
        }
    }
}

@Composable
fun FeatureRow(icon : ImageVector,title: String,body: String){
    Row(modifier = Modifier.fillMaxWidth()
        .padding(vertical = 8.dp)
        .background(Color.White,RoundedCornerShape(12.dp))
        .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorResource(R.color.mainColor),
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column{
            Text(text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.darker_mainColor))
            Text(text = body,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colorResource(R.color.darker_mainColor))
        }
    }
}

@Composable
fun ContactRow(icon: ImageVector,text: String){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)){
        Icon(imageVector = icon,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text ,
            color = Color.White,
            fontSize = 14.sp)
    }

}

@Preview
@Composable
fun PreviewMain() {
    MainScreen()
}