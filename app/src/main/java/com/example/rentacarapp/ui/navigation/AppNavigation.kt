package com.example.rentacarapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.UserProfile
import com.example.rentacarapp.ui.info.InfoScreen
import com.example.rentacarapp.ui.tabs.TabsDashboard
import com.example.rentacarapp.ui.login.screen.LoginScreen
import com.example.rentacarapp.ui.userprofile.screen.ProfileScreen

enum class LoginNavigation(@StringRes val title: Int) {
    Login(title = R.string.loginScreen),
    Main(title = R.string.app_name),

    Profile(title = R.string.personalInfo),
    Info(title = R.string.info)
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginNavigation.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LoginNavigation.Login.name) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(LoginNavigation.Main.name) {
                        popUpTo(LoginNavigation.Login.name) { inclusive = true }
                    }
                }
                )
            }
            composable(route = LoginNavigation.Main.name) {
                TabsDashboard(
                    navController = navController
                )
            }
            composable(route = LoginNavigation.Profile.name) {
                ProfileScreen(
                    navController = navController,
                    user = UserProfile()
                )
            }
            composable(route = LoginNavigation.Info.name){
                InfoScreen(
                    navController = navController
                )
            }

        }
    }
}