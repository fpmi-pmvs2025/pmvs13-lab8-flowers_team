package bsu.pi_13.flowers_team

import android.content.Context
import android.content.SharedPreferences
import bsu.pi_13.flowers_team.data.db.DatabaseHelper
import bsu.pi_13.flowers_team.data.repository.UserRepository
import bsu.pi_13.flowers_team.feature.auth.view_model.LoginViewModel
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var userRepository: UserRepository
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        dbHelper = mockk(relaxed = true)
        userRepository = mockk(relaxed = true)
        context = mockk()
        sharedPreferences = mockk()
        editor = mockk(relaxed = true)

        every { context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE) } returns sharedPreferences
        every { sharedPreferences.edit() } returns editor

        every { dbHelper.userRepository } returns userRepository

        viewModel = LoginViewModel(dbHelper, context)
    }

    @Test
    fun `loginUser returns false when login or password is blank`() = runBlocking {
        viewModel.login = ""
        viewModel.password = ""
        val result = viewModel.loginUser()
        assertFalse(result)
        assertEquals("Пожалуйста, введите логин и пароль", viewModel.errorMessage)
    }

    @Test
    fun `loginUser returns true when credentials are correct`() = runBlocking {
        viewModel.login = "user"
        viewModel.password = "pass"

        every { userRepository.authenticateUser("user", "pass") } returns true
        every { userRepository.getUserIdByLogin("user") } returns 1

        val result = viewModel.loginUser()

        assertTrue(result)
        assertEquals("", viewModel.errorMessage)
        verify { editor.putBoolean("isLoggedIn", true) }
        verify { editor.putInt("current_user_id", 1) }
        verify { editor.apply() }
    }

    @Test
    fun `loginUser returns false when credentials are incorrect`() = runBlocking {
        viewModel.login = "user"
        viewModel.password = "wrong"

        every { userRepository.authenticateUser("user", "wrong") } returns false

        val result = viewModel.loginUser()

        assertFalse(result)
        assertEquals("Неверный логин или пароль", viewModel.errorMessage)
    }
}
