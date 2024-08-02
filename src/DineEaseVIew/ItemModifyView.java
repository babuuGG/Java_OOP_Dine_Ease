/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseVIew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ItemModifyView {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JTextField searchField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton searchButton, refreshButton, insertButton, updateButton, deleteButton;

    public ItemModifyView() {
        prepareGUI();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getInsertButton() {
        return insertButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Showing all items");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);
        mainFrame.getContentPane().setBackground(new Color(2, 156, 255));
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("Dine-Ease:Your Virtual Manager", JLabel.CENTER);
        headerLabel.setFont(new Font(null, Font.BOLD, 25));
        headerLabel.setForeground(Color.black);

        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBackground(new Color(0, 51, 102));

        searchButton = new JButton("Search");
        searchField = new JTextField(20);
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        controlPanel.add(searchPanel, BorderLayout.NORTH);

        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 51, 102));
        refreshButton.setForeground(Color.black);
        controlPanel.add(refreshButton, BorderLayout.SOUTH);

        insertButton = new JButton("Insert");
        insertButton.setBackground(new Color(0, 51, 102));
        insertButton.setForeground(Color.black);

        updateButton = new JButton("Update");
        updateButton.setBackground(new Color(0, 51, 102));
        updateButton.setForeground(Color.black);

        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(0, 51, 102));
        deleteButton.setForeground(Color.black);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 5, 5));
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        controlPanel.add(buttonPanel, BorderLayout.EAST);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        controlPanel.add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);

        JMenu backMenu = new JMenu("Back");
        menuBar.add(backMenu);

        JMenu exitMenu = new JMenu("Exit");
        menuBar.add(exitMenu);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenu.add(aboutMenuItem);

        JMenuItem backMenuItem = new JMenuItem("Back");
        backMenu.add(backMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenu.add(exitMenuItem);

        mainFrame.add(headerLabel, BorderLayout.NORTH);
        mainFrame.add(controlPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }
}

