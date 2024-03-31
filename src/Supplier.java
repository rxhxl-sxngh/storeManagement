public class Supplier {
    private int supplierID;
    private String supplierName;
    private String contactPhone;

    // Constructor
    public Supplier(int supplierID, String supplierName, String contactPhone) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactPhone = contactPhone;
    }

    // Getters and setters
    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    // toString method to display supplier information
    @Override
    public String toString() {
        return "Supplier [supplierID=" + supplierID + ", supplierName=" + supplierName + ", contactPhone=" + contactPhone + "]";
    }
}

