package bsu.pi_13.flowers_team.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bsu.pi_13.flowers_team.ui.theme.DarkPink
import bsu.pi_13.flowers_team.ui.theme.MilkBackground


@Composable
public fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MilkBackground),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = DarkPink)
    }
}