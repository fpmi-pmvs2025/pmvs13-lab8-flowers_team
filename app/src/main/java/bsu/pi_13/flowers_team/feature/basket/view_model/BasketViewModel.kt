package bsu.pi_13.flowers_team.feature.basket.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.data.model.Flower
import kotlinx.coroutines.launch

class BasketViewModel : ViewModel() {
    var showNotification by mutableStateOf(false)
        private set
    var notificationMessage by mutableStateOf("")
        private set
    var isSuccessNotification by mutableStateOf(true)
        private set

    fun processOrder(
        dbHelper: DatabaseHelper,
        currentUserId: Int,
        cartItems: List<Flower>,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                if (currentUserId == -1) {
                    showError("Для оформления заказа войдите в систему")
                    return@launch
                }

                dbHelper.open()
                val orderId = dbHelper.getOrderRepository().createOrder(
                    currentUserId,
                    cartItems.sumOf { it.price.toDouble() }
                )

                if (orderId == -1L) {
                    showError("Ошибка создания заказа")
                    return@launch
                }

                cartItems.forEach { flower ->
                    dbHelper.getOrderRepository().addOrderItem(
                        orderId,
                        flower.id,
                        1,
                        flower.price.toDouble()
                    )
                }

                showSuccess("Заказ #$orderId оформлен!")
                onSuccess()
            } catch (e: Exception) {
                showError("Ошибка: ${e.localizedMessage}")
                e.printStackTrace()
            } finally {
                dbHelper.close()
            }
        }
    }

    private fun showSuccess(message: String) {
        notificationMessage = message
        isSuccessNotification = true
        showNotification = true
    }

    private fun showError(message: String) {
        notificationMessage = message
        isSuccessNotification = false
        showNotification = true
    }

    fun dismissNotification() {
        showNotification = false
    }
}