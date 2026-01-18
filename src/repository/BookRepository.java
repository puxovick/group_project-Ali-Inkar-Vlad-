package repository;

import config.DBConnection;
import entity.Book;

import java.sql.*;

public class BookRepository {

    public Book findById(int id) throws Exception {
        String sql =
                "SELECT id, title, available_copies FROM books WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("available_copies")
                );
            }
        }
        return null;
    }

    public void updateAvailableCopies(int id, int value) throws Exception {
        String sql =
                "UPDATE books SET available_copies = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, value);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}

