import controller.LibraryController;
import entity.User;
import repository.UserRepository;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        UserRepository userRepo = new UserRepository();
        LibraryController controller = new LibraryController();
        System.out.println("===== LOGIN =====");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        User currentUser = userRepo.login(username, password);
        if (currentUser == null) {
            System.out.println("❌ Invalid username or password");
            return;
        }
        System.out.println("✅ Logged in as " +
                currentUser.getName() + " (" + currentUser.getRole() + ")");
        int choice;
        do {
            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1 - Borrow book");
            System.out.println("2 - Return book");
            System.out.println("3 - Show all borrowed books");
            System.out.println("4 - Show my borrowing history");
            System.out.println("5 - Search books by category");
            System.out.println("6 - Add new book (ADMIN only)");
            System.out.println("7 - Show all books");
            System.out.println("8 - Add new category (ADMIN only)");
            System.out.println("9 - Show all categories");
            System.out.println("0 - Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Book ID: ");
                    controller.borrowBook(currentUser, sc.nextInt());
                }
                case 2 -> {
                    System.out.print("Borrowing ID: ");
                    controller.returnBook(currentUser, sc.nextInt());
                }
                case 3 -> controller.showAllBorrowedBooks();
                case 4 -> controller.showUserBorrowingHistory(currentUser);
                case 5 -> {
                    sc.nextLine();
                    System.out.print("Category: ");
                    controller.searchBooksByCategory(sc.nextLine());
                }
                case 6 -> {
                    sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Category ID: ");
                    controller.addNewBook(currentUser, title, author, sc.nextInt());
                }
                case 7 -> controller.showAllBooks();
                case 8 -> {
                    sc.nextLine();
                    System.out.print("Category Name: ");
                    controller.addCategory(currentUser, sc.nextLine());
                }
                case 9 -> controller.showAllCategories();
                case 0 -> System.out.println("Goodbye!");
            }
        } while (choice != 0);
    }
}
