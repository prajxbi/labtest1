package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryManagerTest {

    private LibraryManager library;

    @BeforeEach
    void setUp() {
        library = new LibraryManager();
        library.addBook("B101", "Clean Code");
    }

    @Test
    void testAddAndSearchBook() {
        assertTrue(library.addBook("B102", "Design Patterns"));
        Book found = library.searchBook("B102");
        assertNotNull(found);
        assertEquals("Design Patterns", found.getTitle());
        assertTrue(found.isAvailable());
    }

    @Test
    void testSearchNonExistentBook() {
        assertNull(library.searchBook("B999"));
    }

    @Test
    void testDuplicateBookId() {
        assertFalse(library.addBook("B101", "Duplicate Title"));
        assertEquals("Clean Code", library.searchBook("B101").getTitle());
    }

    @Test
    void testIssueAvailableAndAlreadyIssuedBook() {
        // Issuing an available book succeeds
        assertTrue(library.issueBook("B101"));
        assertFalse(library.searchBook("B101").isAvailable());

        // Issuing the same already-issued book fails
        assertFalse(library.issueBook("B101"));
    }

    @Test
    void testReturnBookAndUpdateAvailability() {
        library.issueBook("B101");
        assertFalse(library.searchBook("B101").isAvailable());

        // Returning the issued book succeeds and restores availability
        assertTrue(library.returnBook("B101"));
        assertTrue(library.searchBook("B101").isAvailable());

        // Returning an already-available book fails
        assertFalse(library.returnBook("B101"));
    }
}