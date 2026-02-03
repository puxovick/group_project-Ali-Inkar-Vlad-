package repository;

import entity.BorrowingDetails;
import config.DBConnection;

import java.sql.*;

public class BorrowingRepository {

    public void borrowBook(int userId, int bookId) throws Exception {
        String sql = "INSERT INTO borrowings(user_id, book_id, borrow_date) VALUES (?, ?, CURRENT_DATE)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, bookId);
        ps.executeUpdate();
    }

    public void returnBook(int borrowingId) throws Exception {
        String sql = "UPDATE borrowings SET return_date = CURRENT_DATE WHERE id = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, borrowingId);
        ps.executeUpdate();
    }

    public void showAllBorrowed() throws Exception {
        String sql = """
            SELECT u.name, b.title, c.name AS category, br.borrow_date, br.return_date
            FROM borrowings br
            JOIN users u ON br.user_id = u.id
            JOIN books b ON br.book_id = b.id
            JOIN categories c ON b.category_id = c.id
        """;

        ResultSet rs = DBConnection.getConnection()
                .prepareStatement(sql)
                .executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getString("name") + " | " +
                            rs.getString("title") + " | " +
                            rs.getString("category"));
        }
    }

    public void showUserHistory(int userId) throws Exception {
        String sql = "SELECT id FROM borrowings WHERE user_id = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.println("Borrowing ID: " + rs.getInt("id"));
    }
}
