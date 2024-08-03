package com.memorymaster.namefaceapp.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.memorymaster.namefaceapp.android.login.LoginScreen
import com.memorymaster.namefaceapp.android.login.register.RegisterScreen

@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") { LoginScreen(nav = navController) }
        composable("register") { RegisterScreen(nav = navController) }
        // Voeg hier meer schermen toe
    }
}
