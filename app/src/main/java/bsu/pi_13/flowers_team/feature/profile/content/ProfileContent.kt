package bsu.pi_13.flowers_team.feature.profile.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import bsu.pi_13.flowers_team.data.model.Order
import bsu.pi_13.flowers_team.ui.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import bsu.pi_13.flowers_team.feature.profile.copmonent.OrdersList
import bsu.pi_13.flowers_team.feature.profile.copmonent.ProfileActions
import bsu.pi_13.flowers_team.feature.profile.copmonent.ProfileHeader

@Composable
fun ProfileContent(
    userName: String,
    userOrders: List<Order>,
    onLogoutClick: () -> Unit,
) {
    var showOrders by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MilkBackground)
    ) {
        ProfileHeader(userName = userName)

        ProfileActions(
            onOrdersClick = {
                showOrders = !showOrders
            },
            onLogoutClick = onLogoutClick
        )

        if (showOrders) {
            OrdersList(userOrders = userOrders)
        }
    }
}
