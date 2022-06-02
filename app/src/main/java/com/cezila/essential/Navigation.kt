package com.cezila.essential

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cezila.essential.presentation.home_screen.HomeScreen
import com.cezila.essential.presentation.splash_screen.AnimatedSplash

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            AnimatedSplash(navController)
        }
        composable(route = Screen.MainScreen.route) {
            HomeScreen()
        }
    }
}