// Library Test

package tests;

import library.Library;
import library.objects.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class LibraryTest {
    // Library object
    private Library library;

    // Setup method to initiate before tests
    // Initializes library and it's lists
    @BeforeEach
    void setUp() {
        library = Library.getInstance();
        library.setBooks(new ArrayList<>());
        library.setMembers(new ArrayList<>());
        library.setLoans(new ArrayList<>());
    }

    // Tests:
    @Test
    void getInstance() {
        assertNotNull(library,
                "Library is null");
    }

    @Test
    void getBooks() {
        ArrayList<Book> books = library.getBooks();

        assertNotNull(books,
                "Books ArrayList is null");
        assertTrue(books.isEmpty(),
                "Books ArrayList is empty");
    }

    @Test
    void setBooks() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author", "PublishDate"));
        library.setBooks(books);

        assertEquals(1, library.getBooks().size(),
                "Books ArrayList is empty or contains more than one book");
        assertEquals("Title", library.getBooks().getFirst().getTitle(),
                "Books ArrayList first title doesn't match");
    }

    @Test
    void getMembers() {
        ArrayList<Member> members = library.getMembers();

        assertNotNull(members,
                "Members ArrayList is null");
        assertTrue(members.isEmpty(),
                "Members ArrayList is not empty");
    }

    @Test
    void setMembers() {
        ArrayList<Member> members = new ArrayList<>();
        members.add(new Member(1, "Name"));
        library.setMembers(members);

        assertEquals(1, library.getMembers().size(),
                "Members ArrayList is empty or contains more than one member");
        assertEquals("Name", library.getMembers().getFirst().getName(),
                "Members ArrayList first name doesn't match");
    }

    @Test
    void getLoans() {
        ArrayList<Loan> loans = library.getLoans();

        assertNotNull(loans,
                "Loans ArrayList is null");
        assertTrue(loans.isEmpty(),
                "Loans ArrayList is not empty");
    }

    @Test
    void setLoans() {
        ArrayList<Loan> loans = new ArrayList<>();
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Name");
        loans.add(new Loan(book, member));
        library.setLoans(loans);

        assertEquals(1, library.getLoans().size(),
                "Loans ArrayList is empty or contains more than one loan");
        assertEquals("Title", library.getLoans().getFirst().getBook().getTitle(),
                "Loans ArrayList first book title doesn't match");
    }

    @Test
    void findBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        library.getBooks().add(book);

        assertEquals(book, library.findBook("Title", "Author", "PublishDate"),
                "Searched book not found");
        assertNull(library.findBook("Test", "Test", "Test"),
                "Found non existing book");
    }

    @Test
    void findLoan() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Name");
        Loan loan = new Loan(book, member);
        library.getLoans().add(loan);

        assertEquals(loan, library.findLoan(book, member),
                "Searched loan not found");
        assertNull(library.findLoan(new Book("Test", "Test", "Test"),
                        new Member(2, "Test")),
                "Found non existing loan");
    }

    @Test
    void findMember() {
        Member member = new Member(1, "Name");
        library.getMembers().add(member);

        assertEquals(member, library.findMember(1, "Name"),
                "Searched member not found");
        assertNull(library.findMember(2, "Test"),
                "Found non existing member");
    }

    @Test
    void status() {
        String status = library.status();

        assertNotNull(status, "Status is null");
        assertTrue(status.contains("Books:"),
                "Status does not contain Books");
        assertTrue(status.contains("Members:"),
                "Status does not contain Members");
        assertTrue(status.contains("Loans:"),
                "Status does not contain Loans");
    }

}