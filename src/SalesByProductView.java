import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class SalesByProductView extends JFrame {
    private final JTextField startDateField;
    private final JTextField endDateField;
    private final JCheckBox sortCheckBox;
    private final JCheckBox cutOffCheckBox;
    private final JCheckBox saveCheckBox;
    private final JTextArea resultArea;

    public SalesByProductView() {
        setTitle("Sales By Product");
        setSize(650, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField();
        panel.add(startDateLabel);
        panel.add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField();
        panel.add(endDateLabel);
        panel.add(endDateField);

        JLabel sortLabel = new JLabel("Sort Descending:");
        sortCheckBox = new JCheckBox();
        panel.add(sortLabel);
        panel.add(sortCheckBox);

        JLabel cutOffLabel = new JLabel("Cut Off (Display Top 5):");
        cutOffCheckBox = new JCheckBox();
        panel.add(cutOffLabel);
        panel.add(cutOffCheckBox);

        JLabel saveLabel = new JLabel("Save Report:");
        saveCheckBox = new JCheckBox();
        panel.add(saveLabel);
        panel.add(saveCheckBox);

        JButton generateButton = new JButton("Generate Report");
        panel.add(generateButton);

        resultArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        add(panel);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate startDate = LocalDate.parse(startDateField.getText());
                LocalDate endDate = LocalDate.parse(endDateField.getText());
                boolean sortDescending = sortCheckBox.isSelected();
                boolean cutOff = cutOffCheckBox.isSelected();
                boolean saveReport = saveCheckBox.isSelected();

                Map<Integer, Double> salesData = DataAccess.getTotalSalePerProduct(startDate, endDate);

                if (sortDescending) {
                    salesData = sortDescending(salesData);
                }
                if (cutOff) {
                    salesData = cutOff(salesData);
                }

                displayResults(salesData);

                if (saveReport) {
                    saveReportToFile(salesData);
                }
            }
        });
    }

    private Map<Integer, Double> sortDescending(Map<Integer, Double> salesData) {
        // Create a list from the map entries
        ArrayList<Map.Entry<Integer, Double>> entryList = new ArrayList<>(salesData.entrySet());

        // Sort the list in descending order based on sales
        entryList.sort(new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return Double.compare(o2.getValue(), o1.getValue()); // Descending order
            }
        });

        // Convert the sorted list back to a map
        Map<Integer, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private Map<Integer, Double> cutOff(Map<Integer, Double> salesData) {
        // Create a list from the map entries
        ArrayList<Map.Entry<Integer, Double>> entryList = new ArrayList<>(salesData.entrySet());

        // Sort the list in descending order based on sales
        entryList.sort(new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return Double.compare(o2.getValue(), o1.getValue()); // Descending order
            }
        });

        // Keep only top 5 entries
        ArrayList<Map.Entry<Integer, Double>> topEntries = new ArrayList<>(entryList.subList(0, Math.min(entryList.size(), 5)));

        // Convert the top entries list back to a map
        Map<Integer, Double> topMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> entry : topEntries) {
            topMap.put(entry.getKey(), entry.getValue());
        }

        return topMap;
    }

    private void saveReportToFile(Map<Integer, Double> salesData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sales_by_product_report.txt"))) {
            writer.write("---- Total Sales per Product from " + startDateField.getText() + " to " + endDateField.getText() + " ----\n");
            for (Map.Entry<Integer, Double> entry : salesData.entrySet()) {
                writer.write("Product ID: " + entry.getKey() + ", Total Sales: $" + entry.getValue() + "\n");
            }
            JOptionPane.showMessageDialog(this, "Report saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayResults(Map<Integer, Double> salesData) {
        resultArea.setText("");
        for (Map.Entry<Integer, Double> entry : salesData.entrySet()) {
            Product product = DataAccess.getProductByID(entry.getKey());
            resultArea.append(product.getProductName() + " with Product ID: " + entry.getKey() + ", Total Sales: $" + entry.getValue() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new SalesByProductView().setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
