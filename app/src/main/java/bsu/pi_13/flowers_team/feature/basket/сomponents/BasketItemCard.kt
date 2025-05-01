
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.ui.theme.DarkGreen
import bsu.pi_13.flowers_team.ui.theme.DarkPink
import bsu.pi_13.flowers_team.ui.theme.DarkTextColor
import bsu.pi_13.flowers_team.ui.theme.SoftPink
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun BasketItemCard(flower: Flower, onRemove: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = DarkTextColor
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            flower.imageUrl?.takeIf { it.isNotEmpty() }?.let { url ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("android.resource://${LocalContext.current.packageName}/drawable/${flower.imageUrl}")
                        .crossfade(true)
                        .build(),
                    contentDescription = flower.name,
                    modifier = Modifier
                        .size(80.dp)
                        .background(SoftPink.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = flower.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = DarkPink,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = "${flower.price} руб.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = DarkGreen
                    ),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Удалить",
                    tint = Color(0xFFE57373)
                )
            }
        }
    }
}