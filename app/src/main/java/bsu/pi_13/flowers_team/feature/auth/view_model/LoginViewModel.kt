package bsu.pi_13.flowers_team.feature.auth.view_model

import android.content.Context
import androidx.compose.runtime.*
import bsu.pi_13.flowers_team.data.db.DatabaseHelper

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

    suspend fun loginUser(): Boolean {
        if (login.isBlank() || password.isBlank()) {
            errorMessage = "Пожалуйста, введите логин и пароль"
            return false
        }

        isLoading = true
        errorMessage = ""

        return try {
            dbHelper.open()
            val isAuthenticated = dbHelper.userRepository.authenticateUser(login, password)
            if (isAuthenticated) {
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
                true
            } else {
                errorMessage = "Неверный логин или пароль"
                false
            }
        } catch (e: Exception) {
            errorMessage = "Ошибка входа. Пожалуйста, попробуйте снова."
            false
        } finally {
            dbHelper.close()
            isLoading = false
        }
    }

}
