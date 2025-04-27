package bsu.pi_13.flowers_team.feature.auth.content

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

val lightGreen = Color(0xFFA5D6A7)
val blackTextColor = Color(0xFF000000)
val whiteBackgroundColor = Color(0xFFFFFFFF)


@Composable
fun LoginContent(
    login: String,
    password: String,
    errorMessage: String,
    isLoading: Boolean,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", fontSize = 32.sp, fontWeight = FontWeight.Bold)

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
            onClick = onLoginClick,
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = lightGreen) 
        ) {
            if (isLoading) {
                Text("Processing...")
            } else {
                Text("Login")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onRegisterClick) {
            Text("Don't have an account? Register")
        }
    }
}