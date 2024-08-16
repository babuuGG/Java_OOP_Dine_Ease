package DineEaseVIew;

import DIneEaseModel.Staff;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StaffInfoView {
    public JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private JTextField searchField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton refreshButton;

    public StaffInfoView() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Staff Information");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);
        mainFrame.getContentPane().setBackground(new Color(0, 153, 255));
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("Staff Information", JLabel.CENTER);
        headerLabel.setFont(new Font(null, Font.BOLD, 25));
        headerLabel.setForeground(Color.white);

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBackground(new Color(0, 51, 102));

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        controlPanel.add(searchPanel, BorderLayout.NORTH);

        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 51, 102));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBorderPainted(false);
        refreshButton.setOpaque(true);
        controlPanel.add(refreshButton, BorderLayout.SOUTH);

        insertButton = new JButton("Insert");
        insertButton.setBackground(new Color(0, 51, 102));
        insertButton.setForeground(Color.WHITE);
        insertButton.setBorderPainted(false);
        insertButton.setOpaque(true);

        updateButton = new JButton("Update");
        updateButton.setBackground(new Color(0, 51, 102));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBorderPainted(false);
        updateButton.setOpaque(true);

        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(0, 51, 102));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBorderPainted(false);
        deleteButton.setOpaque(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 5, 5));
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        controlPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"ID", "Username", "Password", "Address", "Contact", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        controlPanel.add(scrollPane, BorderLayout.CENTER);

        mainFrame.add(headerLabel, BorderLayout.NORTH);
        mainFrame.add(controlPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    public void addInsertButtonListener(ActionListener listener) {
        insertButton.addActionListener(listener);
    }

    public void addUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public Object getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }

    public String getSearchTerm() {
        return searchField.getText();
    }

    public void updateTable(List<Staff> staffList) {
        tableModel.setRowCount(0); // Clear existing data
        for (Staff staff : staffList) {
            Object[] rowData = {
                staff.getId(),
                staff.getUsername(),
                staff.getPassword(),
                staff.getAddress(),
                staff.getContact(),
                staff.getEmail()
            };
            tableModel.addRow(rowData);
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }
}
