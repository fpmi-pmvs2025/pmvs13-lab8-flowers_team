package bsu.pi_13.flowers_team

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import bsu.pi_13.flowers_team.feature.auth.content.LoginContent
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginButtonClick_callsCallback() {
        var loginClicked = false
        composeTestRule.setContent {
            LoginContent(
                login = "user",
                password = "pass",
                errorMessage = "",
                isLoading = false,
                onLoginChange = {},
                onPasswordChange = {},
                onLoginClick = { loginClicked = true },
                onRegisterClick = {}
            )
        }

        composeTestRule.onNodeWithText("Войти").performClick()
        assert(loginClicked)
    }
    @Test
    fun registerButtonClick_callsCallback() {
        var registerClicked = false
        composeTestRule.setContent {
            LoginContent(
                login = "",
                password = "",
                errorMessage = "",
                isLoading = false,
                onLoginChange = {},
                onPasswordChange = {},
                onLoginClick = {},
                onRegisterClick = { registerClicked = true }
            )
        }

        composeTestRule.onNodeWithText("Нет аккаунта? Зарегистрируйтесь").performClick()
        assert(registerClicked)
    }

    @Test
    fun loginFieldInput_updatesText() {
        var loginText = ""
        composeTestRule.setContent {
            LoginContent(
                login = loginText,
                password = "",
                errorMessage = "",
                isLoading = false,
                onLoginChange = { loginText = it },
                onPasswordChange = {},
                onLoginClick = {},
                onRegisterClick = {}
            )
        }

        val loginField = composeTestRule.onNodeWithText("Логин")
        loginField.performTextInput("testuser")
        assert(loginText == "testuser")
    }
    @Test
    fun passwordFieldInput_updatesText() {
        var passwordText = ""
        composeTestRule.setContent {
            LoginContent(
                login = "",
                password = passwordText,
                errorMessage = "",
                isLoading = false,
                onLoginChange = {},
                onPasswordChange = { passwordText = it },
                onLoginClick = {},
                onRegisterClick = {}
            )
        }

        val passwordField = composeTestRule.onNodeWithText("Пароль")
        passwordField.performTextInput("mypassword")
        assert(passwordText == "mypassword")
    }
    @Test
    fun errorMessage_displayed_whenNotEmpty() {
        val error = "Неверный логин или пароль"
        composeTestRule.setContent {
            LoginContent(
                login = "",
                password = "",
                errorMessage = error,
                isLoading = false,
                onLoginChange = {},
                onPasswordChange = {},
                onLoginClick = {},
                onRegisterClick = {}
            )
        }

        composeTestRule.onNodeWithText(error).assertIsDisplayed()
    }
}
