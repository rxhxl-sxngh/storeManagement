import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.LocalDate;

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

    // Add a product to the database
    public static void addProduct(Product product) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO products (productID, productName, stock, price, supplierID) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, product.getProductID());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setInt(3, product.getStock());
                preparedStatement.setDouble(4, product.getPrice());
                preparedStatement.setInt(5, product.getSupplierID());
                preparedStatement.executeUpdate();
                System.out.println("Product added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add product.");
        }
    }

    // Get a product from the database by productID
    public static Product getProductByID(int productID) {
        Product product = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM products WHERE productID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, productID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("productID"),
                            resultSet.getString("productName"),
                            resultSet.getInt("stock"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("supplierID")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get product.");
        }
        return product;
    }

    // Get all products from the database
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM products";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("productID"),
                            resultSet.getString("productName"),
                            resultSet.getInt("stock"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("supplierID")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get all products.");
        }
        return products;
    }

    // Update a product in the database
    public static void updateProduct(Product product) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE products SET productName = ?, stock = ?, price = ?, supplierID = ? WHERE productID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setInt(2, product.getStock());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setInt(4, product.getSupplierID());
                preparedStatement.setInt(5, product.getProductID());
                preparedStatement.executeUpdate();
                System.out.println("Product updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update product.");
        }
    }

    // Delete a product from the database by productID
    public static void deleteProduct(int productID) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "DELETE FROM products WHERE productID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, productID);
                preparedStatement.executeUpdate();
                System.out.println("Product deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete product.");
        }
    }

    // Add a payment to the database
    public static void addPayment(Payment payment) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO payments (paymentID, cardName, cardNumber, CVV, expDate) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, payment.getPaymentID());
                preparedStatement.setString(2, payment.getCardName());
                preparedStatement.setString(3, payment.getCardNumber());
                preparedStatement.setString(4, payment.getCVV());
                preparedStatement.setDate(5, payment.getExpDate());
                preparedStatement.executeUpdate();
                System.out.println("Payment added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add payment.");
        }
    }

    // Read a payment from the database by paymentID
    public static Payment getPaymentByID(int paymentID) {
        Payment payment = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM payments WHERE paymentID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, paymentID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    payment = new Payment(
                            resultSet.getInt("paymentID"),
                            resultSet.getString("cardName"),
                            resultSet.getString("cardNumber"),
                            resultSet.getString("CVV"),
                            resultSet.getDate("expDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get payment.");
        }
        return payment;
    }

    // Get all payments from the database
    public static List<Payment> getAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM payments";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Payment payment = new Payment(
                            resultSet.getInt("paymentID"),
                            resultSet.getString("cardName"),
                            resultSet.getString("cardNumber"),
                            resultSet.getString("CVV"),
                            resultSet.getDate("expDate")
                    );
                    paymentList.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get payments.");
        }
        return paymentList;
    }

    // Update a payment in the database
    public static void updatePayment(Payment payment) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE payments SET cardName = ?, cardNumber = ?, CVV = ?, expDate = ? WHERE paymentID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, payment.getCardName());
                preparedStatement.setString(2, payment.getCardNumber());
                preparedStatement.setString(3, payment.getCVV());
                preparedStatement.setDate(4, payment.getExpDate());
                preparedStatement.setInt(5, payment.getPaymentID());
                preparedStatement.executeUpdate();
                System.out.println("Payment updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update payment.");
        }
    }

    // Delete a payment from the database by paymentID
    public static void deletePayment(int paymentID) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "DELETE FROM payments WHERE paymentID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, paymentID);
                preparedStatement.executeUpdate();
                System.out.println("Payment deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete payment.");
        }
    }

    // Order SQL queries
    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders (customerID, timestamp, total, paymentID) VALUES (?, ?, ?, ?)";
    private static final String INSERT_ORDER_PRODUCT_QUERY = "INSERT INTO orderproducts (orderID, productID, quantity) VALUES (?, ?, ?)";
    private static final String SELECT_ORDER_BY_ID_QUERY = "SELECT * FROM orders WHERE orderID = ?";
    private static final String SELECT_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String UPDATE_ORDER_QUERY = "UPDATE orders SET customerID = ?, timestamp = ?, total = ?, paymentID = ? WHERE orderID = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE orderID = ?";
    private static final String DELETE_ORDER_PRODUCTS_QUERY = "DELETE FROM orderproducts WHERE orderID = ?";

    // Function to add an order to the database
    public static void addOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Insert order details
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getCustomerID());
            statement.setTimestamp(2, Timestamp.valueOf(order.getTimestamp()));
            statement.setDouble(3, order.getTotal());
            statement.setInt(4, order.getPaymentID());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                order.setOrderID(orderId);
                // Insert order products
                insertOrderProducts(connection, order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to insert order products into the database
    private static void insertOrderProducts(Connection connection, Order order) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_PRODUCT_QUERY);
        for (Map.Entry<Integer, Integer> entry : order.getProducts().entrySet()) {
            statement.setInt(1, order.getOrderID());
            statement.setInt(2, entry.getKey());
            statement.setInt(3, entry.getValue());
            statement.addBatch();
        }
        statement.executeBatch();
    }

    // Function to retrieve an order by its ID from the database
    public static Order getOrderByID(int orderID) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_ID_QUERY)) {
            statement.setInt(1, orderID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Function to retrieve all orders from the database
    public static Map<Integer, Order> getAllOrders() {
        Map<Integer, Order> orders = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ORDERS_QUERY)) {
            while (resultSet.next()) {
                Order order = mapResultSetToOrder(resultSet);
                orders.put(order.getOrderID(), order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Function to map a ResultSet to an Order object
    private static Order mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        int orderID = resultSet.getInt("orderID");
        int customerID = resultSet.getInt("customerID");
        LocalDateTime timestamp = resultSet.getTimestamp("timestamp").toLocalDateTime();
        double total = resultSet.getDouble("total");
        int paymentID = resultSet.getInt("paymentID");
        Order order = new Order(orderID, customerID, timestamp, total, paymentID);
        // Fetch order products
        fetchOrderProducts(order);
        return order;
    }

    // Function to fetch order products from the database
    private static void fetchOrderProducts(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderproducts WHERE orderID = ?")) {
            statement.setInt(1, order.getOrderID());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                int quantity = resultSet.getInt("quantity");
                order.addProduct(productID, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to fetch order products by orderID from the database
    public static Map<Integer, Integer> getOrderProductsByID(int orderID) {
        Map<Integer, Integer> products = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderproducts WHERE orderID = ?")) {
            statement.setInt(1, orderID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                int quantity = resultSet.getInt("quantity");
                products.put(productID, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Function to update an order in the database
    public static void updateOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_QUERY)) {
            statement.setInt(1, order.getCustomerID());
            statement.setTimestamp(2, Timestamp.valueOf(order.getTimestamp()));
            statement.setDouble(3, order.getTotal());
            statement.setInt(4, order.getPaymentID());
            statement.setInt(5, order.getOrderID());
            statement.executeUpdate();
            // Delete existing order products
            deleteOrderProducts(connection, order.getOrderID());
            // Insert updated order products
            insertOrderProducts(connection, order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getTotal(Map<Integer, Integer> products) {
        double total = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
                int productID = entry.getKey();
                int quantity = entry.getValue();
                // Query the database to get the price of the product
                String sql = "SELECT price FROM products WHERE productID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, productID);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            double price = rs.getDouble("price");
                            total += price * quantity;
                        } else {
                            System.out.println("Product with ID " + productID + " not found.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to calculate total.");
        }
        return total;
    }

    // Function to delete an order from the database
    public static void deleteOrder(int orderID) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Delete order products
            deleteOrderProducts(connection, orderID);
            // Delete order
            PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_QUERY);
            statement.setInt(1, orderID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to delete order products associated with an order
    private static void deleteOrderProducts(Connection connection, int orderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_PRODUCTS_QUERY);
        statement.setInt(1, orderID);
        statement.executeUpdate();
    }

    public static Map<Month, Double> getTotalSalePerMonth(LocalDate startDate, LocalDate endDate) {
        Map<Month, Double> totalSalePerMonth = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT MONTH(timestamp) AS month, SUM(total) AS total_sale " +
                    "FROM orders " +
                    "WHERE timestamp BETWEEN ? AND ? " +
                    "GROUP BY MONTH(timestamp)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, startDate.toString());
                stmt.setString(2, endDate.toString());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int monthValue = rs.getInt("month");
                    Month month = new Month(monthValue);
                    double totalSale = rs.getDouble("total_sale");
                    totalSalePerMonth.put(month, totalSale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSalePerMonth;
    }

    public static Map<Integer, Double> getTotalSalePerProduct(LocalDate startDate, LocalDate endDate) {
        Map<Integer, Double> totalSalePerProduct = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT op.productID, SUM(o.total) AS total_sale " +
                    "FROM orders o " +
                    "JOIN orderproducts op ON o.orderID = op.orderID " +
                    "WHERE o.timestamp BETWEEN ? AND ? " +
                    "GROUP BY op.productID";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, startDate.toString());
                stmt.setString(2, endDate.toString());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    double totalSale = rs.getDouble("total_sale");
                    totalSalePerProduct.put(productID, totalSale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSalePerProduct;
    }

    public static Map<Integer, Double> getTotalSalePerCustomer(LocalDate startDate, LocalDate endDate) {
        Map<Integer, Double> totalSalePerCustomer = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT customerID, SUM(total) AS total_sale " +
                    "FROM orders " +
                    "WHERE timestamp BETWEEN ? AND ? " +
                    "GROUP BY customerID";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDate(1, Date.valueOf(startDate));
                stmt.setDate(2, Date.valueOf(endDate));
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("customerID");
                    double totalSale = rs.getDouble("total_sale");
                    totalSalePerCustomer.put(customerID, totalSale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSalePerCustomer;
    }

}