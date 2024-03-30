import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.List;

public class CustomerView extends JFrame {

    private JTextField customerIDField;
    private JTextField customerNameField;
    private JTextField emailField;
    private final JComboBox<String> operationComboBox;
    private final JPanel panel = new JPanel();

    public CustomerView() {
        setTitle("Customer View");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel.setLayout(new GridLayout(4, 2));

        JLabel operationLabel = new JLabel("Select Operation:");
        String[] operations = {"Show Customers", "Add Customer", "Update Customer", "Delete Customer"};
        operationComboBox = new JComboBox<>(operations);
        panel.add(operationLabel);
        panel.add(operationComboBox);

        operationComboBox.addActionListener(e -> {
            String selectedOperation = (String) operationComboBox.getSelectedItem();
            assert selectedOperation != null;
            switch (selectedOperation) {
                case "Show Customers":
                    showCustomers();
                    break;
                case "Add Customer":
                    addCustomer();
                    break;
                case "Update Customer":
                    updateCustomer();
                    break;
                case "Delete Customer":
                    deleteCustomer();
                    break;
            }
        });

        add(panel);
    }

    private void showCustomers() {
        panel.removeAll();
        setSize(600, 400);
        panel.setLayout(new BorderLayout());

        List<Customer> customerList = DataAccess.getAllCustomers();

        String[] columnNames = {"Customer ID", "Customer Name", "Email"};
        Object[][] data = new Object[customerList.size()][3];

        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            data[i][0] = customer.getCustomerID();
            data[i][1] = customer.getCustomerName();
            data[i][2] = customer.getEmail();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = getBackButton();
        panel.add(backButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void addCustomer() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(4, 2));

        JLabel customerIDLabel = new JLabel("Customer ID:");
        customerIDField = new JTextField();
        panel.add(customerIDLabel);
        panel.add(customerIDField);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField();
        panel.add(customerNameLabel);
        panel.add(customerNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        JButton addButton = getAddButton();
        panel.add(addButton);

        JButton backButton = getBackButton();
        panel.add(backButton);

        revalidate(); // Refresh the layout
        repaint(); // Repaint the component
    }

    private JButton getAddButton() {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            // Add customer
            int customerID = Integer.parseInt(customerIDField.getText());
            String customerName = customerNameField.getText();
            String email = emailField.getText();
            Customer customer = new Customer(customerID, customerName, email);
            DataAccess.addCustomer(customer);
            // Show operation selection view
            showOperationSelectionView();
        });
        return addButton;
    }

    private void updateCustomer() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(4, 2));

        JLabel customerIDLabel = new JLabel("Customer ID:");
        customerIDField = new JTextField();
        panel.add(customerIDLabel);
        panel.add(customerIDField);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField();
        panel.add(customerNameLabel);
        panel.add(customerNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        JButton updateButton = getUpdateButton();
        panel.add(updateButton);

        JButton backButton = getBackButton();
        panel.add(backButton);

        revalidate(); // Refresh the layout
        repaint(); // Repaint the component
    }

    private JButton getUpdateButton() {
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            // Update customer
            int customerID = Integer.parseInt(customerIDField.getText());
            String customerName = customerNameField.getText();
            String email = emailField.getText();
            Customer customer = new Customer(customerID, customerName, email);
            DataAccess.updateCustomer(customer);
            // Show operation selection view
            showOperationSelectionView();
        });
        return updateButton;
    }

    private void deleteCustomer() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(2, 2));

        JLabel customerIDLabel = new JLabel("Customer ID:");
        customerIDField = new JTextField();
        panel.add(customerIDLabel);
        panel.add(customerIDField);

        JButton removeButton = getRemoveButton();
        panel.add(removeButton);

        JButton backButton = getBackButton();
        panel.add(backButton);

        revalidate(); // Refresh the layout
        repaint(); // Repaint the component
    }

    private JButton getRemoveButton() {
        JButton removeButton = new JButton("Delete");
        removeButton.addActionListener(e -> {
            // Remove customer
            int customerID = Integer.parseInt(customerIDField.getText());
            DataAccess.deleteCustomer(customerID);
            // Show operation selection view
            showOperationSelectionView();
        });
        return removeButton;
    }

    private JButton getBackButton() {
        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> {
            // Show operation selection view
            showOperationSelectionView();
        });
        return backButton;
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
            CustomerView customerView = new CustomerView(); // Open Customer View
            customerView.setVisible(true);
        });
    }
}
