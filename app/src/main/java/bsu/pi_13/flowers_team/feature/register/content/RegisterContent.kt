import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

val lightGreen = Color(0xFFA5D6A7)
val blackTextColor = Color(0xFF000000)
val whiteBackgroundColor = Color(0xFFFFFFFF)

val pinkBackground = Color(0xFFF8BBD0)

@Composable
fun RegisterContent(
    login: String,
    password: String,
    errorMessage: String,
    isLoading: Boolean,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginNavigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Register",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = blackTextColor,

        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = login,
            onValueChange = onLoginChange,
            label = { Text("Login", color = blackTextColor) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = blackTextColor,
                unfocusedTextColor = blackTextColor,
                focusedContainerColor = whiteBackgroundColor,
                unfocusedContainerColor = whiteBackgroundColor,
                errorContainerColor = Color.Red,
                cursorColor = blackTextColor,
                focusedLabelColor = blackTextColor,
                unfocusedLabelColor = blackTextColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password", color = blackTextColor) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = blackTextColor,
                unfocusedTextColor = blackTextColor,
                focusedContainerColor = whiteBackgroundColor,
                unfocusedContainerColor = whiteBackgroundColor,
                errorContainerColor = Color.Red,
                cursorColor = blackTextColor,
                focusedLabelColor = blackTextColor,
                unfocusedLabelColor = blackTextColor
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text(errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRegisterClick,
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = lightGreen)
        ) {
            if (isLoading) {
                Text("Processing...")
            } else {
                Text("Register")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onLoginNavigate) {
            Text("Already have an account? Login",)
        }
    }
}
