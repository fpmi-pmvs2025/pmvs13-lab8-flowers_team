package bsu.pi_13.flowers_team.navigation

import LoginScreen
import RegisterScreen
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsu.pi_13.flowers_team.data.DatabaseHelper


import bsu.pi_13.flowers_team.screens.MainScreen
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {

            navController.navigate(Screen.Main.route) {

                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screen.Main.route else Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                dbHelper = DatabaseHelper(LocalContext.current)
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                navController = navController,
                dbHelper = DatabaseHelper(LocalContext.current),

            )
        }

        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}