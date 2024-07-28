// Loan Test

package tests;

import library.objects.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


class LoanTest {
    // Tests
    @Test
    void getId() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Member");
        Loan loan = new Loan(book, member);

        assertTrue(loan.getId() >= 1000,
                "Loan ID is not 1000 or higher");
    }

    @Test
    void getBook() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Member");
        Loan loan = new Loan(book, member);

        assertEquals(book, loan.getBook(),
                "Loan book does not match");
    }

    @Test
    void getMember() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Member");
        Loan loan = new Loan(book, member);

        assertEquals(member, loan.getMember(),
                "Loan member does not match");
    }

    @Test
    void getLoanDate() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Member");
        Loan loan = new Loan(book, member);

        assertEquals(LocalDate.now(), loan.getLoanDate(),
                "Loan date does not match");
    }

    @Test
    void getReturnDate() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Member");
        Loan loan = new Loan(book, member);

        assertEquals(LocalDate.now().plusDays(7), loan.getReturnDate(),
                "Loan return date does not match");
    }

    @Test
    void testEquals() {
        Book book1 = new Book("Title1", "Author1", "PublishDate1");
        Book book2 = new Book("Title2", "Author2", "PublishDate2");
        Member member1 = new Member(1, "Member1");
        Member member2 = new Member(2, "Member2");

        Loan loan1 = new Loan(book1, member1);
        Loan loan2 = new Loan(book2, member2);
        Loan loan3 = new Loan(book1, member1);

        // Same loan parameters
        assertTrue(loan1.equals(loan3),
                "Same loan parameters returned false");

        // Different book and member
        assertFalse(loan1.equals(loan2),
                "Different loan parameters returned true");

        // Different book
        assertFalse(loan1.equals(new Loan(book2, member1)),
                "Different book returned true");

        // Different member
        assertFalse(loan1.equals(new Loan(book1, member2)),
                "Different member returned true");

        // Different loan date
        Loan loan4 = new Loan(book1, member1);
        loan4.setLoanDate(LocalDate.now().minusDays(1));
        assertFalse(loan1.equals(loan4),
                "Different loan date returned true");

        // Different return date
        Loan loan5 = new Loan(book1, member1);
        loan5.setReturnDate(LocalDate.now().plusDays(10));
        assertFalse(loan1.equals(loan5),
                "Different return date returned true");

        // Null or different class
        assertFalse(loan1.equals(null),
                "Equals with null returned true");
        assertFalse(loan1.equals(new String("String")),
                "Equals with different class returned true");
    }

    @Test
    void testToString() {
        Book book = new Book("Title", "Author", "PublishDate");
        Member member = new Member(1, "Member");
        Loan loan = new Loan(book, member);
        String expected = "\"" + book.getTitle() + "\"" +
                "  -  loaned by " + member.getName() +
                "      Loan Date: " + loan.getLoanDate() +
                "      Return Date: " + loan.getReturnDate();

        assertEquals(expected, loan.toString(),
                "Loan string does not match");
    }

}