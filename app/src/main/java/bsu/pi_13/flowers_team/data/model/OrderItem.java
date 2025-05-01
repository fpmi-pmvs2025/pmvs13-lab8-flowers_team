package bsu.pi_13.flowers_team.data.model;

public class OrderItem {
    private final int flowerId;
    private final int quantity;
    private final double price;

    public OrderItem(long orderId, int flowerId, int quantity, double price) {
        this.flowerId = flowerId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getFlowerId() {
        return flowerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}