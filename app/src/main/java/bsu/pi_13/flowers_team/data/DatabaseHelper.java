package bsu.pi_13.flowers_team.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public DatabaseHelper(Context context) {
        dbHelper = new DBHelper(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public boolean authenticateUser(String login, String password) {
        String query = "SELECT * FROM Users WHERE login = ? AND password = ?";
        Cursor cursor = database.rawQuery(query, new String[]{login, password});
        boolean isAuthenticated = cursor.getCount() > 0;
        cursor.close();
        return isAuthenticated;
    }


    public boolean registerUser(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            return false; 
        }

        String query = "SELECT 1 FROM Users WHERE login = ?";
        Cursor cursor = database.rawQuery(query, new String[]{login});
        boolean userExists = cursor.moveToFirst();
        cursor.close();

        if (userExists) {
            return false; 
        }

        ContentValues values = new ContentValues();
        values.put("login", login);
        values.put("password", password);
        values.put("role", "S");

        long result = database.insert("Users", null, values);

        return result != -1; 
    }

}