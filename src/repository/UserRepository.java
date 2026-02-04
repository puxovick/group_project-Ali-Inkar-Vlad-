package repository;
import entity.User;
import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserRepository {
    public User login(String username, String password) throws Exception {
        String sql = """
            SELECT id, name, role
            FROM users
            WHERE username = ? AND password = ?
        """;
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("role")
            );
        }
        return null;
    }
}