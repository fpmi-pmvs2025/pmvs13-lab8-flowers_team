import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import bsu.pi_13.flowers_team.ui.theme.DarkGreen
import bsu.pi_13.flowers_team.ui.theme.DarkPink
import bsu.pi_13.flowers_team.ui.theme.DarkTextColor
import bsu.pi_13.flowers_team.ui.theme.ErrorRed
import bsu.pi_13.flowers_team.ui.theme.LightTextColor
import bsu.pi_13.flowers_team.ui.theme.MilkBackground
import bsu.pi_13.flowers_team.ui.theme.SoftGreen
import bsu.pi_13.flowers_team.ui.theme.SoftPink
//
//@Composable
//fun RegisterContent(
//    login: String,
//    password: String,
//    errorMessage: String,
//    isLoading: Boolean,
//    onLoginChange: (String) -> Unit,
//    onPasswordChange: (String) -> Unit,
//    onRegisterClick: () -> Unit,
//    onLoginNavigate: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            "Register",
//            fontSize = 32.sp,
//            fontWeight = FontWeight.Bold,
//            color = blackTextColor,
//
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        OutlinedTextField(
//            value = login,
//            onValueChange = onLoginChange,
//            label = { Text("Login", color = blackTextColor) },
//            modifier = Modifier
//                .fillMaxWidth(),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedTextColor = blackTextColor,
//                unfocusedTextColor = blackTextColor,
//                focusedContainerColor = whiteBackgroundColor,
//                unfocusedContainerColor = whiteBackgroundColor,
//                errorContainerColor = Color.Red,
//                cursorColor = blackTextColor,
//                focusedLabelColor = blackTextColor,
//                unfocusedLabelColor = blackTextColor
//            )
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = onPasswordChange,
//            label = { Text("Password", color = blackTextColor) },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier
//                .fillMaxWidth(),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedTextColor = blackTextColor,
//                unfocusedTextColor = blackTextColor,
//                focusedContainerColor = whiteBackgroundColor,
//                unfocusedContainerColor = whiteBackgroundColor,
//                errorContainerColor = Color.Red,
//                cursorColor = blackTextColor,
//                focusedLabelColor = blackTextColor,
//                unfocusedLabelColor = blackTextColor
//            )
//        )
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(errorMessage, color = Color.Red)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = onRegisterClick,
//            enabled = !isLoading,
//            colors = ButtonDefaults.buttonColors(containerColor = lightGreen)
//        ) {
//            if (isLoading) {
//                Text("Processing...")
//            } else {
//                Text("Register")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextButton(onClick = onLoginNavigate) {
//            Text("Already have an account? Login",)
//        }
//    }
//}



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
            .background(MilkBackground)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            "Создайте аккаунт",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = DarkGreen,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Заполните форму регистрации",
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
                cursorColor = DarkGreen,
                focusedBorderColor = DarkGreen,
                unfocusedBorderColor = SoftGreen,
                focusedLabelColor = DarkGreen,
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
                cursorColor = DarkGreen,
                focusedBorderColor = DarkGreen,
                unfocusedBorderColor = SoftGreen,
                focusedLabelColor = DarkGreen,
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

        // Кнопка регистрации
        Button(
            onClick = onRegisterClick,
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkGreen,
                contentColor = Color.White,
                disabledContainerColor = SoftGreen
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
                    "Зарегистрироваться",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ссылка на вход
        TextButton(
            onClick = onLoginNavigate,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Уже есть аккаунт? Войдите",
                color = DarkGreen,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}