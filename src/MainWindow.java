import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Store Management System");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a JLabel for the title
        JLabel titleLabel = new JLabel("Store Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set a large font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text

        // Buttons to open different views
        JButton openCustomerViewButton = new JButton("Open Customer View");
        openCustomerViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerView customerView = new CustomerView(); // Open Customer View
                customerView.setVisible(true);
            }
        });

        JButton openSupplierViewButton = new JButton("Open Supplier View");
        openSupplierViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierView supplierView = new SupplierView(); // Open Supplier View
                supplierView.setVisible(true);
            }
        });

        JButton openProductViewButton = new JButton("Open Product View");
        openProductViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductView productView = new ProductView(); // Open Product View
                productView.setVisible(true);
            }
        });

        JButton openPaymentViewButton = new JButton("Open Payment View");
        openPaymentViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentView paymentView = new PaymentView(); // Open Payment View
                paymentView.setVisible(true);
            }
        });

        JButton openOrderViewButton = new JButton("Open Order View");
        openOrderViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderView orderView = new OrderView(); // Open Order View
                orderView.setVisible(true);
            }
        });

        // Buttons to open different views
        JButton openMonthlySalesReportButton = new JButton("Monthly Sales Report");
        openMonthlySalesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthlySalesReportView monthlySalesReportView = new MonthlySalesReportView(); // Open Customer View
                monthlySalesReportView.setVisible(true);
            }
        });

        JButton openSalesByProductButton = new JButton("Sales by Product");


        JButton openSalesByCustomerButton = new JButton("Sales by Customer");

        // Panel to hold the title and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for positioning
        panel.add(titleLabel, BorderLayout.NORTH); // Add the title label at the top
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to hold buttons
        buttonPanel.add(openCustomerViewButton);
        buttonPanel.add(openSupplierViewButton);
        buttonPanel.add(openProductViewButton);
        buttonPanel.add(openPaymentViewButton);
        buttonPanel.add(openOrderViewButton);
        panel.add(buttonPanel, BorderLayout.CENTER); // Add the buttons below the title

        // Panel to hold the sales reports title and buttons
        JPanel salesPanel = new JPanel(new BorderLayout());
        JLabel salesReportsTitleLabel = new JLabel("Sales Reports");
        salesReportsTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        salesReportsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salesPanel.add(salesReportsTitleLabel, BorderLayout.NORTH);
        JPanel salesButtonPanel = new JPanel(new FlowLayout());
        salesButtonPanel.add(openMonthlySalesReportButton);
        salesButtonPanel.add(openSalesByProductButton);
        salesButtonPanel.add(openSalesByCustomerButton);
        salesPanel.add(salesButtonPanel, BorderLayout.CENTER);
        panel.add(salesPanel, BorderLayout.SOUTH); // add the sales reports title and buttons

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
