import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class OrderView extends JFrame {

    private final JComboBox<String> operationComboBox;
    private final JPanel panel = new JPanel();

    public OrderView() {
        setTitle("Order View");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel.setLayout(new GridLayout(4, 2));

        JLabel operationLabel = new JLabel("Select Operation:");
        String[] operations = {"Add Order", "Update Order"};
        operationComboBox = new JComboBox<>(operations);
        panel.add(operationLabel);
        panel.add(operationComboBox);

        operationComboBox.addActionListener(e -> {
            String selectedOperation = (String) operationComboBox.getSelectedItem();
            assert selectedOperation != null;
            switch (selectedOperation) {
                case "Add Order":
                    addOrder();
                    break;
                case "Update Order":
                    updateOrder();
                    break;
            }
        });

        add(panel);
    }

    private void addOrder() {
        setSize(600, 400);
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(0, 2));

        JLabel customerIDLabel = new JLabel("Customer ID:");
        JTextField customerIDField = new JTextField();
        panel.add(customerIDLabel);
        panel.add(customerIDField);

        JLabel paymentIDLabel = new JLabel("Payment ID:");
        JTextField paymentIDField = new JTextField();
        panel.add(paymentIDLabel);
        panel.add(paymentIDField);

        JButton addButton = new JButton("Add Order");
        panel.add(addButton);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> showOperationSelectionView());
        panel.add(backButton);



        Map<JTextField, JTextField> productFields = new HashMap<>();
        JTextField productIDField = new JTextField();
        JTextField quantityField = new JTextField();
        productFields.put(productIDField, quantityField);
        panel.add(new JLabel("Product ID:"));
        panel.add(productIDField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        JButton addProductButton = new JButton("Add Product");
        panel.add(addProductButton);

        addProductButton.addActionListener(e -> {
            JTextField newProductIDField = new JTextField();
            JTextField newQuantityField = new JTextField();
            productFields.put(newProductIDField, newQuantityField);
            panel.remove(addProductButton);
            panel.add(new JLabel("Product ID:"));
            panel.add(newProductIDField);
            panel.add(new JLabel("Quantity:"));
            panel.add(newQuantityField);
            panel.add(addProductButton);
            revalidate();
            repaint();
        });

        addButton.addActionListener(e -> {
            // Get customer ID
            int customerID = Integer.parseInt(customerIDField.getText());
            int paymentID = Integer.parseInt(paymentIDField.getText());
            // Create order
            Order order = new Order(0, customerID, LocalDateTime.now(), 0, paymentID);
            // Add products
            for (Map.Entry<JTextField, JTextField> entry : productFields.entrySet()) {
                int productID = Integer.parseInt(entry.getKey().getText());
                int quantity = Integer.parseInt(entry.getValue().getText());
                order.addProduct(productID, quantity);
            }
            updateTotal(order);
            // Add order to database
            DataAccess.addOrder(order);
            // Show operation selection view
            showOperationSelectionView();
        });

        revalidate(); // Refresh the layout
        repaint(); // Repaint the component
    }

    private void updateOrder() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(0, 2));

        JLabel orderIDLabel = new JLabel("Order ID:");
        JTextField orderIDField = new JTextField();
        panel.add(orderIDLabel);
        panel.add(orderIDField);

        JButton getOrderDetailsButton = new JButton("Get Order Details");
        panel.add(getOrderDetailsButton);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> showOperationSelectionView());
        panel.add(backButton);

        getOrderDetailsButton.addActionListener(e -> {
            int orderID = Integer.parseInt(orderIDField.getText());
            Order order = DataAccess.getOrderByID(orderID);
            panel.removeAll(); // Clear previous components
            panel.setLayout(new GridLayout(0, 2));
            if (order != null) {
                // Fill the text fields with order details
                panel.add(orderIDLabel);
                panel.add(orderIDField);

                JLabel customerIDLabel = new JLabel("Customer ID:");
                JTextField customerIDField = new JTextField();
                panel.add(customerIDLabel);
                panel.add(customerIDField);

                JLabel paymentIDLabel = new JLabel("Payment ID:");
                JTextField paymentIDField = new JTextField();
                panel.add(paymentIDLabel);
                panel.add(paymentIDField);

                JButton updateButton = new JButton("Update Order");
                panel.add(updateButton);

                panel.add(backButton);

                orderIDField.setText(String.valueOf(orderID));
                orderIDField.setEditable(false);
                customerIDField.setText(String.valueOf(order.getCustomerID()));
                paymentIDField.setText(String.valueOf(order.getPaymentID()));

                // Get products for the order from the database
                Map<Integer, Integer> products = DataAccess.getOrderProductsByID(orderID);

                // Create product fields dynamically based on the retrieved products
                Map<JTextField, JTextField> productFields = new HashMap<>();
                for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
                    JTextField productIDField = new JTextField(String.valueOf(entry.getKey()));
                    JTextField quantityField = new JTextField(String.valueOf(entry.getValue()));
                    productFields.put(productIDField, quantityField);
                    panel.add(new JLabel("Product ID:"));
                    panel.add(productIDField);
                    panel.add(new JLabel("Quantity:"));
                    panel.add(quantityField);
                }

                // Add a button to dynamically add more products
                JButton addProductButton = new JButton("Add Product");
                panel.add(addProductButton);

                addProductButton.addActionListener(ae -> {
                    JTextField newProductIDField = new JTextField();
                    JTextField newQuantityField = new JTextField();
                    productFields.put(newProductIDField, newQuantityField);
                    panel.remove(addProductButton);
                    panel.add(new JLabel("Product ID:"));
                    panel.add(newProductIDField);
                    panel.add(new JLabel("Quantity:"));
                    panel.add(newQuantityField);
                    panel.add(addProductButton);
                    revalidate();
                    repaint();
                });

                updateButton.addActionListener(ae -> {
                    // Create order
                    Order updatedOrder = new Order(orderID, Integer.parseInt(customerIDField.getText()), order.getTimestamp(), 0, Integer.parseInt(paymentIDField.getText()));
                    boolean someQuantity = false;
                    // Add or update products
                    for (Map.Entry<JTextField, JTextField> entry : productFields.entrySet()) {
                        int productID = Integer.parseInt(entry.getKey().getText());
                        int quantity = Integer.parseInt(entry.getValue().getText());
                        if(quantity > 0) {
                            updatedOrder.addProduct(productID, quantity);
                            someQuantity = true;
                        }
                        if (!someQuantity) {
                            DataAccess.deleteOrder(orderID);
                            showOperationSelectionView();
                        }
                    }
                    // Update order in database
                    updateTotal(updatedOrder);
                    DataAccess.updateOrder(updatedOrder);
                    // Show operation selection view
                    showOperationSelectionView();
                });

                revalidate(); // Refresh the layout
                repaint(); // Repaint the component
            } else {
                JOptionPane.showMessageDialog(this, "Order ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        revalidate(); // Refresh the layout
        repaint(); // Repaint the component
    }

    public void updateTotal(Order order) {
        order.setTotal(DataAccess.getTotal(order.getProducts()));
    }

    private void showOperationSelectionView() {
        panel.removeAll();
        setSize(300, 200);
        panel.setLayout(new GridLayout(4, 2));
        JLabel operationLabel = new JLabel("Select Operation:");
        panel.add(operationLabel);
        panel.add(operationComboBox);
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderView orderView = new OrderView(); // Open Order View
            orderView.setVisible(true);
        });
    }
}


