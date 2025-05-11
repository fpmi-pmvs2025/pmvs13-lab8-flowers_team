package bsu.pi_13.flowers_team

import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.data.model.Flower
import bsu.pi_13.flowers_team.data.repository.OrderRepository
import bsu.pi_13.flowers_team.feature.basket.view_model.BasketViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class BasketViewModelTest {

    private lateinit var viewModel: BasketViewModel
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var orderRepository: OrderRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    private val testFlower = Flower(1, "Роза", 199.0, "", "")

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dbHelper = mock(DatabaseHelper::class.java)
        orderRepository = mock(OrderRepository::class.java)
        viewModel = BasketViewModel()

        `when`(dbHelper.getOrderRepository()).thenReturn(orderRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test error shown when user not logged in`() = runTest {
        viewModel.processOrder(
            dbHelper = dbHelper,
            currentUserId = -1,
            cartItems = listOf(testFlower),
            onSuccess = {}
        )

        assert(viewModel.showNotification)
        assert(!viewModel.isSuccessNotification)
        assert(viewModel.notificationMessage == "Для оформления заказа войдите в систему")
    }

    @Test
    fun `test error when createOrder returns -1`() = runTest {
        `when`(orderRepository.createOrder(anyInt(), anyDouble())).thenReturn(-1L)

        viewModel.processOrder(
            dbHelper = dbHelper,
            currentUserId = 1,
            cartItems = listOf(testFlower),
            onSuccess = {}
        )

        assert(viewModel.showNotification)
        assert(!viewModel.isSuccessNotification)
        assert(viewModel.notificationMessage == "Ошибка создания заказа")
    }

    @Test
    fun `test success order creation`() = runTest {
        `when`(orderRepository.createOrder(anyInt(), anyDouble())).thenReturn(123L)
        `when`(orderRepository.addOrderItem(anyLong(), anyInt(), anyInt(), anyDouble())).thenReturn(true)

        var successCalled = false

        viewModel.processOrder(
            dbHelper = dbHelper,
            currentUserId = 1,
            cartItems = listOf(testFlower),
            onSuccess = { successCalled = true }
        )

        assert(viewModel.showNotification)
        assert(viewModel.isSuccessNotification)
        assert(viewModel.notificationMessage.contains("Заказ #123 оформлен"))
        assert(successCalled)
    }

    @Test
    fun `test exception handled in processOrder`() = runTest {
        `when`(orderRepository.createOrder(anyInt(), anyDouble())).thenThrow(RuntimeException("DB failure"))

        viewModel.processOrder(
            dbHelper = dbHelper,
            currentUserId = 1,
            cartItems = listOf(testFlower),
            onSuccess = {}
        )

        assert(viewModel.showNotification)
        assert(!viewModel.isSuccessNotification)
        assert(viewModel.notificationMessage.contains("Ошибка"))
    }
}
