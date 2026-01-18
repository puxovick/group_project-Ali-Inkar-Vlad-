import controller.LibraryController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        LibraryController controller = new LibraryController();

        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1 - Borrow book");
            System.out.println("2 - Return book");
            System.out.println("3 - Show borrowed books");
            System.out.println("0 - Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    controller.borrowBook(userId, bookId);
                }
                case 2 -> {
                    System.out.print("Borrowing ID: ");
                    int borrowingId = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    controller.returnBook(borrowingId, bookId);
                }
                case 3 -> controller.showBorrowedBooks();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}

