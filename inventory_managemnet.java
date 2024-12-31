package shadowfox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class InventoryItem {
    private String name;
    private int quantity;
    private double price;

    public InventoryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

public class InventoryManagementSystem extends JFrame {
    private ArrayList<InventoryItem> inventory;
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, quantityField, priceField;

    public InventoryManagementSystem() {
        inventory = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Item Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Item");
        JButton updateButton = new JButton("Update Item");
        JButton deleteButton = new JButton("Delete Item");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Table Setup
        String[] columnNames = {"Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        itemTable = new JTable(tableModel);

        // Action Listeners
        addButton.addActionListener(e -> addItem());
        updateButton.addActionListener(e -> updateItem());
        deleteButton.addActionListener(e -> deleteItem());

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(itemTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addItem() {
        try {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            InventoryItem newItem = new InventoryItem(name, quantity, price);
            inventory.add(newItem);
            tableModel.addRow(new Object[]{name, quantity, price});
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your entries.");
        }
    }

    private void updateItem() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());

                inventory.get(selectedRow).setName(name);
                inventory.get(selectedRow).setQuantity(quantity);
                inventory.get(selectedRow).setPrice(price);

                tableModel.setValueAt(name, selectedRow, 0);
                tableModel.setValueAt(quantity, selectedRow, 1);
                tableModel.setValueAt(price, selectedRow, 2);

                clearInputFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your entries.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to update");
        }
    }

    private void deleteItem() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow >= 0) {
            inventory.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete");
        }
    }

    private void clearInputFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryManagementSystem().setVisible(true);
        });
    }
}
