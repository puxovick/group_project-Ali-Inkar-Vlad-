package repository;
import config.DBConnection;
import entity.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CategoryRepository implements ICategoryRepository {
    @Override
    public void addCategory(String name) throws SQLException {
        String sql = "INSERT INTO categories(name) VALUES (?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }
    @Override
    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT id, name FROM categories ORDER BY id";
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }
        return categories;
    }
}
