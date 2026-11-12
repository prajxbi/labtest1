package com.example;


import java.util.HashMap;
import java.util.Map;

public class LibraryManager {
    private Map<String, Book> books = new HashMap<>();

    // Add a book with a unique book ID and title
    public boolean addBook(String bookId, String title) {
        if (bookId == null || bookId.trim().isEmpty() || title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID and title cannot be empty.");
        }
        if (books.containsKey(bookId)) {
            return false; // Duplicate ID rejected
        }
        books.put(bookId, new Book(bookId, title));
        return true;
    }

    // Search for a book using its ID
    public Book searchBook(String bookId) {
        return books.get(bookId);
    }

    // Issue an available book
    public boolean issueBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false; // Book not found or already issued
    }

    // Return an issued book
    public boolean returnBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false; // Book not found or was not issued
    }

    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        library.addBook("B101", "Clean Code");
        library.addBook("B102", "Effective Java");

        System.out.println("Search B101: " + library.searchBook("B101"));
        System.out.println("Issue B101: " + library.issueBook("B101"));
        System.out.println("Issue B101 again: " + library.issueBook("B101"));
        System.out.println("Return B101: " + library.returnBook("B101"));
        System.out.println("After Return: " + library.searchBook("B101"));
    }
}