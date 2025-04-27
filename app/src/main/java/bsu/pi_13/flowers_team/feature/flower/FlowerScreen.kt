package bsu.pi_13.flowers_team.feature.flower

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.feature.flower.components.FlowerCard
import bsu.pi_13.flowers_team.ui.theme.MilkBackground


@Composable
fun FlowerScreen(
    flowers: List<Flower>,
    expandedFlowerId: Int?,
    onExpand: (Int) -> Unit,
    onAddToCart: (Flower) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MilkBackground),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(flowers) { flower ->
            FlowerCard(
                flower = flower,
                isExpanded = flower.id == expandedFlowerId,
                onExpand = { (if (flower.id == expandedFlowerId) null else flower.id)?.let(onExpand) },
                onAddToCart = { onAddToCart(flower) }
            )
        }
    }
}
