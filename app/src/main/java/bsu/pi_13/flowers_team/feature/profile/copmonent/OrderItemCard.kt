package bsu.pi_13.flowers_team.feature.profile.copmonent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bsu.pi_13.flowers_team.data.model.Order
import bsu.pi_13.flowers_team.ui.theme.DarkPink


@Composable
fun OrderItemCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Заказ номер ${order.id}",
                style = MaterialTheme.typography.titleMedium,
                color = DarkPink
            )
            Text(
                text = order.orderDate,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Статус: новый",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Сумма: ${order.totalPrice} руб.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            order.items.forEach { item ->
                Text(
                    text = "• Товар #${item.flowerId}, ${item.quantity} шт. x ${item.price} руб.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}