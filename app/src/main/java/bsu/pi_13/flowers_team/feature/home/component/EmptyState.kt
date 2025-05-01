package bsu.pi_13.flowers_team.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bsu.pi_13.flowers_team.ui.theme.LightTextColor
import bsu.pi_13.flowers_team.ui.theme.MilkBackground


@Composable
fun EmptyState() {
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
