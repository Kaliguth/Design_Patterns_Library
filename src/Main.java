// Main

import library.*;
import library.facade.LibrarianFacade;

public class Main {
    public static void main(String[] args) {
        Library library = Library.getInstance();
        Librarian librarian = new Librarian();
        System.out.println("\nlibrary book before add: " + library.getBooks());
        librarian.createBook("book", "author", "date");
        System.out.println("library books after add: " + library.getBooks());

        System.out.println("\nlibrary members before add: " + library.getMembers());
        librarian.createMember(1, "name");
        System.out.println("library members after add: " + library.getMembers());

        System.out.println("\ntests:");
        System.out.println("loan:");
        System.out.println("library loans before: " + library.getLoans());
        System.out.println("library books before: " + library.getBooks());
        librarian.createLoan(library.findBook("book", "author", "date"),library.getMembers().getFirst());
        System.out.println("library loans after loan: " + library.getLoans());
        System.out.println("library books after loan: " + library.getBooks());

        System.out.println("\nreturn:");
        librarian.returnBook(library.findBook("book", "author", "date")
                ,library.findMember(1, "name"));
        System.out.println("library loans after return: " + library.getLoans());
        System.out.println("library books after return: " + library.getBooks());

        System.out.println("\nclone:");
        System.out.println("library books before: " + library.getBooks());
        librarian.addBook(library.findBook("book", "author", "date").clone());
        System.out.println("library books after clone: " + library.getBooks());

        System.out.println("\nremove book:");
        librarian.removeBook(library.findBook("book", "author", "date"));
        System.out.println("library books after remove: " + library.getBooks());
        System.out.println("only one removed");
        // Trying to remove a book that does not exist
        System.out.println("\nremove non-existent book:");
        librarian.removeBook(new Book("none", "author", "date"));
        System.out.println("library books after remove of fake book: " + library.getBooks());
        System.out.println("failed, none removed");

        System.out.println("\nremove member:");
        System.out.println("library members before: " + library.getMembers());
        librarian.removeMember(library.findMember(1, "name"));
        System.out.println("library members after remove: " + library.getMembers());

        System.out.println("\nlibrary status:");
        librarian.showLibraryStatus();

    }

}