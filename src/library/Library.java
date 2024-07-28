// Library class - Singleton and ObserverSubject

package library;

import library.objects.Book;
import library.objects.Loan;
import library.objects.Member;
import library.observer.ObserverSubject;

// Util imports
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

    // Method to find a book in the library's books list by book parameters
    public Book findBook(String title, String author, String publishDate) {
        // Book to find object
        Book bookToFind = new Book(title, author, publishDate);

        // Go over books list to find a fitting book that is also available
        Book availableBook = null;
        for (Book book : books) {
            if (book.equals(bookToFind) && book.isAvailable()) {
                availableBook = book;
            }
        }

        // Check if an available book was found and notify the observer
        if (availableBook == null) {
            notifyObserver("Book not found");
        } else {
            notifyObserver("Book found");
        }

        // Return the book (null checked later)
        return availableBook;
    }

    // Method to find a loan by book and member in the library's loans list
    public Loan findLoan(Book book, Member member) {
        // Go over loans list, find fitting loan and notify the observer
        for (Loan loan : loans) {
            if (loan.getBook().equals(book) && loan.getMember().equals(member)) {
                notifyObserver("Loan found");
                return loan;
            }
        }

        // Return null if loan not found and notify the observer (null checked later)
        notifyObserver("Loan not found");
        return null;
    }

    // Method to find a member in the library's members list
    public Member findMember(int id, String name) {
        // Member to find object
        Member memberToFind = new Member(id, name);

        // Go over members list, find fitting member and notify the observer
        for (Member member : members) {
            if (member.equals(memberToFind)) {
                notifyObserver("Member found");
                return member;
            }
        }

        // Return null if member not found and notify the observer (null checked later)
        notifyObserver("Member not found");
        return null;
    }

    // Method to show library status as string
    public String status() {
        // String builder object
        StringBuilder status = new StringBuilder();

        // Books
        status.append("Books:\n");
        for (Book book : books) {
            status.append(book.toString()).append("\n");
        }
        // Members
        status.append("\nMembers:\n");
        for (Member member : members) {
            status.append(member.toString()).append("\n");
        }
        // Loans
        status.append("\nLoans:\n");
        for (Loan loan : loans) {
            status.append(loan.toString()).append("\n");
        }

        // Return the string builder as string
        return status.toString();
    }

}
