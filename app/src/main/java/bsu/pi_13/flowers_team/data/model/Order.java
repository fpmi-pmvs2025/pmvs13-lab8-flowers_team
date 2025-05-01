package bsu.pi_13.flowers_team.data.model;


import java.util.List;

public class Order {
    private final long id;
    private final int userId;
    private final String orderDate;
    private final double totalPrice;
    private final String status;
    private final List<OrderItem> items;

    public Order(long id, int userId, String orderDate, double totalPrice, String status, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}