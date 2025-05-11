package bsu.pi_13.flowers_team

import bsu.pi_13.flowers_team.data.model.Flower
import org.junit.Assert.assertEquals
import org.junit.Test

class FlowerCardLogicTest {

    private val sampleFlower = Flower(1, "Ромашка", 50.0, "Летняя ромашка", "daisy")

    @Test
    fun testAddToCart() {
        val cart = mutableListOf<Flower>()
        val addToCart: (Flower) -> Unit = { flower -> cart.add(flower) }

        addToCart(sampleFlower)

        assertEquals(1, cart.size)
        assertEquals("Ромашка", cart[0].name)
    }

    @Test
    fun testExpandLogic() {
        var expandedId: Int? = null
        val flowerId = 5

        val toggleExpand: (Int) -> Unit = {
            expandedId = if (expandedId == it) null else it
        }

        toggleExpand(flowerId)
        assertEquals(flowerId, expandedId)

        toggleExpand(flowerId)
        assertEquals(null, expandedId)
    }
}
