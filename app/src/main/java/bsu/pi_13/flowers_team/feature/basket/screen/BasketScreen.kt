package bsu.pi_13.flowers_team.feature.basket.screen

import EmptyBasketView
import NotificationView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.feature.basket.content.BasketContent
import bsu.pi_13.flowers_team.feature.basket.view_model.BasketViewModel
import bsu.pi_13.flowers_team.ui.theme.*
import kotlinx.coroutines.delay

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