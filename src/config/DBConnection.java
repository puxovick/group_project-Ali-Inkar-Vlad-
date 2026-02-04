package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static volatile Connection connection;
    private DBConnection() {}
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DBConnection.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/library_db",
                            "postgres",
                            "0000"
                    );
                }
            }
        }
        return connection;
    }
}