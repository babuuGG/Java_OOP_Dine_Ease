/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseDatabase;

import DIneEaseModel.OrderReportModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderReportDAO {
    private Connection connection;

    public OrderReportDAO() throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/dineease";
        String username = "root";
        String password = "new_password";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

    public List<OrderReportModel> fetchAllOrders() throws SQLException {
        List<OrderReportModel> orders = new ArrayList<>();
        String query = "SELECT order_date, report_id, total_amount FROM OrderReport";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                orders.add(new OrderReportModel(
                    resultSet.getTimestamp("order_date"),
                    resultSet.getInt("report_id"),
                    resultSet.getDouble("total_amount")
                ));
            }
        }
        return orders;
    }

    public List<OrderReportModel> searchOrdersByDate(Date searchDate, String searchTerm) throws SQLException {
        List<OrderReportModel> orders = new ArrayList<>();
        String query = "SELECT order_date, report_id, total_amount FROM OrderReport WHERE DATE(order_date) = ? AND (report_id LIKE ? OR total_amount LIKE ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, searchDate);
            preparedStatement.setString(2, "%" + searchTerm + "%");
            preparedStatement.setString(3, "%" + searchTerm + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(new OrderReportModel(
                    resultSet.getTimestamp("order_date"),
                    resultSet.getInt("report_id"),
                    resultSet.getDouble("total_amount")
                ));
            }
        }
        return orders;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

