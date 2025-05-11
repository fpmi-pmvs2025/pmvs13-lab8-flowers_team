package bsu.pi_13.flowers_team

import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.data.model.Order
import bsu.pi_13.flowers_team.data.repository.OrderRepository
import bsu.pi_13.flowers_team.data.repository.UserRepository
import bsu.pi_13.flowers_team.feature.profile.view_model.ProfileViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {

    @Mock
    private lateinit var mockUserRepository: UserRepository

    @Mock
    private lateinit var mockOrderRepository: OrderRepository

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setup() {
        profileViewModel = ProfileViewModel(mock(DatabaseHelper::class.java).apply {
            `when`(getUserRepository()).thenReturn(mockUserRepository)
            `when`(getOrderRepository()).thenReturn(mockOrderRepository)
        })
    }

    @Test
    fun `loadUserData should update userName and userOrders`() {

        val userId = 1
        val userName = "Test User"
        val orders = listOf(Order(1, userId, "2025-01-01", 100.0, "Completed", emptyList()))


        `when`(mockUserRepository.getUserLogin(userId)).thenReturn(userName)
        `when`(mockOrderRepository.getOrdersByUserId(userId)).thenReturn(orders)


        profileViewModel.loadUserData(userId)


        assertEquals(userName, profileViewModel.userName)
        assertEquals(orders, profileViewModel.userOrders)
    }
}
