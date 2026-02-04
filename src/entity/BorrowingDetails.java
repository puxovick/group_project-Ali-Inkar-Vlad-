package entity;
import java.sql.Date;
public class BorrowingDetails {
    private String user;
    private String book;
    private String category;
    private Date borrowDate;
    private Date returnDate;
    public BorrowingDetails(String user, String book,
                            String category, Date borrowDate, Date returnDate) {
        this.user = user;
        this.book = book;
        this.category = category;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    @Override
    public String toString() {
        return user + " borrowed '" + book + "' (" + category + ") on " +
                borrowDate + (returnDate == null ? " [NOT RETURNED]" : " [RETURNED]");
    }
}
