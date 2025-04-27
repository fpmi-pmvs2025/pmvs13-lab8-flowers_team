package bsu.pi_13.flowers_team.feature.register.view_model

import android.content.Context
import androidx.compose.runtime.*
import bsu.pi_13.flowers_team.data.db.DatabaseHelper

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
            errorMessage = "Логин и пароль не могут быть пустыми"
            return
        }

        isLoading = true
        errorMessage = ""

        try {
            dbHelper.open()
            val isRegistered = dbHelper.userRepository.registerUser(login, password)

            if (isRegistered) {
                onSuccess()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
            } else {
                errorMessage = "Пользователь с таким логином уже существует"
            }
        } catch (e: Exception) {
            errorMessage = "Ошибка регистрации"
            e.printStackTrace()
        } finally {
            dbHelper.close()
            isLoading = false
        }
    }
}
