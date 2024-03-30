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
    public static Customer getCustomer(int customerID) {
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

}