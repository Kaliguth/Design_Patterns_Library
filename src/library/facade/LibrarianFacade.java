package library.facade;

import library.Book;
import library.Library;
import library.Loan;
import library.Member;
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
        return library.getMembers().add(member);
    }

    // Method to remove a member from the library
    public boolean removeMember(int id, String name) {
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

        Book bookToLoan = library.findBook(book.getTitle(), book.getAuthor(), book.getPublishDate());
        if (bookToLoan == null || !bookToLoan.isAvailable()) {
            return false;
        }

        bookToLoan.setAvailability(false);
        Loan loan = new Loan(book, member);
        library.getLoans().add(loan);
        member.getLoans().add(loan);
        return true;
    }

    // Method to return a book and remove the load from the library and member
    public boolean returnBook(Book book, Member member) {
        if (book == null || member == null) {
            return false;
        }

        Loan loanToRemove = library.findLoan(book, member);
        if (loanToRemove == null) {
            return false;
        }

        book.setAvailability(true);
        library.getLoans().remove(loanToRemove);
        member.getLoans().remove(loanToRemove);
        return true;
    }

}
