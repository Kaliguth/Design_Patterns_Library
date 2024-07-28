// Library factory test

package tests;

import library.factory.LibraryFactory;
import library.objects.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryFactoryTest {
    // Tests
    @Test
    void createBook() {
        Book book = LibraryFactory.createBook("Title", "Author", "PublishDate");

        assertNotNull(book,
                "Book is null");
        assertEquals("Title", book.getTitle(),
                "Book title does not match");
        assertEquals("Author", book.getAuthor(),
                "Book author does not match");
        assertEquals("PublishDate", book.getPublishDate(),
                "Book publishDate does not match");

        // Null or empty inputs
        assertNull(LibraryFactory.createBook(null, "Author", "PublishDate"),
                "Book with null title created");
        assertNull(LibraryFactory.createBook("Title", null, "PublishDate"),
                "Book with null author created");
        assertNull(LibraryFactory.createBook("Title", "Author", null),
                "Book with null publishDate created");
        assertNull(LibraryFactory.createBook("", "Author", "PublishDate"),
                "Book with empty title created");
        assertNull(LibraryFactory.createBook("Title", "", "PublishDate"),
                "Book with empty author created");
        assertNull(LibraryFactory.createBook("Title", "Author", ""),
                "Book with empty publishDate created");
    }

    @Test
    void createMember() {
        Member member = LibraryFactory.createMember(1, "Member");

        assertNotNull(member,
                "Member is null");
        assertEquals(1, member.getId(),
                "Member ID does not match");
        assertEquals("Member", member.getName(),
                "Member name does not match");

        // Null, empty or invalid inputs
        assertNull(LibraryFactory.createMember(0, "Member"),
                "Member with ID 0 created");
        assertNull(LibraryFactory.createMember(-1, "Member"),
                "Member with negative number ID created");
        assertNull(LibraryFactory.createMember(1, null),
                "Member with null name created");
        assertNull(LibraryFactory.createMember(1, ""),
                "Member with empty name created");
    }

    @Test
    void createLoan() {
        Book book = LibraryFactory.createBook("Title", "Author", "PublishDate");
        Member member = LibraryFactory.createMember(1, "Member");
        Loan loan = LibraryFactory.createLoan(book, member);

        assertNotNull(loan,
                "Loan is null");
        assertEquals(book, loan.getBook(),
                "Book does not match");
        assertEquals(member, loan.getMember(),
                "Member does not match");

        // Null parameters
        assertNull(LibraryFactory.createLoan(null, member),
                "Loan with null book created");
        assertNull(LibraryFactory.createLoan(book, null),
                "Loan with null member created");
        assertNull(LibraryFactory.createLoan(null, null),
                "Loan with null book and member created");
    }

}