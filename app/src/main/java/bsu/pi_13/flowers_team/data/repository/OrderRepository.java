package bsu.pi_13.flowers_team.data.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bsu.pi_13.flowers_team.data.db.DBHelper;


public class OrderRepository {
    private final SQLiteDatabase database;

    public OrderRepository(SQLiteDatabase database) {
        this.database = database;
    }

    public long createOrder(int userId, double totalPrice) {
        try {
            ContentValues values = new ContentValues();
            values.put("user_id", userId);
            values.put("order_date", getCurrentDateTime());
            values.put("total_price", totalPrice);

            return database.insert(DBHelper.TABLE_ORDERS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean addOrderItem(long orderId, int flowerId, int quantity, double price) {
        try {
            ContentValues values = new ContentValues();
            values.put("order_id", orderId);
            values.put("flower_id", flowerId);
            values.put("quantity", quantity);
            values.put("price", price);

            return database.insert("order_items", null, values) != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }
}