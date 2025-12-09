package com.example.rentacarapp.ui.userprofile.screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.UserProfile

@Composable
fun ProfileScreen(
    navController: NavController,
    user: UserProfile = UserProfile(
        fName = "Fnamidis",
        lName = "Unamidis",
        email = "user@example.com",
        phone = "694******",
        address = "Str.8, Athens",
        memberSince = "March 2023"
    )
) {
    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.LightGray))
    {
        Box(modifier = Modifier.fillMaxWidth()
            .height(250.dp))
        {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(color = colorResource(R.color.mainColor),
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                )
            Row(modifier = Modifier.padding(16.dp)
                .align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically)
            {
                IconButton(
                    onClick = {navController.popBackStack()}
                ) {
                    Icon(Icons.Default.ArrowBackIosNew,
                        contentDescription = null,
                        tint = Color.Black)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.personalInfo),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            
            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = CircleShape,
                    shadowElevation = 8.dp,
                    border = BorderStroke(4.dp,Color.White),
                    modifier = Modifier.size(130.dp)
                )
                {
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.background(Color.LightGray))
                    {
                        Icon(
                            painter = painterResource(R.drawable.man),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${user.fName} ${user.lName}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)){
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp))
                {
                    ProfileInfoRow(icon = Icons.Default.Email,
                        label = "Email", value = user.email)
                    HorizontalDivider(thickness = 2.dp,color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    ProfileInfoRow(icon = Icons.Default.Phone,
                        label = "Phone", value = user.phone)
                    HorizontalDivider(thickness = 2.dp,
                        color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    ProfileInfoRow(icon = Icons.Default.LocationOn,
                        label = "Address", value = user.address)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(contentColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ProfileInfoRow(icon = Icons.Default.CalendarToday,
                        label = "Member Since", value = user.memberSince)
                }
            }
        }
        Column(modifier = Modifier.fillMaxSize())
        {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.background(color = colorResource(R.color.mainColor))
                .fillMaxWidth()
                .padding(12.dp))
            {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center))
                {
                    Text(text = stringResource(R.string.company_Name),
                        fontSize = 25.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White)
                    Text(text = stringResource(R.string.est_),
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White)
                }
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                        .align(Alignment.CenterEnd),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
fun ProfileInfoRow(icon: ImageVector, label:String, value: String){
    Row(verticalAlignment = Alignment.CenterVertically){
        Box(
            modifier = Modifier.size(40.dp)
                .background(colorResource(R.color.mainColor)),
            contentAlignment =Alignment.Center
        ){
            Icon(imageVector = icon,contentDescription = null,
                tint = Color.White)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column{
            Text(text = label,
                fontSize = 18.sp,
                color = Color.Gray)
            Text(text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    val mockUser = UserProfile(
        fName = "Fnamidis",
        lName = "Lnamidis",
        email = "naiexoemail@gmail",
        phone = "69********",
        address = "str.8 Athens",
        memberSince = "June 2024",
    )
    ProfileScreen(navController = rememberNavController(),
        user = mockUser)
}