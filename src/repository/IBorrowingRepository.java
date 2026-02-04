package repository;
import entity.BorrowingDetails;
import java.sql.SQLException;
import java.util.List;
public interface IBorrowingRepository {
    void borrowBook(int userId, int bookId) throws SQLException;
    void returnBook(int borrowingId) throws SQLException;
    List<BorrowingDetails> getAllBorrowed() throws SQLException;
    List<BorrowingDetails> getUserHistory(int userId) throws SQLException;
}