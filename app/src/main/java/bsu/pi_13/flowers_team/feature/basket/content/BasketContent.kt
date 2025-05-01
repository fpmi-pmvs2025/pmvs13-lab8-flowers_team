package bsu.pi_13.flowers_team.feature.basket.content

import BasketItemCard
import OrderSummary
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.ui.theme.DarkPink
import bsu.pi_13.flowers_team.ui.theme.MilkBackground

@Composable
fun BasketContent(
    cartItems: List<Flower>,
    onRemoveItem: (Flower) -> Unit,
    onOrderClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MilkBackground)
    ) {
        Text(
            text = "Ваша корзина",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = DarkPink,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cartItems, key = { it.id }) { flower ->  // Добавлен key для стабильной анимации
                BasketItemCard(
                    flower = flower,
                    onRemove = { onRemoveItem(flower) }
                )
            }
        }

        OrderSummary(
            totalPrice = cartItems.sumOf { it.price.toDouble() },
            onOrderClick = onOrderClick
        )
    }
}