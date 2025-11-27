package com.example.rentacarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rentacarapp.ui.login.screen.LoginScreen
import com.example.rentacarapp.ui.theme.RentACarAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentACarAppTheme {
                LoginScreen(
                    viewModel = TODO(),
                    onLoginSuccess = TODO()
                )
            }
        }
    }
}
