package repository;

import config.DBConnection;

import java.sql.*;

public class BookRepository {

    public void addBook(String title, String author, int categoryId) throws Exception {
        String sql = "INSERT INTO books(title, author, category_id) VALUES (?, ?, ?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, author);
        ps.setInt(3, categoryId);
        ps.executeUpdate();
    }

    public void searchByCategory(String category) throws Exception {
        String sql = """
            SELECT b.title, b.author
            FROM books b
            JOIN categories c ON b.category_id = c.id
            WHERE c.name = ?
        """;
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, category);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("title") +
                    " by " + rs.getString("author"));
        }
    }
}
