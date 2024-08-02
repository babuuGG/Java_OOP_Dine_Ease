package DineEaseDatabase;

import DIneEaseModel.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemInfoDAO {

    private final String url = "jdbc:mysql://localhost:3306/dineease";
    private final String username = "root";
    private final String password = "new_password";

    public ItemInfoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Food";

        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("f_id"),
                        rs.getString("f_name"),
                        rs.getDouble("f_prize")
                );
                items.add(item);
            }
        }

        return items;
    }

    public List<Item> searchItems(String searchTerm) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Food WHERE f_name LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, searchTerm + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getInt("f_id"),
                            rs.getString("f_name"),
                            rs.getDouble("f_prize")
                    );
                    items.add(item);
                }
            }
        }

        return items;
    }
}
