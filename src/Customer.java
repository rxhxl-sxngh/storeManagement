public class Customer {
    private int customerID;
    private String customerName;
    private String email;

    // Constructor
    public Customer(int customerID, String customerName, String email) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.email = email;
    }

    // Getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString method to display customer information
    @Override
    public String toString() {
        return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", email=" + email + "]";
    }
}

