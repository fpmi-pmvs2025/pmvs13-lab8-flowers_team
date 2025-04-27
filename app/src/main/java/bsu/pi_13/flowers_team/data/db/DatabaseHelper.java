package bsu.pi_13.flowers_team.data.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import bsu.pi_13.flowers_team.data.repository.FlowerRepository;
import bsu.pi_13.flowers_team.data.repository.UserRepository;

public class DatabaseHelper {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private UserRepository userRepository;
    private FlowerRepository flowerRepository;

    public DatabaseHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        userRepository = new UserRepository(database);
        flowerRepository = new FlowerRepository(database);
    }

    public void close() {
        dbHelper.close();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public FlowerRepository getFlowerRepository() {
        return flowerRepository;
    }
}
