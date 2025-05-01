package bsu.pi_13.flowers_team.feature.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import bsu.pi_13.flowers_team.feature.home.screen.Screen

sealed class BottomNavItem(val title: String, val icon: ImageVector, val screen: Screen) {
    object Catalog : BottomNavItem("Каталог", Icons.Default.Home, Screen.Catalog)
    object Cart : BottomNavItem("Корзина", Icons.Default.ShoppingCart, Screen.Basket)
    object Profile : BottomNavItem("Профиль", Icons.Default.AccountCircle, Screen.Profile)
}

