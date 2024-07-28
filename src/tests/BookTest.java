// Book test

package tests;

import library.objects.Book;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    // Tests
    @Test
    void getTitle() {
        Book book = new Book("Title", "Author", "PublishDate");

        assertEquals("Title", book.getTitle(),
                "Book title does not match");
    }

    @Test
    void getAuthor() {
        Book book = new Book("Title", "Author", "PublishDate");

        assertEquals("Author", book.getAuthor(),
                "Book author does not match");
    }

    @Test
    void getPublishDate() {
        Book book = new Book("Title", "Author", "PublishDate");

        assertEquals("PublishDate", book.getPublishDate(),
                "Book publishDate does not match");
    }

    @Test
    void isAvailable() {
        Book book = new Book("Title", "Author", "PublishDate");

        assertTrue(book.isAvailable(),
                "Book was not initially available");
    }

    @Test
    void setAvailability() {
        Book book = new Book("Title", "Author", "PublishDate");
        book.setAvailability(false);

        assertFalse(book.isAvailable(),
                "Book was still available after change");
    }

    @Test
    void testClone() {
        Book originalBook = new Book("Title", "Author", "PublishDate");
        Book clonedBook = originalBook.clone();

        assertNotNull(clonedBook,
                "Cloned book is null");
        assertNotSame(originalBook, clonedBook,
                "Cloned book was not the same as original");
        assertEquals(originalBook.getTitle(), clonedBook.getTitle(),
                "Cloned book title does not match");
        assertEquals(originalBook.getAuthor(), clonedBook.getAuthor(),
                "Cloned book author does not match");
        assertEquals(originalBook.getPublishDate(), clonedBook.getPublishDate(),
                "Cloned book publishDate does not match");
        assertEquals(originalBook.isAvailable(), clonedBook.isAvailable(),
                "Cloned book availability does not match");
    }

    @Test
    void testEquals() {
        Book book1 = new Book("Title", "Author", "PublishDate");
        Book book2 = new Book("Title", "Author", "PublishDate");
        Book book3 = new Book("DifferentTitle", "Author", "PublishDate");

        // Same parameters
        assertTrue(book1.equals(book2));
        // Different title
        assertFalse(book1.equals(book3));
        // Null
        assertFalse(book1.equals(null));
        // Different class
        assertFalse(book1.equals(new String("String")));
    }

    @Test
    void testToString() {
        Book book = new Book("Title", "Author", "PublishDate");
        String result = "\"" + book.getTitle() + "\"" +
                "  -  Written by " + book.getAuthor() +
                "      Publish Date: " + book.getPublishDate() +
                "      AVAILABLE";

        assertEquals(result, book.toString(),
                "Available book toString does not match");

        book.setAvailability(false);
        result = "\"" + book.getTitle() + "\"" +
                "  -  Written by " + book.getAuthor() +
                "      Publish Date: " + book.getPublishDate() +
                "      LOANED";
        assertEquals(result, book.toString(),
                "Loaned book toString does not match");
    }

}