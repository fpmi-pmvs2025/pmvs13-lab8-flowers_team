import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.feature.auth.content.LoginContent
import bsu.pi_13.flowers_team.feature.auth.view_model.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, dbHelper: DatabaseHelper) {
    val context = LocalContext.current
    val viewModel = remember { LoginViewModel(dbHelper, context) }
    val coroutineScope = rememberCoroutineScope()

    LoginContent(
        login = viewModel.login,
        password = viewModel.password,
        errorMessage = viewModel.errorMessage,
        isLoading = viewModel.isLoading,
        onLoginChange = { viewModel.login = it },
        onPasswordChange = { viewModel.password = it },
        onLoginClick = {
            coroutineScope.launch {
                val isSuccess = viewModel.loginUser()
                if (isSuccess) {
                    navController.navigate("main_screen") {
                        popUpTo("login_screen") { inclusive = true }
                    }
                }
            }
        },
        onRegisterClick = {
            navController.navigate("register_screen") {
                popUpTo("login_screen") { inclusive = false }
            }
        }
    )

}
