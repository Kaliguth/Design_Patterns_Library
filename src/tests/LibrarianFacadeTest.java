// Librarian facade test

package tests;

import library.*;
import library.facade.LibrarianFacade;
import library.objects.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class LibrarianFacadeTest {
    // LibrarianFacade object
    private LibrarianFacade facade;
    // Library object
    private Library library;

    // Setup method to initiate before tests
    // Initializes library and facade
    @BeforeEach
    void setUp() {
        library = Library.getInstance();
        library.setBooks(new ArrayList<>());
        library.setMembers(new ArrayList<>());
        library.setLoans(new ArrayList<>());
        facade = LibrarianFacade.getInstance();
    }

    // Tests
    @Test
    void getInstance() {
        assertNotNull(facade,
                "Facade is null");
    }

    @Test
    void getBooks() {
        Book book = new Book("Title", "Author", "PublishDate");
        library.getBooks().add(book);

        assertEquals(1, facade.getBooks().size(),
                "Books ArrayList is empty or contains more than one book");
        assertTrue(facade.getBooks().contains(book),
                "Book was not added to the ArrayList");
    }

    @Test
    void getMembers() {
        Member member = new Member(1, "Name");
        library.getMembers().add(member);

        assertEquals(1, facade.getMembers().size(),
                "Members ArrayList is empty or contains more than one member");
        assertTrue(facade.getMembers().contains(member),
                "Member was not added to the ArrayList");
    }

    @Test
    void getLoans() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Name");
        Loan loan = new Loan(book, member);
        library.getLoans().add(loan);

        assertEquals(1, facade.getLoans().size(),
                "Loans ArrayList is empty or contains more than one loan");
        assertTrue(facade.getLoans().contains(loan),
                "Loan was not added to the ArrayList");
    }

    @Test
    void showLibraryStatus() {
        String status = library.status();

        assertEquals(status, facade.showLibraryStatus(),
                "Library status does not match");
    }

    @Test
    void createBook() {
        Book book = facade.createBook("Title", "Author", "PublishDate");

        assertNotNull(book,
                "Book is null");
        assertEquals("Title", book.getTitle(),
                "Book title does not match");
        assertEquals("Author", book.getAuthor(),
                "Book author does not match");
        assertEquals("PublishDate", book.getPublishDate(),
                "Book publish date does not match");
    }

    @Test
    void addBook() {
        Book book = new Book("Title", "Author", "PublishDate");

        assertTrue(facade.addBook(book),
                "Book was not added to the ArrayList");
        assertTrue(library.getBooks().contains(book),
                "Book was not found in the ArrayList");
        assertFalse(facade.addBook(null),
                "Null book was added to the ArrayList");
    }

    @Test
    void removeBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        library.getBooks().add(book);

        assertTrue(facade.removeBook("Title", "Author", "PublishDate"),
                "Book was not removed from the ArrayList");
        assertFalse(library.getBooks().contains(book),
                "Book is still in the ArrayList");
        assertFalse(facade.removeBook("Test", "Test", "Test"),
                "Removed non existing book");
    }

    @Test
    void createMember() {
        Member member = facade.createMember(1, "Name");

        assertNotNull(member,
                "Member is null");
        assertEquals(1, member.getId(),
                "Member ID does not match");
        assertEquals("Name", member.getName(),
                "Member name does not match");
    }

    @Test
    void registerMember() {
        Member member = new Member(1, "Name");

        assertTrue(facade.registerMember(member),
                "Member was not registered to the library");
        assertTrue(library.getMembers().contains(member),
                "Member was not found in the library");
        assertFalse(facade.registerMember(member),
                "Same member registered to the library twice");
        assertFalse(facade.registerMember(null),
                "Null member was added to the library");
    }

    @Test
    void removeMember() {
        Member member = new Member(1, "Name");
        library.getMembers().add(member);

        assertTrue(facade.removeMember(1, "Name"),
                "Member was not removed from the ArrayList");
        assertFalse(library.getMembers().contains(member),
                "Member is still in the ArrayList");
        assertFalse(facade.removeMember(2, "NonName"),
                "Removed non existing member");
    }

    @Test
    void createLoan() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Name");
        Loan loan = facade.createLoan(book, member);

        assertNotNull(loan,
                "Loan is null");
        assertEquals(book, loan.getBook(),
                "Loan book does not match");
        assertEquals(member, loan.getMember(),
                "Loan member does not match");
    }

    @Test
    void loanBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Name");
        library.getBooks().add(book);
        library.getMembers().add(member);

        assertTrue(facade.loanBook(book, member),
                "Book was not loaned");
        assertFalse(book.isAvailable(), "Book availability was not false after loan");
        assertEquals(1, library.getLoans().size(),
                "Loans ArrayList is empty or contains more than one loan");
        assertFalse(facade.loanBook(null, member),
                "Null book was loaned to the member");
        assertFalse(facade.loanBook(book, null),
                "Book was loaned to a null member");
        assertFalse(facade.loanBook(book, member),
                "Unavailable book loaned to the member");
    }

    @Test
    void returnBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Name");
        Loan loan = new Loan(book, member);
        library.getBooks().add(book);
        library.getMembers().add(member);
        library.getLoans().add(loan);
        member.getLoans().add(loan);
        book.setAvailability(false);

        assertTrue(facade.returnBook(book, member),
                "Book was not returned");
        assertTrue(book.isAvailable(),
                "Book availability was not true after return");
        assertEquals(0, library.getLoans().size(),
                "Library's loans ArrayList is not empty after return");
        assertEquals(0, member.getLoans().size(),
                "Member's loans ArrayList is not empty after return");
        assertFalse(facade.returnBook(null, member),
                "Null book was returned by the member");
        assertFalse(facade.returnBook(book, null),
                "Book was returned from null member");
        assertFalse(facade.returnBook(book, member),
                "Returned non existing book loan");
    }

}