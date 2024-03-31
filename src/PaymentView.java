import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.sql.Date;

public class PaymentView extends JFrame {

    private JTextField paymentIDField;
    private JTextField cardNameField;
    private JTextField cardNumberField;
    private JTextField CVVField;
    private JTextField expDateField;
    private final JComboBox<String> operationComboBox;
    private final JPanel panel = new JPanel();

    public PaymentView() {
        setTitle("Payment View");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel.setLayout(new GridLayout(5, 2));

        JLabel operationLabel = new JLabel("Select Operation:");
        String[] operations = {"Show Payments", "Add Payment", "Update Payment", "Delete Payment"};
        operationComboBox = new JComboBox<>(operations);
        panel.add(operationLabel);
        panel.add(operationComboBox);

        operationComboBox.addActionListener(e -> {
            String selectedOperation = (String) operationComboBox.getSelectedItem();
            assert selectedOperation != null;
            switch (selectedOperation) {
                case "Show Payments":
                    showPayments();
                    break;
                case "Add Payment":
                    addPayment();
                    break;
                case "Update Payment":
                    updatePayment();
                    break;
                case "Delete Payment":
                    deletePayment();
                    break;
            }
        });

        add(panel);
    }

    private void showPayments() {
        panel.removeAll();
        setSize(600, 400);
        panel.setLayout(new BorderLayout());

        List<Payment> paymentList = DataAccess.getAllPayments();

        String[] columnNames = {"Payment ID", "Card Name", "Card Number", "CVV", "Exp. Date"};
        Object[][] data = new Object[paymentList.size()][5];

        for (int i = 0; i < paymentList.size(); i++) {
            Payment payment = paymentList.get(i);
            data[i][0] = payment.getPaymentID();
            data[i][1] = payment.getCardName();
            data[i][2] = payment.getCardNumber();
            data[i][3] = payment.getCVV();
            data[i][4] = payment.getExpDate();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = getBackButton();
        panel.add(backButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void addPayment() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(6, 2));

        JLabel paymentIDLabel = new JLabel("Payment ID:");
        paymentIDField = new JTextField();
        panel.add(paymentIDLabel);
        panel.add(paymentIDField);

        JLabel cardNameLabel = new JLabel("Card Name:");
        cardNameField = new JTextField();
        panel.add(cardNameLabel);
        panel.add(cardNameField);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField();
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);

        JLabel CVVLabel = new JLabel("CVV:");
        CVVField = new JTextField();
        panel.add(CVVLabel);
        panel.add(CVVField);

        JLabel expDateLabel = new JLabel("Exp. Date:");
        expDateField = new JTextField();
        panel.add(expDateLabel);
        panel.add(expDateField);

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
            // Add payment
            int paymentID = Integer.parseInt(paymentIDField.getText());
            String cardName = cardNameField.getText();
            String cardNumber = cardNumberField.getText();
            String CVV = CVVField.getText();
            String expDateString = expDateField.getText(); // Assuming date format is string
            Date expDate = null;
            try {
                expDate = Date.valueOf(expDateString);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Please use yyyy-MM-dd.");
                return;
            }
            Payment payment = new Payment(paymentID, cardName, cardNumber, CVV, expDate);
            DataAccess.addPayment(payment);
            // Show operation selection view
            showOperationSelectionView();
        });
        return addButton;
    }

    private void updatePayment() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(6, 2));

        JLabel paymentIDLabel = new JLabel("Payment ID:");
        paymentIDField = new JTextField();
        panel.add(paymentIDLabel);
        panel.add(paymentIDField);

        JLabel cardNameLabel = new JLabel("Card Name:");
        cardNameField = new JTextField();
        panel.add(cardNameLabel);
        panel.add(cardNameField);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField();
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);

        JLabel CVVLabel = new JLabel("CVV:");
        CVVField = new JTextField();
        panel.add(CVVLabel);
        panel.add(CVVField);

        JLabel expDateLabel = new JLabel("Exp. Date:");
        expDateField = new JTextField();
        panel.add(expDateLabel);
        panel.add(expDateField);

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
            // Update payment
            int paymentID = Integer.parseInt(paymentIDField.getText());
            String cardName = cardNameField.getText();
            String cardNumber = cardNumberField.getText();
            String CVV = CVVField.getText();
            String expDateString = expDateField.getText(); // Assuming date format is string
            Date expDate = null;
            try {
                expDate = Date.valueOf(expDateString);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Please use yyyy-MM-dd.");
                return;
            }
            Payment payment = new Payment(paymentID, cardName, cardNumber, CVV, expDate);
            DataAccess.updatePayment(payment);
            // Show operation selection view
            showOperationSelectionView();
        });
        return updateButton;
    }

    private void deletePayment() {
        panel.removeAll(); // Clear previous components
        panel.setLayout(new GridLayout(2, 2));

        JLabel paymentIDLabel = new JLabel("Payment ID:");
        paymentIDField = new JTextField();
        panel.add(paymentIDLabel);
        panel.add(paymentIDField);

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
            // Remove payment
            int paymentID = Integer.parseInt(paymentIDField.getText());
            DataAccess.deletePayment(paymentID);
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
        setSize(300, 250);
        panel.setLayout(new GridLayout(5, 2));
        JLabel operationLabel = new JLabel("Select Operation:");
        panel.add(operationLabel);
        panel.add(operationComboBox);
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaymentView paymentView = new PaymentView(); // Open Payment View
            paymentView.setVisible(true);
        });
    }
}


