package com.example.rentacarapp.ui.login.screen


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rentacarapp.R
import com.example.rentacarapp.ui.login.viewmodel.LoginUiState
import com.example.rentacarapp.ui.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess:()->Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    LaunchedEffect(uiState.success) {
        if(uiState.success){
            Toast.makeText(context,"Successful connection", Toast.LENGTH_SHORT).show()
            onLoginSuccess()
            viewModel.resetLoginState()
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.car_login_screen2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.blur(
                radiusX = 2.dp,
                radiusY = 2.dp
            )
        )
        Column(modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Box(modifier = Modifier.padding(8.dp)
                .alpha(0.7f)
                .clip(
                    CutCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight()
            ){
                Column(modifier = Modifier.padding(48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    LoginHeader()
                    Spacer(modifier = Modifier.height(20.dp))
                    LoginFields(
                        uiState = uiState,
                        onEmailChange = viewModel::onEmailChanged,
                        onPasswordChange = viewModel::onPasswordChanged,
                        onLoginClick = viewModel::onLoginClicked
                    )

                }
            }
        }
    }
}

@Composable
fun LoginHeader(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.welcome_back),
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.DarkGray)
        Text(text = stringResource(R.string.signIn),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray)
    }
}

@Composable
fun LoginFields(
    uiState: LoginUiState,
    onEmailChange:(String)-> Unit,
    onPasswordChange:(String)-> Unit,
    onLoginClick:()-> Unit
){
    Column{
        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = {Text(text = stringResource(R.string.email))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(Icons.Default.Email,contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = {Text(text = stringResource(R.string.password))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(Icons.Default.Email,contentDescription = null)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        if(uiState.isLoading){
            CircularProgressIndicator()
        }else{
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = stringResource(R.string.signIn_Footer))
            }
        }

    }
}



