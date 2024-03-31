import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderID;
    private int customerID;
    private LocalDateTime timestamp;
    private double total;
    private int paymentID;
    private Map<Integer, Integer> products; // Map of productID to quantity



    public Order(int orderID, int customerID, LocalDateTime timestamp, double total, int paymentID) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.timestamp = timestamp;
        this.total = total;
        this.paymentID = paymentID;
        this.products = new HashMap<>();
    }

    // Getters and setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }

    // Method to add a product with quantity to the order
    public void addProduct(int productID, int quantity) {
        products.put(productID, quantity);
    }

    // Method to remove a product from the order
    public void removeProduct(int productID) {
        products.remove(productID);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order{")
                .append("orderID=").append(orderID)
                .append(", customerID=").append(customerID)
                .append(", timestamp=").append(timestamp)
                .append(", total=").append(total)
                .append(", paymentID=").append(paymentID)
                .append(", products=[");

        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
            stringBuilder.append("(ProductID=").append(entry.getKey())
                    .append(", Quantity=").append(entry.getValue())
                    .append("), ");
        }

        // Remove the last ", " if there are products
        if (!products.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}

