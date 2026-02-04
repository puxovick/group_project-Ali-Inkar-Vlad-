package repository;
import entity.Category;
import java.sql.SQLException;
import java.util.List;
public interface ICategoryRepository {
    void addCategory(String name) throws SQLException;
    List<Category> getAllCategories() throws SQLException;
}