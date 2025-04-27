package bsu.pi_13.flowers_team.feature.register.view_model

import android.content.Context
import androidx.compose.runtime.*
import bsu.pi_13.flowers_team.data.DatabaseHelper

class RegisterViewModel(
    private val dbHelper: DatabaseHelper,
    private val context: Context
) {
    var login by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


    fun registerUser(onSuccess: () -> Unit) {
        if (login.isBlank() || password.isBlank()) {
            errorMessage = "Login and password cannot be empty"
            return
        }

        isLoading = true
        errorMessage = ""

        try {
            dbHelper.open()
            val isRegistered = dbHelper.registerUser(login, password)

            if (isRegistered) {
                onSuccess()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
            } else {
                errorMessage = "User with this login already exists"
            }
        } catch (e: Exception) {
            errorMessage = "Registration error"
            e.printStackTrace()
        } finally {
            dbHelper.close()
            isLoading = false
        }
    }

    fun checkIfUserIsLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}
