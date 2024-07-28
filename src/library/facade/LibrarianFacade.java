// Librarian Facade class - Singleton

package library.facade;

import library.Library;
import library.objects.*;
import library.factory.LibraryFactory;

import java.util.*;

public class LibrarianFacade {
    // Singleton instance
    private static LibrarianFacade instance;
    // Singleton Library object
    private final Library library;

    // Constructor
    private LibrarianFacade() {
        library = Library.getInstance();
    }

    // Method to get access to the singleton LibrarianFacade
    public static synchronized LibrarianFacade getInstance() {
        if (instance == null) {
            instance = new LibrarianFacade();
        }
        return instance;
    }

    // Method to return library's books
    public ArrayList<Book> getBooks() {
        return library.getBooks();
    }

    // Method to return library's members
    public ArrayList<Member> getMembers() {
        return library.getMembers();
    }

    // Method to return library's loans
    public ArrayList<Loan> getLoans() {
        return library.getLoans();
    }

    // Use library's status method
    public String showLibraryStatus() {
        return library.status();
    }

    // Using LibraryFactory to create a book
    public Book createBook(String title, String author, String publishDate) {
        return LibraryFactory.createBook(title, author, publishDate);
    }

    // Method to add a book to the library
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        return library.getBooks().add(book);
    }

    // Method to remove a book from the library
    public boolean removeBook(String title, String author, String publishDate) {
        // Find the book in the library's books list
        Book bookToRemove = library.findBook(title, author, publishDate);
        if (bookToRemove == null) {
            return false;
        }
        return library.getBooks().remove(bookToRemove);
    }

    // Using LibraryFactory to create a member
    public Member createMember(int id, String name) {
        return LibraryFactory.createMember(id, name);
    }

    // Method to register a member to the library
    public boolean registerMember(Member member) {
        if (member == null) {
            return false;
        }

        // If the same member is already registered to the library
        Member existingMember = library.findMember(member.getId(), member.getName());
        if (existingMember != null && member.equals(existingMember)) {
            return false;
        }
        return library.getMembers().add(member);
    }

    // Method to remove a member from the library
    public boolean removeMember(int id, String name) {
        // Find the member in the library's members list
        Member memberToRemove = library.findMember(id, name);
        if (memberToRemove == null) {
            return false;
        }
        return library.getMembers().remove(memberToRemove);
    }

    // Using LibraryFactory to create a loan
    public Loan createLoan(Book book, Member member) {
        return LibraryFactory.createLoan(book, member);
    }

    // Method to load a book and add a loan to the library and member
    public boolean loanBook(Book book, Member member) {
        if (book == null || member == null) {
            return false;
        }

        // Find the book in the library's books list to check if available to loan
        Book bookToLoan = library.findBook(book.getTitle(), book.getAuthor(), book.getPublishDate());
        if (bookToLoan == null || !bookToLoan.isAvailable()) {
            return false;
        }

        // Set the book's availability to false
        bookToLoan.setAvailability(false);
        // Create a new loan and add to library and member's loans list
        Loan loan = new Loan(book, member);
        library.getLoans().add(loan);
        member.getLoans().add(loan);
        return true;
    }

    // Method to return a book and remove the loan from the library and member
    public boolean returnBook(Book book, Member member) {
        if (book == null || member == null) {
            return false;
        }

        // Find the loan in the library's loans list
        Loan loanToRemove = library.findLoan(book, member);
        if (loanToRemove == null) {
            return false;
        }

        // Set the book's availability to true
        book.setAvailability(true);
        // Remove the loan from the library and member's loans list
        library.getLoans().remove(loanToRemove);
        member.getLoans().remove(loanToRemove);
        return true;
    }

}
