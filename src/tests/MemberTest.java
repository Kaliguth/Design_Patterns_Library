// Member test

package tests;

import library.objects.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class MemberTest {
    // Tests
    @Test
    void getMemberNum() {
        Member member = new Member(1, "Member");

        assertTrue(member.getMemberNum() >= 100,
                "Member number not 100 or higher");
    }

    @Test
    void getId() {
        Member member = new Member(1, "Member");

        assertEquals(1, member.getId(),
                "Member ID does not match");
    }

    @Test
    void getName() {
        Member member = new Member(1, "Member");

        assertEquals("Member", member.getName(),
                "Member name does not match");
    }

    @Test
    void getLoans() {
        Member member = new Member(1, "Member");

        assertNotNull(member.getLoans(),
                "Member loans is null");
        assertTrue(member.getLoans().isEmpty(),
                "New member loans is not empty");
    }

    @Test
    void setLoans() {
        Member member = new Member(1, "Member");
        Book book = new Book("Title", "Author", "PublishDate");
        Loan loan = new Loan(book, member);

        ArrayList<Loan> loans = new ArrayList<>();
        loans.add(loan);
        member.setLoans(loans);

        assertEquals(1, member.getLoans().size(),
                "Member loans list is empty or contains more than one loan");
        assertEquals(loan, member.getLoans().getFirst(),
                "Added loan does not match");
    }

    @Test
    void testEquals() {
        Member member1 = new Member(1, "Member1");
        Member member2 = new Member(1, "Member1");
        Member member3 = new Member(2, "Member2");

        // Same parameters
        assertTrue(member1.equals(member2),
                "Same parameters returned false");

        // Different parameters
        assertFalse(member1.equals(member3),
                "Different parameters returned true");

        // Null or different class
        assertFalse(member1.equals(null),
                "Equals with null returned true");
        assertFalse(member1.equals(new String("Not a member")),
                "Equals with different class returned true");
    }

    @Test
    void testToString() {
        Member member = new Member(1, "Member");
        String result = "Member number " + member.getMemberNum() +
                " - " + member.getName() +
                "      ID: " + member.getId() +
                "      Number of loans: " + member.getLoans().size();

        assertEquals(result, member.toString(),
                "Member string does not match");
    }

}