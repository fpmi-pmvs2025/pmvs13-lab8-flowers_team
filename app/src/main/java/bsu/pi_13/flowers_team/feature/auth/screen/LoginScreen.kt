import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import bsu.pi_13.flowers_team.data.DatabaseHelper
import bsu.pi_13.flowers_team.feature.auth.content.LoginContent
import bsu.pi_13.flowers_team.feature.auth.view_model.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, dbHelper: DatabaseHelper) {
    val context = LocalContext.current
    val viewModel = remember { LoginViewModel(dbHelper, context) }
    val pinkBackground = Color(0xFFF8BBD0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pinkBackground) 
    ) {
        LoginContent(
            login = viewModel.login,
            password = viewModel.password,
            errorMessage = viewModel.errorMessage,
            isLoading = viewModel.isLoading,
            onLoginChange = { viewModel.login = it },
            onPasswordChange = { viewModel.password = it },
            onLoginClick = {
                navController.navigate("main_screen") {
                    popUpTo("login_screen") { inclusive = true }
                }
            },
            onRegisterClick = {
                navController.navigate("register_screen") {
                    popUpTo("login_screen") { inclusive = false }
                }
            }
        )
    }
}
