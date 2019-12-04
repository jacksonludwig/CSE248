package nonThreadedVersion;

public class Order {
    private static int orderNumber = 0;
    // prices, name, description, etc.

    public Order() {
        System.out.println("Order " + ++orderNumber + " was created.");
    }

    @Override
    public String toString() {
        return "The order number is: " + orderNumber;
    }
}
