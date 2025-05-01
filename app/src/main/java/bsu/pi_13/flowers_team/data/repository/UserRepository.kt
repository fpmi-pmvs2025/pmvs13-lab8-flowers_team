package bsu.pi_13.flowers_team.data.repository
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import bsu.pi_13.flowers_team.data.db.DBHelper
import bsu.pi_13.flowers_team.data.db.DBHelper.TABLE_USERS


class UserRepository(private val database: SQLiteDatabase) {
    fun authenticateUser(login: String, password: String): Boolean {
        val cursor = database.query(
            DBHelper.TABLE_USERS,
            null,
            "login = ? AND password = ?",
            arrayOf(login, password),
            null, null, null
        )
        val result = cursor.count > 0
        cursor.close()
        return result
    }

    fun registerUser(login: String?, password: String?): Boolean {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            return false
        }

        val cursor = database.query(
            DBHelper.TABLE_USERS,
            null,
            "login = ?",
            arrayOf(login),
            null, null, null
        )
        val userExists = cursor.count > 0
        cursor.close()

        if (userExists) {
            return false
        }

        val values = ContentValues()
        values.put("login", login)
        values.put("password", password)
        values.put("role", "S")

        return database.insert(DBHelper.TABLE_USERS, null, values) != -1L
    }

    fun getUserIdByLogin(login: String): Int {
        val cursor = database.query(
            TABLE_USERS,
            arrayOf("id"),
            "login = ?",
            arrayOf(login),
            null, null, null
        )
        return if (cursor.moveToFirst()) {
            cursor.getInt(0)
        } else {
            -1
        }.also { cursor.close() }
    }
}
