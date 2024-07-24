package library;

import java.time.LocalDate;

public class Loan {
    // Loaned book
    private Book book;
    // Borrowing member
    private Member member;
    // Loan date
    private LocalDate loanDate;
    // Return date
    private LocalDate returnDate;

    // Constructor
    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.loanDate = LocalDate.now();
        // Return 7 days from now
        this.returnDate = this.loanDate.plusDays(7);
    }

    // Getters & Setters
    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Overload of Object's equals method with Loan parameter
    public boolean equals(Loan loan) {
        if (this == loan) return true;

        return this.book.equals(loan.book) &&
                this.member.equals(loan.member) &&
                this.loanDate.equals(loan.loanDate);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + this.book.getTitle() +
                ", member=" + this.member.getName() +
                ", loanDate=" + this.loanDate +
                ", returnDate=" + this.returnDate +
                '}';
    }
}
