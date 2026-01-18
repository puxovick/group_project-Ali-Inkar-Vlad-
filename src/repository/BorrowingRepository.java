package repository;

import config.DBConnection;

import java.sql.*;

public class BorrowingRepository {

    public void borrowBook(int userId, int bookId) throws Exception {
        String sql =
                "INSERT INTO borrowings(user_id, book_id, borrow_date) VALUES (?, ?, CURRENT_DATE)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        }
    }

    public void returnBook(int borrowingId) throws Exception {
        String sql =
                "UPDATE borrowings SET return_date = CURRENT_DATE WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, borrowingId);
            ps.executeUpdate();
        }
    }

    public void showBorrowedBooks() throws Exception {
        String sql =
                "SELECT u.name, bk.title, b.borrow_date, b.return_date " +
                        "FROM borrowings b " +
                        "JOIN users u ON b.user_id = u.id " +
                        "JOIN books bk ON b.book_id = bk.id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String status = rs.getDate("return_date") == null
                        ? "NOT RETURNED"
                        : "Returned";

                System.out.println(
                        rs.getString("name") + " borrowed \"" +
                                rs.getString("title") + "\" on " +
                                rs.getDate("borrow_date") +
                                " [" + status + "]"
                );
            }
        }
    }
}
