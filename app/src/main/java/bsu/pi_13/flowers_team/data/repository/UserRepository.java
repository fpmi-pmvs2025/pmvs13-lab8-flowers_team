package bsu.pi_13.flowers_team.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bsu.pi_13.flowers_team.data.db.DBHelper;

public class UserRepository {
    private final SQLiteDatabase database;

    public UserRepository(SQLiteDatabase database) {
        this.database = database;
    }

    public boolean authenticateUser(String login, String password) {
        Cursor cursor = database.query(
                DBHelper.TABLE_USERS,
                null,
                "login = ? AND password = ?",
                new String[]{login, password},
                null, null, null
        );

        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    public boolean registerUser(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) return false;

        Cursor cursor = database.query(
                DBHelper.TABLE_USERS,
                null,
                "login = ?",
                new String[]{login},
                null, null, null
        );

        boolean userExists = cursor.moveToFirst();
        cursor.close();

        if (userExists) return false;

        ContentValues values = new ContentValues();
        values.put("login", login);
        values.put("password", password);
        values.put("role", "S");

        return database.insert(DBHelper.TABLE_USERS, null, values) != -1;
    }

    public String getUserLogin(int userId) {
        Cursor cursor = database.query(
                DBHelper.TABLE_USERS,
                new String[]{"login"},
                "id = ?",
                new String[]{String.valueOf(userId)},
                null, null, null
        );

        String login = "";
        if (cursor.moveToFirst()) {
            login = cursor.getString(0);
        }
        cursor.close();
        return login;
    }

    public int getUserIdByLogin(String login) {
        Cursor cursor = database.query(
                DBHelper.TABLE_USERS,
                new String[]{"id"},
                "login = ?",
                new String[]{login},
                null, null, null
        );

        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }
}
