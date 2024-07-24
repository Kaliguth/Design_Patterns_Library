package library;

import java.util.*;

public class Library {
    // Singleton instance
    private static Library instance;
    // Books list
    private ArrayList<Book> books;
    // Members list
    private ArrayList<Member> members;
    // Loans list
    private ArrayList<Loan> loans;

    // Constructor
    private Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        loans = new ArrayList<>();
    }

    // Method to get access to the singleton Library
    // Synchronized prevents multiple creations of Library objects at the same time
    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Getters
    public List<Book> getBooks() {
        return this.books;
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public List<Loan> getLoans() {
        return this.loans;
    }

    // Method to find a book in the library's book list
    public Book findBook(Book bookToFind) {
        for (Book book : this.getBooks()) {
            if (book.equals(bookToFind)) {
                return book;
            }
        }
        return null;
    }

    // Method to find a loan by book and member in the library's loans list
    public Loan findLoan(Book book, Member member) {
        for (Loan loan : this.getLoans()) {
            if (loan.getBook().equals(book) && loan.getMember().equals(member)) {
                return loan;
            }
        }
        return null;
    }
}
