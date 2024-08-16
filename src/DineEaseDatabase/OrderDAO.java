/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseDatabase;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrderDAO {
    private String url = "jdbc:mysql://localhost:3306/dineease";
    private String username = "root";
    private String password = "new_password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public Object[][] getFoodData() {
        String sql = "SELECT * FROM Food";
        Object[][] data = new Object[100][3];
        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getInt("f_id");
                data[i][1] = rs.getString("f_name");
                data[i][2] = rs.getDouble("f_prize");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Set<Integer> getUsedTables() {
        String sql = "SELECT DISTINCT table_number FROM Orders";
        Set<Integer> usedTables = new HashSet<>();

        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int usedTableNumber = rs.getInt("table_number");
                usedTables.add(usedTableNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usedTables;
    }
}
