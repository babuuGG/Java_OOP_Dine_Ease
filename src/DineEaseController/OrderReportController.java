/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;




import DIneEaseModel.OrderReportModel;
import DineEaseDatabase.OrderReportDAO;
import DineEaseVIew.OrderReportView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderReportController {
    private OrderReportView view;
    private OrderReportDAO dao;

    public OrderReportController(OrderReportView view, OrderReportDAO dao) {
        this.view = view;
        this.dao = dao;

        // Add ActionListener to the search button
        this.view.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the date from the text field
                    Date searchDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(view.dateTextField.getText()).getTime());
                    performSearch(searchDate);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd.");
                } catch (SQLException sqlEx) {
                    JOptionPane.showMessageDialog(null, "Database error: " + sqlEx.getMessage());
                    sqlEx.printStackTrace(); // Log the exception
                }
            }
        });

        // Fetch and display all orders when the application starts
        try {
            fetchAllOrders();
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, "Database error: " + sqlEx.getMessage());
            sqlEx.printStackTrace(); // Log the exception
        }
    }

    private void fetchAllOrders() throws SQLException {
        List<OrderReportModel> orders = dao.fetchAllOrders();
        displayOrders(orders);
    }

    private void performSearch(Date searchDate) throws SQLException {
        String searchTerm = view.searchField.getText().trim();
        List<OrderReportModel> orders = dao.searchOrdersByDate(searchDate, searchTerm);
        displayOrders(orders);
    }

    private void displayOrders(List<OrderReportModel> orders) {
        view.model.setRowCount(0);
        for (OrderReportModel order : orders) {
            view.model.addRow(new Object[]{
                order.getOrderDate(),
                order.getReportId(),
                order.getTotalAmount()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                OrderReportView view = new OrderReportView();
                OrderReportDAO dao = new OrderReportDAO();
                new OrderReportController(view, dao);
                view.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Application error: " + e.getMessage());
                e.printStackTrace(); // Log the exception
            }
        });
    }
}
