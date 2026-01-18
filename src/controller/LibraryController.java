package controller;

import entity.Book;
import repository.BookRepository;
import repository.BorrowingRepository;

public class LibraryController {

    private BookRepository bookRepo = new BookRepository();
    private BorrowingRepository borrowRepo = new BorrowingRepository();

    public void borrowBook(int userId, int bookId) throws Exception {
        Book book = bookRepo.findById(bookId);

        if (book == null) {
            System.out.println("Book not found");
            return;
        }

        if (book.getAvailableCopies() == 0) {
            System.out.println("No copies available");
            return;
        }

        borrowRepo.borrowBook(userId, bookId);
        bookRepo.updateAvailableCopies(bookId, book.getAvailableCopies() - 1);

        System.out.println("Book borrowed successfully");
    }

    public void returnBook(int borrowingId, int bookId) throws Exception {
        borrowRepo.returnBook(borrowingId);
        Book book = bookRepo.findById(bookId);

        bookRepo.updateAvailableCopies(bookId, book.getAvailableCopies() + 1);

        System.out.println("Book returned successfully");
    }

    public void showBorrowedBooks() throws Exception {
        borrowRepo.showBorrowedBooks();
    }
}
