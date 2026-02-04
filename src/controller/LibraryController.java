
package controller;
import entity.User;
import entity.Book;
import entity.BorrowingDetails;
import entity.Category;
import repository.BookRepository;
import repository.BorrowingRepository;
import repository.CategoryRepository;
import repository.IBookRepository;
import repository.IBorrowingRepository;
import repository.ICategoryRepository;
import config.SecurityUtil;
import java.sql.SQLException;
import java.util.List;
public class LibraryController {
    private final IBookRepository bookRepo = new BookRepository();
    private final IBorrowingRepository borrowRepo = new BorrowingRepository();
    private final ICategoryRepository categoryRepo = new CategoryRepository();
    public void addCategory(User user, String name) throws SQLException {
        if (!SecurityUtil.hasAccess(user, "ADMIN")) {
            System.out.println("❌ Access Denied: ADMIN only.");
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ Category name cannot be empty.");
            return;
        }
        categoryRepo.addCategory(name);
        System.out.println("✅ Category added successfully.");
    }
    public void showAllCategories() throws SQLException {
        List<Category> categories = categoryRepo.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
        } else {
            System.out.println("===== Categories =====");
            categories.forEach(System.out::println);
        }
    }
    public void borrowBook(User user, int bookId) throws SQLException {
        if (bookId <= 0) {
            System.out.println("❌ Invalid Book ID.");
            return;
        }
        borrowRepo.borrowBook(user.getId(), bookId);
        System.out.println("✅ Book borrowed.");
    }
    public void returnBook(User user, int borrowingId) throws SQLException {
        if (borrowingId <= 0) {
            System.out.println("❌ Invalid Borrowing ID.");
            return;
        }
        borrowRepo.returnBook(borrowingId);
        System.out.println("✅ Book returned.");
    }
    public void showAllBorrowedBooks() throws SQLException {
        List<BorrowingDetails> details = borrowRepo.getAllBorrowed();
        if (details.isEmpty()) {
            System.out.println("No books are currently borrowed.");
        } else {
            details.forEach(System.out::println);
        }
    }
    public void showUserBorrowingHistory(User user) throws SQLException {
        List<BorrowingDetails> history = borrowRepo.getUserHistory(user.getId());
        if (history.isEmpty()) {
            System.out.println("You have no borrowing history.");
        } else {
            history.forEach(System.out::println);
        }
    }
    public void searchBooksByCategory(String category) throws SQLException {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("❌ Category name cannot be empty.");
            return;
        }
        List<Book> books = bookRepo.searchByCategory(category);
        if (books.isEmpty()) {
            System.out.println("No books found in category: " + category);
        } else {
            books.forEach(System.out::println);
        }
    }
    public void addNewBook(User user, String title, String author, int categoryId) throws SQLException {
        if (!SecurityUtil.hasAccess(user, "ADMIN")) {
            System.out.println("❌ Access Denied: ADMIN only.");
            return;
        }
        if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
            System.out.println("❌ Title and Author cannot be empty.");
            return;
        }
        bookRepo.addBook(title, author, categoryId);
        System.out.println("✅ Book added successfully.");
    }
    public void showAllBooks() throws SQLException {
        List<Book> books = bookRepo.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("The library is currently empty.");
        } else {
            System.out.println("===== All Books =====");
            books.forEach(System.out::println);
        }
    }
}