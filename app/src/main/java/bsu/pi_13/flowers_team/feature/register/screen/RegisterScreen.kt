import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import bsu.pi_13.flowers_team.feature.register.view_model.RegisterViewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import bsu.pi_13.flowers_team.data.db.DatabaseHelper

@Composable
fun RegisterScreen(navController: NavController, dbHelper: DatabaseHelper) {
    val context = LocalContext.current
    val viewModel = remember { RegisterViewModel(dbHelper, context) }
    Column(
        modifier = Modifier
            .fillMaxSize()

    )
    {
        RegisterContent(
            login = viewModel.login,
            password = viewModel.password,
            errorMessage = viewModel.errorMessage,
            isLoading = viewModel.isLoading,
            onLoginChange = { viewModel.login = it },
            onPasswordChange = { viewModel.password = it },
            onRegisterClick = {
                viewModel.registerUser {
                    // Navigate to the main screen on successful registration
                    navController.navigate("main_screen") {
                        popUpTo("register_screen") { inclusive = true }
                    }
                }
            },
            onLoginNavigate = {
                navController.navigate("login_screen") {
                    popUpTo("register_screen") { inclusive = false }
                }
            }
        )
    }
}
