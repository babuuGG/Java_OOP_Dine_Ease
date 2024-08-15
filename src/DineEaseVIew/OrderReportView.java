/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseVIew;



import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OrderReportView extends JFrame {
    public JTable table;
    public JScrollPane scrollPane;
    public DefaultTableModel model;
    public JTextField searchField;
    public JButton searchButton;
    public JLabel enterDateLabel;
    public JFormattedTextField dateTextField;

    public OrderReportView() {
        setTitle("Order Report");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        enterDateLabel = new JLabel("Enter Date (yyyy-MM-dd):");
        dateTextField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        dateTextField.setColumns(10);

        searchPanel.add(enterDateLabel);
        searchPanel.add(dateTextField);
        searchPanel.add(searchButton);

        model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        String[] columnNames = {"Order Date", "Report ID", "Total Amount"};
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        Font columnFont = new Font("Arial", Font.BOLD, 16);
        table.getTableHeader().setFont(columnFont);

        Font rowFont = new Font("Arial", Font.PLAIN, 16);
        table.setFont(rowFont);
        table.setRowHeight(30);

        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}

