package library;

import java.util.*;

public class Library {
    private static Library instance; // Instance
    private ArrayList<Book> books; // Books list
    private ArrayList<Member> members; // Members list
    private ArrayList<Loan> loans; // Loans list

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
