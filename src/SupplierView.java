import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SupplierView extends JFrame {

    private JTextField supplierIDField;
    private JTextField supplierNameField;
    private JTextField contactPhoneField;
    private final JComboBox<String> operationComboBox;
    private final JPanel panel = new JPanel();

    public SupplierView() {
        setTitle("Supplier View");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel.setLayout(new GridLayout(4, 2));

        JLabel operationLabel = new JLabel("Select Operation:");
        String[] operations = {"Show Suppliers", "Add Supplier", "Update Supplier", "Delete Supplier"};
        operationComboBox = new JComboBox<>(operations);
        panel.add(operationLabel);
        panel.add(operationComboBox);

        operationComboBox.addActionListener(e -> {
            String selectedOperation = (String) operationComboBox.getSelectedItem();
            assert selectedOperation != null;
            switch (selectedOperation) {
                case "Show Suppliers":
                    showSuppliers();
                    break;
                case "Add Supplier":
                    addSupplier();
                    break;
                case "Update Supplier":
                    updateSupplier();
                    break;
                case "Delete Supplier":
                    deleteSupplier();
                    break;
            }
        });

        add(panel);
    }

    private void showSuppliers() {
        panel.removeAll();
        setSize(600, 400);
        panel.setLayout(new BorderLayout());

        List<Supplier> supplierList = DataAccess.getAllSuppliers();

        String[] columnNames = {"Supplier ID", "Supplier Name", "Contact Phone"};
        Object[][] data = new Object[supplierList.size()][3];

        for (int i = 0; i < supplierList.size(); i++) {
            Supplier supplier = supplierList.get(i);
            data[i][0] = supplier.getSupplierID();
            data[i][1] = supplier.getSupplierName();
            data[i][2] = supplier.getContactPhone();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = getBackButton();
        panel.add(backButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void addSupplier() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(4, 2));

        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDField = new JTextField();
        panel.add(supplierIDLabel);
        panel.add(supplierIDField);

        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameField = new JTextField();
        panel.add(supplierNameLabel);
        panel.add(supplierNameField);

        JLabel contactPhoneLabel = new JLabel("Contact Phone:");
        contactPhoneField = new JTextField();
        panel.add(contactPhoneLabel);
        panel.add(contactPhoneField);

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
            // Add supplier
            int supplierID = Integer.parseInt(supplierIDField.getText());
            String supplierName = supplierNameField.getText();
            String contactPhone = contactPhoneField.getText();
            Supplier supplier = new Supplier(supplierID, supplierName, contactPhone);
            DataAccess.addSupplier(supplier);
            // Show operation selection view
            showOperationSelectionView();
        });
        return addButton;
    }

    private void updateSupplier() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(4, 2));

        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDField = new JTextField();
        panel.add(supplierIDLabel);
        panel.add(supplierIDField);

        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameField = new JTextField();
        panel.add(supplierNameLabel);
        panel.add(supplierNameField);

        JLabel contactPhoneLabel = new JLabel("Contact Phone:");
        contactPhoneField = new JTextField();
        panel.add(contactPhoneLabel);
        panel.add(contactPhoneField);

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
            // Update supplier
            int supplierID = Integer.parseInt(supplierIDField.getText());
            String supplierName = supplierNameField.getText();
            String contactPhone = contactPhoneField.getText();
            Supplier supplier = new Supplier(supplierID, supplierName, contactPhone);
            DataAccess.updateSupplier(supplier);
            // Show operation selection view
            showOperationSelectionView();
        });
        return updateButton;
    }

    private void deleteSupplier() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(2, 2));

        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDField = new JTextField();
        panel.add(supplierIDLabel);
        panel.add(supplierIDField);

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
            // Remove supplier
            int supplierID = Integer.parseInt(supplierIDField.getText());
            DataAccess.deleteSupplier(supplierID);
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
            SupplierView supplierView = new SupplierView(); // Open Supplier View
            supplierView.setVisible(true);
        });
    }
}

