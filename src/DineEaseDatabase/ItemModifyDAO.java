package DineEaseDatabase;
import DIneEaseModel.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemModifyDAO {

    private final String url = "jdbc:mysql://localhost:3306/dineease";
    private final String username = "root";
    private final String password = "new_password";

    public ItemModifyDAO() {
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
                items.add(new Item(
                        rs.getInt("f_id"),
                        rs.getString("f_name"),
                        rs.getDouble("f_prize")
                ));
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
                    items.add(new Item(
                            rs.getInt("f_id"),
                            rs.getString("f_name"),
                            rs.getDouble("f_prize")
                    ));
                }
            }
        }

        return items;
    }

    public void insertItem(Item item) throws SQLException {
        String sql = "INSERT INTO Food (f_id, f_name, f_prize) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, item.getId());
            pst.setString(2, item.getName());
            pst.setDouble(3, item.getPrice());
            pst.executeUpdate();
        }
    }

    public void updateItem(Item item) throws SQLException {
        String sql = "UPDATE Food SET f_name = ?, f_prize = ? WHERE f_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, item.getName());
            pst.setDouble(2, item.getPrice());
            pst.setInt(3, item.getId());
            pst.executeUpdate();
        }
    }

    public void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM Food WHERE f_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}
