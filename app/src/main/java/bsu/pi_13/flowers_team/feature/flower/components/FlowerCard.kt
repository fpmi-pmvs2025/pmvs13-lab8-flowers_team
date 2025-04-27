package bsu.pi_13.flowers_team.feature.flower.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.ui.theme.DarkGreen
import bsu.pi_13.flowers_team.ui.theme.DarkPink
import bsu.pi_13.flowers_team.ui.theme.SoftPink
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun FlowerCard(
    flower: Flower,
    isExpanded: Boolean,
    onExpand: () -> Unit,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onExpand,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color(0xFF333333)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            flower.imageUrl?.takeIf { it.isNotEmpty() }?.let { url ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("android.resource://${LocalContext.current.packageName}/drawable/${flower.imageUrl}")
                        .crossfade(true)
                        .build(),
                    contentDescription = flower.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = flower.name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = DarkPink
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Цена: ${flower.price} руб.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = DarkGreen
                ),
                modifier = Modifier.padding(top = 4.dp)
            )

            if (isExpanded) {
                flower.description?.let { desc ->
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF555555)
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Button(
                    onClick = onAddToCart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SoftPink,
                        contentColor = Color(0xFF5D2D50)
                    )
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Добавить")
                    Spacer(Modifier.width(8.dp))
                    Text("Добавить в корзину")
                }
            }
        }
    }
}