package bsu.pi_13.flowers_team.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.feature.basket.CartScreen
import bsu.pi_13.flowers_team.feature.flower.FlowerScreen
import bsu.pi_13.flowers_team.feature.profile.ProfileScreen
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.feature.home.component.LoadingIndicator
import bsu.pi_13.flowers_team.ui.theme.DarkGreen
import bsu.pi_13.flowers_team.ui.theme.DarkPink

import bsu.pi_13.flowers_team.ui.theme.LightTextColor
import bsu.pi_13.flowers_team.ui.theme.MilkBackground
import bsu.pi_13.flowers_team.ui.theme.SoftGreen
import bsu.pi_13.flowers_team.ui.theme.SoftPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, onLogout: () -> Unit) {
    val context = LocalContext.current
    var flowers by remember { mutableStateOf<List<Flower>>(emptyList()) }
    var cartItems by remember { mutableStateOf<List<Flower>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Catalog) }
    var expandedFlowerId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        val dbHelper = DatabaseHelper(context)
        try {
            dbHelper.open()
            flowers = dbHelper.flowerRepository.getAllFlowers()
        } finally {
            dbHelper.close()
            isLoading = false
        }
    }

    fun addToCart(flower: Flower) {
        cartItems = cartItems + flower
    }

    fun removeFromCart(flowerId: Int) {
        cartItems = cartItems.filter { it.id != flowerId }
    }

    Scaffold(
        modifier = Modifier.background(MilkBackground),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Цветочная лавка",
                        color = DarkPink,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White
                ),

            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Каталог",
                            tint = if (currentScreen == Screen.Catalog) DarkPink else LightTextColor
                        )
                    },
                    label = {
                        Text(
                            "Каталог",
                            color = if (currentScreen == Screen.Catalog) DarkPink else LightTextColor
                        )
                    },
                    selected = currentScreen == Screen.Catalog,
                    onClick = { currentScreen = Screen.Catalog },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkPink,
                        selectedTextColor = DarkPink,
                        indicatorColor = SoftPink.copy(alpha = 0.4f)
                    )
                )
                NavigationBarItem(
                    icon = {
                        BadgedBox(badge = {
                            if (cartItems.isNotEmpty()) {
                                Badge(
                                    containerColor = DarkGreen,
                                    contentColor = Color.White
                                ) {
                                    Text(cartItems.size.toString())
                                }
                            }
                        }) {
                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = "Корзина",
                                tint = if (currentScreen == Screen.Cart) DarkGreen else LightTextColor
                            )
                        }
                    },
                    label = {
                        Text(
                            "Корзина",
                            color = if (currentScreen == Screen.Cart) DarkGreen else LightTextColor
                        )
                    },
                    selected = currentScreen == Screen.Cart,
                    onClick = { currentScreen = Screen.Cart },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkGreen,
                        selectedTextColor = DarkGreen,
                        indicatorColor = SoftGreen.copy(alpha = 0.4f)
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Профиль",
                            tint = if (currentScreen == Screen.Profile) DarkPink else LightTextColor
                        )
                    },
                    label = {
                        Text(
                            "Профиль",
                            color = if (currentScreen == Screen.Profile) DarkPink else LightTextColor
                        )
                    },
                    selected = currentScreen == Screen.Profile,
                    onClick = { currentScreen = Screen.Profile },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkPink,
                        selectedTextColor = DarkPink,
                        indicatorColor = SoftPink.copy(alpha = 0.4f)
                    )
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .background(MilkBackground)
        ) {
            when (currentScreen) {
                is Screen.Catalog -> {
                    when {
                        isLoading -> LoadingIndicator()
                        flowers.isEmpty() -> EmptyState()
                        else -> FlowerScreen(
                            flowers = flowers,
                            expandedFlowerId = expandedFlowerId,
                            onExpand = { id -> expandedFlowerId = id },
                            onAddToCart = ::addToCart
                        )
                    }
                }
                is Screen.Cart -> {
                    CartScreen(
                        cartItems = cartItems,
                        onRemoveItem = ::removeFromCart
                    )
                }
                is Screen.Profile -> {
                    ProfileScreen(
                        onLogoutClick = onLogout
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MilkBackground),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Цветы не найдены",
            style = MaterialTheme.typography.bodyLarge.copy(color = LightTextColor)
        )
    }
}


sealed class Screen {
    object Catalog : Screen()
    object Cart : Screen()
    object Profile : Screen()
}

