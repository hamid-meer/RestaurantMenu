import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TOPTOPRestaurantMenu extends JFrame implements ActionListener {

    JTextField nameField;
    JCheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;
    JButton orderButton;
    JTextArea resultArea;

    String[] items = {
            "Burger",
            "Pizza",
            "Pasta",
            "Sandwich",
            "Coca Cola",
            "7 Up",
            "Fanta",
            "Hamid's Special"
    };

    int[] prices = {120, 250, 180, 100, 70, 60, 60, 90};

    public TOPTOPRestaurantMenu() {

        setTitle("TOPTOP Restaurant Menu");
        setSize(400, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel top = new JPanel();
        top.add(new JLabel("Customer Name:"));
        nameField = new JTextField(15);
        top.add(nameField);
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(10, 1));
        center.add(new JLabel("Welcome to TOPTOP Restaurant"));
        center.add(new JLabel("Select Food / Drink Items"));

        cb1 = new JCheckBox("Burger - Rs.120");
        cb2 = new JCheckBox("Pizza - Rs.250");
        cb3 = new JCheckBox("Pasta - Rs.180");
        cb4 = new JCheckBox("Sandwich - Rs.100");
        cb5 = new JCheckBox("Coca Cola - Rs.70");
        cb6 = new JCheckBox("7 Up - Rs.60");
        cb7 = new JCheckBox("Fanta - Rs.60");
        cb8 = new JCheckBox("Hamid's Special - Rs.90");

        center.add(cb1);
        center.add(cb2);
        center.add(cb3);
        center.add(cb4);
        center.add(cb5);
        center.add(cb6);
        center.add(cb7);
        center.add(cb8);

        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

        orderButton = new JButton("Place Order");
        orderButton.addActionListener(this);
        bottom.add(orderButton, BorderLayout.NORTH);

        resultArea = new JTextArea(10, 25);
        resultArea.setEditable(false);
        bottom.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        add(bottom, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();

        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter your name");
            return;
        }

        StringBuilder selectedItems = new StringBuilder();
        int totalAmount = 0;

        if (cb1.isSelected()) {
            selectedItems.append(items[0]).append(", ");
            totalAmount += prices[0];
        }
        if (cb2.isSelected()) {
            selectedItems.append(items[1]).append(", ");
            totalAmount += prices[1];
        }
        if (cb3.isSelected()) {
            selectedItems.append(items[2]).append(", ");
            totalAmount += prices[2];
        }
        if (cb4.isSelected()) {
            selectedItems.append(items[3]).append(", ");
            totalAmount += prices[3];
        }
        if (cb5.isSelected()) {
            selectedItems.append(items[4]).append(", ");
            totalAmount += prices[4];
        }
        if (cb6.isSelected()) {
            selectedItems.append(items[5]).append(", ");
            totalAmount += prices[5];
        }
        if (cb7.isSelected()) {
            selectedItems.append(items[6]).append(", ");
            totalAmount += prices[6];
        }
        if (cb8.isSelected()) {
            selectedItems.append(items[7]).append(", ");
            totalAmount += prices[7];
        }

        if (selectedItems.length() == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one item");
            return;
        }

        // Remove last comma and space
        selectedItems.setLength(selectedItems.length() - 2);

        resultArea.setText("");
        resultArea.append("TOPTOP Restaurant\n");
        resultArea.append("------------------------\n");
        resultArea.append("Customer Name: " + name + "\n");
        resultArea.append("Ordered Items: " + selectedItems + "\n");
        resultArea.append("Total Amount: Rs." + totalAmount + "\n");
        resultArea.append("Thank You!");

        try {
            FileWriter fw = new FileWriter("toptop_orders.txt", true);
            fw.write(name + "," + selectedItems + "," + totalAmount + "\n");
            fw.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "File error");
        }
    }

    public static void main(String[] args) {
        new TOPTOPRestaurantMenu().setVisible(true);
    }
}
