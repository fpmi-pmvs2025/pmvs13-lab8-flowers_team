package bsu.pi_13.flowers_team
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.feature.basket.screen.BasketScreen
import org.junit.Rule
import org.junit.Test

class BasketScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sampleFlower = Flower(
        1,
        "Rose",
        199.99,
       "A beautiful red rose",
      "rose"
    )

    private val cartItems = listOf(sampleFlower)

    @Test
    fun testEmptyBasket() {
        composeTestRule.setContent {
            BasketScreen(
                cartItems = emptyList(),
                currentUserId = 1,
                onRemoveItem = {},
                onOrderSuccess = {}
            )
        }

        composeTestRule.onNodeWithText("Ваша корзина пуста", useUnmergedTree = true).assertExists()

    }

    @Test
    fun testBasketWithItems() {
        composeTestRule.setContent {
            BasketScreen(
                cartItems = cartItems,
                currentUserId = 1,
                onRemoveItem = {},
                onOrderSuccess = {}
            )
        }

        composeTestRule.onNodeWithText("Ваша корзина").assertExists()
        composeTestRule.onNodeWithText(sampleFlower.name).assertExists()
    }

    @Test
    fun testOrderSuccessNotification() {
        var successCalled = false

        composeTestRule.setContent {
            BasketScreen(
                cartItems = cartItems,
                currentUserId = 1,
                onRemoveItem = {},
                onOrderSuccess = {
                    successCalled = true
                }
            )
        }

        composeTestRule.onNodeWithText("Оформить заказ").performClick()
        assert(successCalled)
    }

    @Test
    fun testOrderFailureNotification() {
        composeTestRule.setContent {
            BasketScreen(
                cartItems = cartItems,
                currentUserId = -1,
                onRemoveItem = {},
                onOrderSuccess = {}
            )
        }

        composeTestRule.onNodeWithText("Оформить заказ").performClick()
        composeTestRule.onNodeWithText("Для оформления заказа войдите в систему").assertExists()
    }
}
