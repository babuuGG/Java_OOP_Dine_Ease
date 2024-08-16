package DineEaseController;

import DineEaseDatabase.OrderDAO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class OrderExample {
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JLabel headerLabel;
    private JLabel tableNumberLabel; // New JLabel to display the table number
    private int selectedTableNumber; // New variable to hold the selected table number
    private boolean tableNumberEntered = false;
    private int currentTableNumber = 0; // Track the current table being processed
    private JTextField searchField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea orderTextArea;
    private JTable orderTable;
    private DefaultTableModel orderTableModel;

    private OrderDAO orderDAO; // Add an instance of OrderDAO

    public OrderExample() {
        orderDAO = new OrderDAO(); // Initialize the DAO
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderExample orderExample = new OrderExample();
        });
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Dine-Ease: Your Virtual Manager");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);
        mainFrame.getContentPane().setBackground(new Color(0, 153, 255));
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBackground(new Color(0, 51, 102));

        headerLabel = new JLabel("", JLabel.CENTER);
        headerLabel.setFont(new Font(null, Font.BOLD, 25));
        headerLabel.setForeground(Color.black);

        JButton searchButton = new JButton("Search");
        searchField = new JTextField(20);
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        controlPanel.add(searchPanel, BorderLayout.NORTH);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 51, 102));
        refreshButton.setForeground(Color.black);
        controlPanel.add(refreshButton, BorderLayout.SOUTH);

        // Create a panel for the right-hand side
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(new Color(220, 220, 220));
        orderPanel.setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500);
        splitPane.setLeftComponent(controlPanel);
        splitPane.setRightComponent(orderPanel);

        mainFrame.add(splitPane, BorderLayout.CENTER);

        // Create a new JTable instance
        table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        controlPanel.add(scrollPane, BorderLayout.CENTER);

        // Initialize orderTextArea
        orderTextArea = new JTextArea(10, 30);
        orderTextArea.setEditable(false);
        orderTextArea.setFont(new Font(null, Font.PLAIN, 14));

        // Create components for order placement
        JLabel orderLabel = new JLabel("Order Details");
        orderLabel.setFont(new Font(null, Font.BOLD, 20));
        orderLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the order panel
        orderPanel.add(orderLabel, BorderLayout.NORTH);
        orderPanel.add(orderTextArea, BorderLayout.CENTER);

        JButton tableSearchButton = new JButton("Table Status");
        JButton addToCartButton = new JButton("Place Order");
        JButton generateBillButton = new JButton("Generate Bill");
        JButton markAsPaidButton = new JButton("Mark as Paid");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(tableSearchButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(generateBillButton);
        buttonPanel.add(markAsPaidButton); // Add the button to the buttonPanel
        orderPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Create a new JTable for displaying orders
        orderTable = new JTable();
        orderTableModel = new DefaultTableModel();
        orderTableModel.addColumn("Food Name");
        orderTableModel.addColumn("Quantity");
        orderTableModel.addColumn("Total Price");

        // Set the model for the orderTable
        orderTable.setModel(orderTableModel);

        // Create a JScrollPane for the orderTable
        JScrollPane orderScrollPane = new JScrollPane(orderTable);

        // Add the JScrollPane to the orderPanel
        orderPanel.add(orderScrollPane, BorderLayout.CENTER);

        // Initialize the table number label
        tableNumberLabel = new JLabel("Table Number: ");
        tableNumberLabel.setFont(new Font(null, Font.BOLD, 20));
        tableNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        orderPanel.add(tableNumberLabel, BorderLayout.NORTH);

        // Set the font for the orderTable headers
        JTableHeader orderTableHeader = orderTable.getTableHeader();
        orderTableHeader.setFont(new Font(null, Font.BOLD, 18));

        // Set the font for the orderTable rows
        orderTable.setFont(new Font(null, Font.PLAIN, 16));
        orderTable.setRowHeight(30);

        // Set the renderer to center-align the content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        orderTable.setDefaultRenderer(Object.class, centerRenderer);

        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);

        JMenu backMenu = new JMenu("Back");
        menuBar.add(backMenu);

        JMenu exitMenu = new JMenu("Exit");
        menuBar.add(exitMenu);

        // Create "OK" button for finishing operations
        JButton okButton = new JButton("Finish Order");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the orderTableModel
                orderTableModel.setRowCount(0);

                // Clear the orderTextArea
                orderTextArea.setText("");

                // Reset operations for the current table
                currentTableNumber = 0;

                // Clear the table number label
                tableNumberLabel.setText("Table Number: ");

                // Display a message to indicate finishing operations
                JOptionPane.showMessageDialog(mainFrame, "Operations finished for the current table.", "Finish", JOptionPane.INFORMATION_MESSAGE);
                // Change the button text to "Add to Cart"
                addToCartButton.setText("Place Order");
            }
        });

        // Add the "OK" button to the buttonPanel
        buttonPanel.add(okButton);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        tableSearchButton.addActionListener(e -> {
            displayTableStatusButtons();
        });

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        refreshTable();
    }

    private void refreshTable() {
        // Fetch the data from the DAO
        Object[][] data = orderDAO.getFoodData();

        String[] columnNames = {"Food ID", "Food Name", "Price"};
        tableModel = new DefaultTableModel(data, columnNames);
        table.setModel(tableModel);
    }

    private void displayTableStatusButtons() {
        Set<Integer> usedTables = orderDAO.getUsedTables();

        // Update table status based on the fetched usedTables set
        StringBuilder statusMessage = new StringBuilder("Used Tables:\n");
        for (Integer tableNumber : usedTables) {
            statusMessage.append("Table ").append(tableNumber).append("\n");
        }

        // Display the table status message in the orderTextArea
        orderTextArea.setText(statusMessage.toString());
    }
}
