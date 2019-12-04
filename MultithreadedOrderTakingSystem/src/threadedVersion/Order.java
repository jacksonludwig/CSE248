package threadedVersion;

public class Order {
    private int orderNumber;
    // prices, name, description, etc.

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
       // System.out.println("Order " + orderNumber + " was created.");
    }

    @Override
    public String toString() {
        return "The order number is: " + orderNumber;
    }
}
