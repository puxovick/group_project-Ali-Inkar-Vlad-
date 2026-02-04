package repository;
import config.DBConnection;
import entity.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookRepository implements IBookRepository {
    @Override
    public void addBook(String title, String author, int categoryId) throws SQLException {
        String sql = "INSERT INTO books(title, author, category_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, categoryId);
            ps.executeUpdate();
        }
    }
    @Override
    public List<Book> searchByCategory(String category) throws SQLException {
        String sql = """
            SELECT b.id, b.title, b.author, c.name AS category
            FROM books b
            JOIN categories c ON b.category_id = c.id
            WHERE c.name = ?
        """;
        List<Book> books = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category")
                    ));
                }
            }
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        String sql = """
            SELECT b.id, b.title, b.author, c.name AS category
            FROM books b
            LEFT JOIN categories c ON b.category_id = c.id
            ORDER BY b.id
        """;
        List<Book> books = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category")
                ));
            }
        }
        return books;
    }
    @Deprecated
    public void showAllBooks() throws SQLException {
        getAllBooks().forEach(System.out::println);
    }
    @Deprecated
    public void searchByCategoryOld(String category) throws Exception {
        searchByCategory(category).forEach(book ->
                System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }
}

