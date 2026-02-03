package entity;

public class Book {
    private int id;
    private String title;
    private int availableCopies;

    public Book(int id, String title, int availableCopies) {
        this.id = id;
        this.title = title;
        this.availableCopies = availableCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}

