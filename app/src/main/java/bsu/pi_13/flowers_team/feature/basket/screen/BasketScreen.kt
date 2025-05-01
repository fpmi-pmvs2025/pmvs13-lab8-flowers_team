package bsu.pi_13.flowers_team.feature.basket.screen

import BasketItemCard
import EmptyBasketView
import NotificationBanner
import NotificationView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.feature.basket.content.BasketContent
import bsu.pi_13.flowers_team.feature.basket.view_model.BasketViewModel
import bsu.pi_13.flowers_team.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BasketScreen(
    cartItems: List<Flower>,
    currentUserId: Int,
    onRemoveItem: (Flower) -> Unit,
    onOrderSuccess: () -> Unit
) {
    val context = LocalContext.current
    val dbHelper = remember { DatabaseHelper(context) }
    val viewModel: BasketViewModel = viewModel()

    LaunchedEffect(viewModel.showNotification) {
        if (viewModel.showNotification) {
            delay(3000)
            viewModel.dismissNotification()
            if (viewModel.isSuccessNotification) {
                onOrderSuccess()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MilkBackground)) {
        if (cartItems.isEmpty()) {
            EmptyBasketView()
        } else {
            BasketContent(
                cartItems = cartItems,
                onRemoveItem = onRemoveItem, 
                onOrderClick = {
                    viewModel.processOrder(
                        dbHelper = dbHelper,
                        currentUserId = currentUserId,
                        cartItems = cartItems,
                        onSuccess = onOrderSuccess
                    )
                }
            )
        }

        NotificationView(viewModel)
    }
}