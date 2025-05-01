package bsu.pi_13.flowers_team.feature.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.feature.profile.content.ProfileContent
import bsu.pi_13.flowers_team.feature.profile.view_model.ProfileViewModel

@Composable
fun ProfileScreen(
    userId: Int,
    onLogoutClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val dbHelper = remember { DatabaseHelper(context).apply { open() } }

    val viewModel = remember {
        ProfileViewModel(dbHelper)
    }

    LaunchedEffect(userId) {
        viewModel.loadUserData(userId)
    }

    ProfileContent(
        userName = viewModel.userName,
        userOrders = viewModel.userOrders,
        onLogoutClick = onLogoutClick
    )
}