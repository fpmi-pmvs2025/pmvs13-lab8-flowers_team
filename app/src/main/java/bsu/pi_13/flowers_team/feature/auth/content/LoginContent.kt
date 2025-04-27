package bsu.pi_13.flowers_team.feature.auth.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bsu.pi_13.flowers_team.ui.theme.DarkGreen
import bsu.pi_13.flowers_team.ui.theme.DarkPink
import bsu.pi_13.flowers_team.ui.theme.DarkTextColor
import bsu.pi_13.flowers_team.ui.theme.ErrorRed
import bsu.pi_13.flowers_team.ui.theme.LightTextColor
import bsu.pi_13.flowers_team.ui.theme.MilkBackground
import bsu.pi_13.flowers_team.ui.theme.SoftPink

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
            .background(MilkBackground)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Добро пожаловать",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = DarkPink,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Войдите в свой аккаунт",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = LightTextColor
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Поле логина
        OutlinedTextField(
            value = login,
            onValueChange = onLoginChange,
            label = {
                Text(
                    "Логин",
                    color = DarkTextColor,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = DarkTextColor,
                unfocusedTextColor = DarkTextColor,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                errorContainerColor = ErrorRed.copy(alpha = 0.2f),
                cursorColor = DarkPink,
                focusedBorderColor = DarkPink,
                unfocusedBorderColor = SoftPink,
                focusedLabelColor = DarkPink,
                unfocusedLabelColor = LightTextColor
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле пароля
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {
                Text(
                    "Пароль",
                    color = DarkTextColor,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = DarkTextColor,
                unfocusedTextColor = DarkTextColor,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                errorContainerColor = ErrorRed.copy(alpha = 0.2f),
                cursorColor = DarkPink,
                focusedBorderColor = DarkPink,
                unfocusedBorderColor = SoftPink,
                focusedLabelColor = DarkPink,
                unfocusedLabelColor = LightTextColor
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Сообщение об ошибке
        if (errorMessage.isNotEmpty()) {
            Text(
                errorMessage,
                color = ErrorRed,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка входа
        Button(
            onClick = onLoginClick,
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkPink,
                contentColor = Color.White,
                disabledContainerColor = SoftPink
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 4.dp
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    "Войти",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ссылка на регистрацию
        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Нет аккаунта? Зарегистрируйтесь",
                color = DarkPink,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}