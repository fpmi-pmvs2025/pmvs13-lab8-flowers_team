package bsu.pi_13.flowers_team

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.feature.flower.FlowerScreen
import bsu.pi_13.flowers_team.feature.flower.components.FlowerCard
import org.junit.Rule
import org.junit.Test

class FlowerScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sampleFlowers = listOf(
        Flower(1, "Роза", 120.0, "Красная роза", "rose_image"),
        Flower(2, "Тюльпан", 90.0, "Весенний тюльпан", "tulip_image")
    )

    @Test
    fun flowerScreen_displaysFlowerNames() {
        composeTestRule.setContent {
            FlowerScreen(
                flowers = sampleFlowers,
                expandedFlowerId = null,
                onExpand = {},
                onAddToCart = {}
            )
        }

        composeTestRule.onNodeWithText("Роза").assertIsDisplayed()
        composeTestRule.onNodeWithText("Тюльпан").assertIsDisplayed()
    }

    @Test
    fun flowerCard_expandsAndShowsDescriptionAndButton() {
        var isExpanded = false
        composeTestRule.setContent {
            FlowerCard(
                flower = sampleFlowers[0],
                isExpanded = isExpanded,
                onExpand = { isExpanded = !isExpanded },
                onAddToCart = {}
            )
        }


        composeTestRule.onAllNodesWithText("Добавить в корзину").assertCountEquals(0)


        composeTestRule.onNodeWithText("Роза").performClick()
    }
}
