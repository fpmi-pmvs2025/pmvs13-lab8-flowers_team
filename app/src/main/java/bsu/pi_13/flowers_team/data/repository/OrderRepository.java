package bsu.pi_13.flowers_team.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bsu.pi_13.flowers_team.data.db.DBHelper;
import bsu.pi_13.flowers_team.data.model.Order;
import bsu.pi_13.flowers_team.data.model.OrderItem;

public class OrderRepository {
    private final SQLiteDatabase database;

    public OrderRepository(SQLiteDatabase database) {
        this.database = database;
    }

    public long createOrder(int userId, double totalPrice) {
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("order_date", getCurrentDateTime());
        values.put("total_price", totalPrice);

        return database.insert(DBHelper.TABLE_ORDERS, null, values);
    }

    public boolean addOrderItem(long orderId, int flowerId, int quantity, double price) {
        ContentValues values = new ContentValues();
        values.put("order_id", orderId);
        values.put("flower_id", flowerId);
        values.put("quantity", quantity);
        values.put("price", price);

        return database.insert(DBHelper.TABLE_ORDER_ITEMS, null, values) != -1;
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();

        Cursor orderCursor = database.query(
                DBHelper.TABLE_ORDERS,
                new String[]{"id", "order_date", "total_price", "status"},
                "user_id = ?",
                new String[]{String.valueOf(userId)},
                null, null, "order_date DESC"
        );

        while (orderCursor.moveToNext()) {
            long orderId = orderCursor.getLong(0);
            String orderDate = orderCursor.getString(1);
            double totalPrice = orderCursor.getDouble(2);
            String status = orderCursor.getString(3);

            List<OrderItem> items = getOrderItems(orderId);

            orders.add(new Order(orderId, userId, orderDate, totalPrice, status, items));
        }

        orderCursor.close();
        return orders;
    }

    private List<OrderItem> getOrderItems(long orderId) {
        List<OrderItem> items = new ArrayList<>();

        Cursor itemCursor = database.query(
                DBHelper.TABLE_ORDER_ITEMS,
                new String[]{"flower_id", "quantity", "price"},
                "order_id = ?",
                new String[]{String.valueOf(orderId)},
                null, null, null
        );

        while (itemCursor.moveToNext()) {
            int flowerId = itemCursor.getInt(0);
            int quantity = itemCursor.getInt(1);
            double price = itemCursor.getDouble(2);

            items.add(new OrderItem(orderId, flowerId, quantity, price));
        }

        itemCursor.close();
        return items;
    }

    private String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }
}
