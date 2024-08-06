/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseDatabase;

import DIneEaseModel.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffInfoDAO {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/dineease";
        String username = "root";
        String password = "new_password";
        return DriverManager.getConnection(url, username, password);
    }

    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getInt("s_id"),
                    rs.getString("s_username"),
                    rs.getString("s_password"),
                    rs.getString("s_address"),
                    rs.getLong("s_contact"),
                    rs.getString("s_email")
                );
                staffList.add(staff);
            }
        }
        return staffList;
    }

    public void insertStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO Staff (s_username, s_password, s_address, s_contact, s_email) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, staff.getUsername());
            pst.setString(2, staff.getPassword());
            pst.setString(3, staff.getAddress());
            pst.setLong(4, staff.getContact());
            pst.setString(5, staff.getEmail());
            pst.executeUpdate();
        }
    }

    public void updateStaff(Staff staff) throws SQLException {
        String sql = "UPDATE Staff SET s_username = ?, s_password = ?, s_address = ?, s_contact = ?, s_email = ? WHERE s_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, staff.getUsername());
            pst.setString(2, staff.getPassword());
            pst.setString(3, staff.getAddress());
            pst.setLong(4, staff.getContact());
            pst.setString(5, staff.getEmail());
            pst.setInt(6, staff.getId());
            pst.executeUpdate();
        }
    }

    public void deleteStaff(int id) throws SQLException {
        String sql = "DELETE FROM Staff WHERE s_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    public List<Staff> searchStaff(String username) throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM Staff WHERE s_username LIKE ?";
        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, username + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getInt("s_id"),
                    rs.getString("s_username"),
                    rs.getString("s_password"),
                    rs.getString("s_address"),
                    rs.getLong("s_contact"),
                    rs.getString("s_email")
                );
                staffList.add(staff);
            }
        }
        return staffList;
    }
}
