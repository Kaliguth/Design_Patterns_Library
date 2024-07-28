// Librarian test

package tests;

import library.*;
import library.facade.LibrarianFacade;
import library.objects.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    private Library library;
    private Librarian librarian;
    private LibrarianFacade facade;

    // Setup method to initiate before tests
    // Initializes librarian and facade
    @BeforeEach
    void setUp() {
        library = Library.getInstance();
        facade = LibrarianFacade.getInstance();
        librarian = new Librarian();
    }

    // Tests
    @Test
    void getBooks() {
        Book book = new Book("Title", "Author", "PublishDate");
        facade.getBooks().add(book);

        assertTrue(librarian.getBooks().contains(book),
                "Added book was not found in the ArrayList");
    }

    @Test
    void getMembers() {
        Member member = new Member(1, "Member1");
        facade.getMembers().add(member);

        assertTrue(librarian.getMembers().contains(member),
                "Added member was not found in the ArrayList");
    }

    @Test
    void getLoans() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Member1");
        Loan loan = new Loan(book, member);
        facade.getLoans().add(loan);

        assertTrue(librarian.getLoans().contains(loan),
                "Added loan was not found in the ArrayList");
    }

    @Test
    void createBook() {
        librarian.createBook("Title", "Author", "PublishDate");
        Book book = library.findBook("Title", "Author", "PublishDate");

        assertNotNull(book,
                "Book is null");
        assertEquals("Title", book.getTitle(),
                "Book was not found in the ArrayList");
    }

    @Test
    void addBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        librarian.addBook(book);

        assertTrue(facade.getBooks().contains(book),
                "Book was not added to the ArrayList");
    }

    @Test
    void removeBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        facade.getBooks().add(book);
        librarian.removeBook(book);

        assertFalse(facade.getBooks().contains(book),
                "Book was not removed from the ArrayList");
    }

    @Test
    void createMember() {
        librarian.createMember(2, "Member2");
        Member member = library.findMember(2, "Member2");

        assertNotNull(member,
                "Member is null");
        assertEquals("Member2", member.getName(),
                "Member name does not match");
    }

    @Test
    void registerMember() {
        Member member = new Member(3, "Member3");
        librarian.registerMember(member);

        assertTrue(facade.getMembers().contains(member),
                "Member was not added to the ArrayList");
    }

    @Test
    void removeMember() {
        Member member = new Member(4, "Member4");
        facade.getMembers().add(member);
        librarian.removeMember(member);

        assertFalse(facade.getMembers().contains(member),
                "Member was not removed from the ArrayList");
    }

    @Test
    void createLoan() {
        Book book = new Book("Title", "Author", "PublishDate");
        librarian.addBook(book);
        Member member = new Member(5, "Member5");
        librarian.registerMember(member);
        librarian.createLoan(book, member);
        Loan loan = library.findLoan(book, member);

        assertNotNull(loan,
                "Loan is null");
    }

    @Test
    void loanBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(6, "Member6");
        Loan loan = new Loan(book, member);
        facade.getLoans().add(loan);
        librarian.loanBook(book, member);

        assertTrue(facade.getLoans().contains(loan),
                "Book was not found in the loans ArrayList");
    }

    @Test
    void returnBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(7, "Member7");
        Loan loan = new Loan(book, member);
        facade.getLoans().add(loan);
        librarian.returnBook(book, member);

        assertFalse(facade.getLoans().contains(loan),
                "Book was still found in the loans ArrayList");
    }

    @Test
    void update() {
        // Test notification
        librarian.update("Test Notification");
    }

}
