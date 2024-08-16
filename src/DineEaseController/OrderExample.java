package DineEaseController;

import DineEaseDatabase.OrderDAO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import DineEaseModel.OrderDAO;

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

    }     
}
