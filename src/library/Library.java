package library;

import library.observer.Observer;
import library.observer.ObserverSubject;

import java.util.*;

public class Library extends ObserverSubject {
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

    // Getters & Setters
    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    // Method to find a book in the library's book list
    public Book findBook(String title, String author, String publicationDate) {
        Book bookToFind = new Book(title, author, publicationDate);
        for (Book book : books) {
            if (book.equals(bookToFind)) {
                notifyObserver("Book found");
                return book;
            }
        }
        notifyObserver("Book not found");
        return null;
    }

    // Method to find a loan by book and member in the library's loans list
    public Loan findLoan(Book book, Member member) {
        for (Loan loan : loans) {
            if (loan.getBook().equals(book) && loan.getMember().equals(member)) {
                notifyObserver("Loan found");
                return loan;
            }
        }
        notifyObserver("Loan not found");
        return null;
    }

    // Method to find a member in the library's members list
    public Member findMember(int id, String name) {
        Member memberToFind = new Member(id, name);
        for (Member member : members) {
            if (member.equals(memberToFind)) {
                notifyObserver("Member found");
                return member;
            }
        }
        notifyObserver("Member not found");
        return null;
    }

    // Method to show library status
    public String status() {
        StringBuilder status = new StringBuilder();
       status.append("Books:\n");
        for (Book book : books) {
            status.append(book.toString()).append("\n");
        }
        status.append("\nMembers:\n");
        for (Member member : members) {
            status.append(member.toString()).append("\n");
        }
        status.append("\nLoans:\n");
        for (Loan loan : loans) {
            status.append(loan.toString()).append("\n");
        }

        return status.toString();
    }

}
