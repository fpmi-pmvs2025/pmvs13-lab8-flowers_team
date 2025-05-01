package bsu.pi_13.flowers_team.feature.profile.view_model
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.data.model.Order

class ProfileViewModel(
    dbHelper: DatabaseHelper
) : ViewModel() {

    private val userRepository = dbHelper.getUserRepository()
    private val orderRepository = dbHelper.getOrderRepository()

    var userName by mutableStateOf("")
        private set

    var userOrders by mutableStateOf<List<Order>>(emptyList())
        private set

    fun loadUserData(userId: Int) {
        userName = userRepository.getUserLogin(userId)
        userOrders = orderRepository.getOrdersByUserId(userId)
    }
}
