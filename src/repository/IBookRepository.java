package repository;
import entity.Book;
import java.sql.SQLException;
import java.util.List;
public interface IBookRepository {
    void addBook(String title, String author, int categoryId) throws SQLException;
    List<Book> searchByCategory(String category) throws SQLException;
    List<Book> getAllBooks() throws SQLException;
}
