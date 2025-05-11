package bsu.pi_13.flowers_team

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.feature.profile.copmonent.OrdersList
import bsu.pi_13.flowers_team.feature.profile.copmonent.ProfileActions
import bsu.pi_13.flowers_team.data.model.Order
import bsu.pi_13.flowers_team.feature.profile.copmonent.ProfileHeader
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val dbHelper = DatabaseHelper(context)

    @Test
    fun testProfileHeader_displayCorrectUserName() {
        val userName = "John Doe"

        composeTestRule.setContent {
            ProfileHeader(userName = userName)
        }

        composeTestRule.onNodeWithText(userName).assertIsDisplayed()
    }

    @Test
    fun testOrdersList_whenEmpty() {

        val emptyOrdersList = listOf<Order>()

        composeTestRule.setContent {
            OrdersList(userOrders = emptyOrdersList)
        }


        composeTestRule.onNodeWithText("У вас пока нет заказов").assertIsDisplayed()
    }
    @Test
    fun testProfileActions_logoutButton() {

        composeTestRule.setContent {
            ProfileActions(
                onOrdersClick = {},
                onLogoutClick ={}
            )
        }


        composeTestRule.onNodeWithText("Выйти").performClick()
    }
}
