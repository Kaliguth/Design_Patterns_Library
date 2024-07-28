// Librarian class - Observer

package library;

import library.facade.LibrarianFacade;
import library.objects.*;
import library.observer.Observer;

import javax.swing.*;
import java.util.ArrayList;

public class Librarian implements Observer {
    // Singleton LibrarianFacade object
    private final LibrarianFacade facade;

    // Constructor
    public Librarian() {
        facade = LibrarianFacade.getInstance();
        Library.getInstance().registerObserver(this);
    }

    // Getters (didn't have use for setters)
    public ArrayList<Book> getBooks() {
        return facade.getBooks();
    }

    public ArrayList<Member> getMembers() {
        return facade.getMembers();
    }

    public ArrayList<Loan> getLoans() {
        return facade.getLoans();
    }

    // Use Facade to show library status (unused)
    public void showLibraryStatus() {
        System.out.println(facade.showLibraryStatus());
    }

    // Handle notifications for createBook
    public void createBook(String title, String author, String publishDate) {
        // Add the newly created book in Facade into a Book object
        Book newBook = facade.createBook(title, author, publishDate);

        // Check if creation succeeded - add the book if it did, update an error message if not
        if (newBook != null) {
            addBook(newBook);
        } else {
            update("Failed to create book");
        }
    }

    // Handle notifications for addBook
    public void addBook(Book book) {
        // Check if book adding succeeded - update a message accordingly
        if (facade.addBook(book)) {
            update("\"" + book.getTitle() + "\" added to the library");
        } else {
            update("Failed to add book");
        }
    }

    // Handle notifications for removeBook
    public void removeBook(Book book) {
        // Check if book removal succeeded - update a message accordingly
        if (facade.removeBook(book.getTitle(), book.getAuthor(), book.getPublishDate())) {
            update("Book removed: \"" + book.getTitle() + "\"");
        } else {
            update("Failed to remove book");
        }
    }

    // Handle notifications for createMember
    public void createMember(int id, String name) {
        // Add the newly created member in facade into a Member object
        Member newMember = facade.createMember(id, name);

        // Check if member creation succeeded - register the member if it did, update an error message if not
        if (newMember != null) {
            registerMember(newMember);
        } else {
            update("Failed to create member");
        }
    }

    // Handle notifications for registerMember
    public void registerMember(Member member) {
        // Check if member registration succeeded - update a message accordingly
        if (facade.registerMember(member)) {
            update("Member " + member.getMemberNum() + " registered: " +
                    member.getId() + " - " + member.getName());
        } else {
            update("Failed to register member");
        }
    }

    // Handle notifications for removeMember
    public void removeMember(Member member) {
        // Check if member removal succeeded - update a message accordingly
        if (facade.removeMember(member.getId(), member.getName())) {
            update("Member removed: " + member.getId() + " - " + member.getName());
        } else {
            update("Failed to remove member");
        }
    }

    // Handle notifications for createLoan (eventually unused)
    public void createLoan(Book book, Member member) {
        // Add the newly created loan in facade into a Loan object
        Loan newLoan = facade.createLoan(book, member);

        // Check if loan creation succeeded - loan the book to the member if it did, update an error message if not
        if (newLoan != null) {
            update("Successfully created loan of \"" + book.getTitle() + "\" " +
                    "for " + member.getName());
            loanBook(book, member);
        } else {
            update("Failed to create loan");
        }
    }

    // Handle notifications for loanBook
    public void loanBook(Book book, Member member) {
        // Check if book loan succeeded - update a message accordingly
        if (facade.loanBook(book, member)) {
            update("\"" + book.getTitle() + "\" loaned to " + member.getName());
        } else {
            update("Failed to loan book");
        }
    }

    // Handle notifications for returnBook
    public void returnBook(Book book, Member member) {
        // Check if book return succeeded - update a message accordingly
        if (facade.returnBook(book, member)) {
            update("\"" + book.getTitle() + "\" returned by " + member.getName());
        } else {
            update("Failed to return book");
        }
    }

    // Override for Observer interface update method
    @Override
    public void update(String notification) {
        // If the message contains "found" (Library's verification messages),
        // it will not pop up a notification window
        if (!notification.contains("found"))
            // Show pop up with the notification
            JOptionPane.showMessageDialog(null,
                    notification, "Message",
                    JOptionPane.INFORMATION_MESSAGE);
    }

}
