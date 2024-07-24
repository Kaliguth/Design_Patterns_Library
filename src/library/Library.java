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

}
