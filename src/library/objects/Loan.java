// Loan class

package library.objects;

import java.time.LocalDate;

public class Loan {
    // Static loan number
    private static int currentNum = 1000;
    // Loan id
    private final int id;
    // Loaned book
    private final Book book;
    // Loan member
    private final Member member;
    // Loan date
    private LocalDate loanDate;
    // Return date
    private LocalDate returnDate;

    // Constructor
    public Loan(Book book, Member member) {
        this.id = currentNum++;
        this.book = book;
        this.member = member;
        this.loanDate = LocalDate.now();
        // Return 7 days after loan date
        this.returnDate = this.loanDate.plusDays(7);
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Overload of Object's equals method with Loan parameter
    public boolean equals(Loan loan) {
        if (loan == null) return false;

        if (this == loan) return true;

        return this.book.equals(loan.book) &&
                this.member.equals(loan.member) &&
                this.loanDate.equals(loan.loanDate) &&
                this.returnDate.equals(loan.returnDate);
    }

    // toString method
    @Override
    public String toString() {
        return "\"" + book.getTitle() + "\"" +
                "  -  loaned by " + member.getName() +
                "      Loan Date: " + loanDate +
                "      Return Date: " + returnDate;
    }

}
