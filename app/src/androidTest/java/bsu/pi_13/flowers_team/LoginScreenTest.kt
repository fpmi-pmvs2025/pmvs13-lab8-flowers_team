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
}
