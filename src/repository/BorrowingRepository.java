package repository;
import config.DBConnection;
import entity.BorrowingDetails;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BorrowingRepository implements IBorrowingRepository {
    @Override
    public void borrowBook(int userId, int bookId) throws SQLException {
        String sql = "INSERT INTO borrowings(user_id, book_id, borrow_date) VALUES (?, ?, CURRENT_DATE)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        }
    }
    @Override
    public void returnBook(int borrowingId) throws SQLException {
        String sql = "UPDATE borrowings SET return_date = CURRENT_DATE WHERE id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, borrowingId);
            ps.executeUpdate();
        }
    }
    @Override
    public List<BorrowingDetails> getAllBorrowed() throws SQLException {
        String sql = """
            SELECT u.name, b.title, c.name AS category,
                   br.borrow_date, br.return_date
            FROM borrowings br
            JOIN users u ON br.user_id = u.id
            JOIN books b ON br.book_id = b.id
            JOIN categories c ON b.category_id = c.id
        """;
        List<BorrowingDetails> details = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                details.add(new BorrowingDetails(
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                ));
            }
        }
        return details;
    }
    @Override
    public List<BorrowingDetails> getUserHistory(int userId) throws SQLException {
        String sql = """
            SELECT u.name, b.title, c.name AS category, br.borrow_date, br.return_date
            FROM borrowings br
            JOIN users u ON br.user_id = u.id
            JOIN books b ON br.book_id = b.id
            JOIN categories c ON b.category_id = c.id
            WHERE br.user_id = ?
        """;
        List<BorrowingDetails> details = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    details.add(new BorrowingDetails(
                            rs.getString("name"),
                            rs.getString("title"),
                            rs.getString("category"),
                            rs.getDate("borrow_date"),
                            rs.getDate("return_date")
                    ));
                }
            }
        }
        return details;
    }
    @Deprecated
    public void showAllBorrowed() throws Exception {
        getAllBorrowed().forEach(System.out::println);
    }
    @Deprecated
    public void showUserHistory(int userId) throws Exception {
        getUserHistory(userId).forEach(System.out::println);
    }
}