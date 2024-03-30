import javax.swing.*;

public class CustomerView extends JFrame {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerView customerView = new CustomerView(); // Open Job View
            customerView.setVisible(true);
        });
    }
}
