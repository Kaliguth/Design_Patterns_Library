package library;

import library.facade.LibrarianFacade;
import library.observer.Observer;

public class Librarian implements Observer {
    // Singleton LibrarianFacade object
    private final LibrarianFacade facade;

    // Constructor
    public Librarian() {
        facade = LibrarianFacade.getInstance();
        Library.getInstance().registerObserver(this);
    }

    // Handle notifications for createBook
    public void createBook(String title, String author, String publicationDate) {
        Book newBook = facade.createBook(title, author, publicationDate);
        if (newBook != null) {
            update("Successfully created \"" + newBook.getTitle() + "\"");
            addBook(newBook);
        } else {
            update("Failed to create book");
        }
    }

    // Handle notifications for addBook
    public void addBook(Book book) {
        if (facade.addBook(book)) {
            update("\"" + book.getTitle() + "\" added to the library");
        } else {
            update("Failed to add book");
        }
    }

    // Handle notifications for removeBook
    public void removeBook(Book book) {
        if (facade.removeBook(book)) {
            update("Book removed: \"" + book.getTitle() + "\"");
        } else {
            update("Failed to remove book");
        }
    }

    // Handle notifications for createMember
    public void createMember(int id, String name) {
        Member newMember = facade.createMember(id, name);
        if (newMember != null) {
            update("Member " + newMember.getMemberNum() + " created: " + newMember.getId() + " - " + newMember.getName());
            registerMember(newMember);
        } else {
            update("Failed to create member");
        }
    }

    // Handle notifications for registerMember
    public void registerMember(Member member) {
        if (facade.registerMember(member)) {
            update("Member " + member.getMemberNum() + " registered: " +
                    member.getId() + " - " + member.getName());
        } else {
            update("Failed to register member");
        }
    }

    // Handle notifications for removeMember
    public void removeMember(Member member) {
        if (facade.removeMember(member)) {
            update("Member removed: " + member.getId() + " - " + member.getName());
        } else {
            update("Failed to remove member");
        }
    }

    // Handle notifications for createLoan
    public void createLoan(Book book, Member member) {
        Loan newLoan = facade.createLoan(book, member);
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
        if (facade.loanBook(book, member)) {
            update("\"" + book.getTitle() + "\" loaned to " + member.getName());
        } else {
            update("Failed to loan book");
        }
    }

    // Handle notifications for returnBook
    public void returnBook(Book book, Member member) {
        if (facade.returnBook(book, member)) {
            update("\"" + book.getTitle() + "\" returned by " + member.getName());
        } else {
            update("Failed to return book");
        }
    }

    // Use facade to show library status
    public void showLibraryStatus() {
        System.out.println(facade.showLibraryStatus());
    }

    // Override for Observer interface update
    @Override
    public void update(String notification) {
        System.out.println(notification);
    }

}
