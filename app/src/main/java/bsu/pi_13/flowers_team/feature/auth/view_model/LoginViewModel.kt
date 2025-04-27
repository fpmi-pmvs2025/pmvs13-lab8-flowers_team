package bsu.pi_13.flowers_team.feature.auth.view_model

import android.content.Context
import androidx.compose.runtime.*
import androidx.navigation.NavController
import bsu.pi_13.flowers_team.data.DatabaseHelper

class LoginViewModel(
    private val dbHelper: DatabaseHelper,
    private val context: Context,
) {
    var login by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun loginUser() {
        if (login.isBlank() || password.isBlank()) {
            errorMessage = "Please enter both login and password"
            return
        }

        isLoading = true
        errorMessage = ""

        try {
            dbHelper.open()
            val isAuthenticated = dbHelper.authenticateUser(login, password)
            if (isAuthenticated) {
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

            } else {
                errorMessage = "Invalid login or password"
            }
        } catch (e: Exception) {
            errorMessage = "Login error. Please try again."
            e.printStackTrace()
        } finally {
            dbHelper.close()
            isLoading = false
        }
    }


}
