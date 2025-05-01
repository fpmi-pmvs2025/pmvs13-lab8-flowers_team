
package bsu.pi_13.flowers_team.data.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "flower.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_USERS = "Users";
    public static final String TABLE_FLOWERS = "Flowers";

    public static final String TABLE_ORDERS = "Orders";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "login TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "role TEXT NOT NULL);");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FLOWERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "image_url TEXT, " +
                "description TEXT);");

        
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ORDERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER NOT NULL, " +
                "order_date TEXT NOT NULL, " +
                "total_price REAL NOT NULL, " +
                "status TEXT NOT NULL DEFAULT 'new', " +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id));");

        db.execSQL("CREATE TABLE IF NOT EXISTS order_items (" +
                "order_id INTEGER NOT NULL, " +
                "flower_id INTEGER NOT NULL, " +
                "quantity INTEGER NOT NULL DEFAULT 1, " +
                "price REAL NOT NULL, " +
                "PRIMARY KEY(order_id, flower_id), " +
                "FOREIGN KEY(order_id) REFERENCES " + TABLE_ORDERS + "(id), " +
                "FOREIGN KEY(flower_id) REFERENCES " + TABLE_FLOWERS + "(id));");

        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS idx_users_login ON " + TABLE_USERS + "(login);");


        db.execSQL("INSERT INTO " + TABLE_USERS + " (login, password, role) VALUES ('user1', 'user123', 'S');");
        db.execSQL("INSERT INTO " + TABLE_USERS + " (login, password, role) VALUES ('user2', 'user456', 'S');");


        db.execSQL("INSERT INTO " + TABLE_FLOWERS + " (name, price, image_url, description) VALUES " +
                "('Букет красных роз', 120.00, 'delicate_bouquet', 'Роскошные красные розы для особых случаев'), " +
                "('Весенний букет', 95.50, 'rose_bouquet1', 'Композиция из тюльпанов, мимозы и ирисов'), " +
                "('Букет «Нежность»', 110.00, 'b1', 'Сочетание белых роз и эустомы'), " +
                "('Яркий микс', 135.99, 'b2', 'Солнечный сборник разноцветных гербер и ромашек'), " +
                "('Романтический букет', 145.75, 'b3', 'Розовые пионы и сирень'), " +
                "('Зимняя сказка', 105.30, 'b4', 'Белоснежные розы и еловые ветки'), " +
                "('Летнее настроение', 88.80, 'b5', 'Полевые цветы в стильной упаковке'), " +
                "('Букет лилий', 130.00, 'b6', 'Белые и розовые лилии в оформлении'), " +
                "('Элегантный букет', 99.90, 'b7', 'Классическое сочетание роз и альстромерий'), " +
                "('Фантазия', 125.50, 'b8', 'Креативная композиция из экзотических цветов');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FLOWERS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "price REAL NOT NULL, " +
                    "image_url TEXT, " +
                    "description TEXT);");
            db.execSQL("INSERT INTO " + TABLE_FLOWERS + " (name, price, image_url, description) VALUES " +
                    "('Букет «Морозный день»', 112.40, '', 'Голубые ирисы и белые розы'), " +
                    "('Осенний вальс', 98.70, '', 'Букет в теплых осенних оттенках');");
        }
    }
}