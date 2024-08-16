/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseDatabase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StaffLoginDAO {
    private Connection connect() {
        // Update with your database details
        String url = "jdbc:mysql://localhost:3306/dineease";
        String user = "root";
        String password = "new_password";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean validateLogin(String username, String password) {
        String query = "SELECT COUNT(1) FROM staff WHERE username = ? AND password = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean resetPassword(String username, String phoneNumber, String newPassword) {
        String query = "UPDATE staff SET password = ? WHERE username = ? AND phone_number = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.setString(3, phoneNumber);
            
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
