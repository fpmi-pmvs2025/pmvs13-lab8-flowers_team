
package bsu.pi_13.flowers_team.data.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flower.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_FLOWERS = "flowers";
    public static final String TABLE_ORDERS = "orders";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_ORDER_ITEMS = "order_items";

    private final Context context;
    private final String DB_PATH;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.DB_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
        createDatabaseIfNotExists();
    }

    private void createDatabaseIfNotExists() {
        File dbFile = new File(DB_PATH);
        if (!dbFile.exists()) {
            getReadableDatabase();
            copyDatabaseFromAssets();
        }
    }

    private void copyDatabaseFromAssets() {
        try (InputStream is = context.getAssets().open(DATABASE_NAME);
             OutputStream os = new FileOutputStream(DB_PATH)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка копирования базы данных из assets", e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
