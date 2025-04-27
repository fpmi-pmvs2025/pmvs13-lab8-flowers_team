package bsu.pi_13.flowers_team.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flower.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "login TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "role TEXT NOT NULL);");

        db.execSQL("INSERT INTO Users (login, password, role) VALUES ('user1', 'user123', 'S');");
        db.execSQL("INSERT INTO Users (login, password, role) VALUES ('user2', 'user456', 'S');");
        db.execSQL("INSERT INTO Users (login, password, role) VALUES ('user3', 'user789', 'S');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Users;");
        onCreate(db);
    }
}