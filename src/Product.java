public class Product {
    private int productID;
    private String productName;
    private int stock;
    private double price;
    private int supplierID;

    // Constructor
    public Product(int productID, String productName, int stock, double price, int supplierID) {
        this.productID = productID;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        this.supplierID = supplierID;
    }

    // Getters and setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    // toString method to display product information
    @Override
    public String toString() {
        return "Product [productID=" + productID + ", productName=" + productName + ", stock=" + stock + ", price=" + price + ", supplierID=" + supplierID + "]";
    }
}
