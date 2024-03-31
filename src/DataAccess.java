import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class DataAccess {
    static final String DB_URL = "jdbc:mysql://localhost:3306/store";
    static final String USER = "root";
    static final String PASS = "Soco2003user#";

    // Add a customer to the database
    public static void addCustomer(Customer customer) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO Customers (customerID, customerName, email) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, customer.getCustomerID());
                preparedStatement.setString(2, customer.getCustomerName());
                preparedStatement.setString(3, customer.getEmail());
                preparedStatement.executeUpdate();
                System.out.println("Customer added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add customer.");
        }
    }

    // Read a customer from the database by customerID
    public static Customer getCustomerByID(int customerID) {
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM customers WHERE customerID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, customerID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    customer = new Customer(
                            resultSet.getInt("customerID"),
                            resultSet.getString("customerName"),
                            resultSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get customer.");
        }
        return customer;
    }

    // Get all customers from the database
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM customers";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Customer customer = new Customer(
                            resultSet.getInt("customerID"),
                            resultSet.getString("customerName"),
                            resultSet.getString("email")
                    );
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get all customers.");
        }
        return customers;
    }

    // Update a customer in the database
    public static void updateCustomer(Customer customer) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE customers SET customerName = ?, email = ? WHERE customerID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, customer.getCustomerName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.setInt(3, customer.getCustomerID());
                preparedStatement.executeUpdate();
                System.out.println("Customer updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update customer.");
        }
    }

    // Delete a customer from the database by customerID
    public static void deleteCustomer(int customerID) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "DELETE FROM customers WHERE customerID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, customerID);
                preparedStatement.executeUpdate();
                System.out.println("Customer deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete customer.");
        }
    }

    // Add a supplier to the database
    public static void addSupplier(Supplier supplier) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO suppliers (supplierID, supplierName, contactPhone) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, supplier.getSupplierID());
                preparedStatement.setString(2, supplier.getSupplierName());
                preparedStatement.setString(3, supplier.getContactPhone());
                preparedStatement.executeUpdate();
                System.out.println("Supplier added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add supplier.");
        }
    }

    // Get a supplier from the database by supplierID
    public static Supplier getSupplierByID(int supplierID) {
        Supplier supplier = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM suppliers WHERE supplierID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, supplierID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    supplier = new Supplier(
                            resultSet.getInt("supplierID"),
                            resultSet.getString("supplierName"),
                            resultSet.getString("contactPhone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get supplier.");
        }
        return supplier;
    }

    // Get all suppliers from the database
    public static List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM suppliers";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Supplier supplier = new Supplier(
                            resultSet.getInt("supplierID"),
                            resultSet.getString("supplierName"),
                            resultSet.getString("contactPhone")
                    );
                    suppliers.add(supplier);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get all suppliers.");
        }
        return suppliers;
    }

    // Update a supplier in the database
    public static void updateSupplier(Supplier supplier) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE suppliers SET supplierName = ?, contactPhone = ? WHERE supplierID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, supplier.getSupplierName());
                preparedStatement.setString(2, supplier.getContactPhone());
                preparedStatement.setInt(3, supplier.getSupplierID());
                preparedStatement.executeUpdate();
                System.out.println("Supplier updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update supplier.");
        }
    }

    // Delete a supplier from the database by supplierID
    public static void deleteSupplier(int supplierID) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "DELETE FROM suppliers WHERE supplierID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, supplierID);
                preparedStatement.executeUpdate();
                System.out.println("Supplier deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete supplier.");
        }
    }

}