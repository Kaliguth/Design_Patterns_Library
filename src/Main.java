// Main

import library.*;
import ui.LibraryGUI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Library library = Library.getInstance();
        Librarian librarian = new Librarian();
        // Default books
        ArrayList<Book> defaultBooks = new ArrayList<>();
        defaultBooks.add(new Book("The Hobbit", "J.R.R. Tolkien", "1937"));
        defaultBooks.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "1997"));
        defaultBooks.add(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", "1998"));
        defaultBooks.add(new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", "1999"));
        defaultBooks.add(new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling", "2000"));
        defaultBooks.add(new Book("Harry Potter and the Order of the Phoenix", "J.K. Rowling", "2003"));
        defaultBooks.add(new Book("Harry Potter and the Half-Blood Prince", "J.K. Rowling", "2005"));
        defaultBooks.add(new Book("Harry Potter and the Deathly Hallows", "J.K. Rowling", "2007"));
        defaultBooks.add(new Book("To Kill a Mockingbird", "Harper Lee", "1960"));
        defaultBooks.add(new Book("Pride and Prejudice", "Jane Austen", "1813"));
        library.setBooks(defaultBooks);
        // Default member
        ArrayList<Member> defaultMembers = new ArrayList<>();
        defaultMembers.add(new Member(1, "Alice Johnson"));
        defaultMembers.add(new Member(2, "Bob Smith"));
        defaultMembers.add(new Member(3, "Charlie Brown"));
        defaultMembers.add(new Member(4, "Diana Prince"));
        defaultMembers.add(new Member(5, "Edward Norton"));
        defaultMembers.add(new Member(6, "Fiona Gallagher"));
        defaultMembers.add(new Member(7, "George Miller"));
        defaultMembers.add(new Member(8, "Hannah Montana"));
        defaultMembers.add(new Member(9, "Ivy League"));
        defaultMembers.add(new Member(10, "Jack Sparrow"));
        library.setMembers(defaultMembers);
//        Member member1 = new Member(11, "Jack Sparrow");
//        librarian.registerMember(member1);
//        Book book1 = new Book("Book1", "author", "date");
//        librarian.addBook(book1);
//        Book book2 = new Book("Book2", "author", "date");
//        librarian.addBook(book2);
//        Book book3 = new Book("Book3", "author", "date");
//        librarian.addBook(book3);
//        Book book4 = new Book("Book4", "author", "date");
//        librarian.addBook(book4);
//        Book book5 = new Book("Book5", "author", "date");
//        librarian.addBook(book5);
//        Book book6 = new Book("Book6", "author", "date");
//        librarian.addBook(book6);
//        Book book7 = new Book("Book7", "author", "date");
//        librarian.addBook(book7);
//        Book book8 = new Book("Book5", "author", "date");
//        librarian.addBook(book8);
//        librarian.loanBook(book1, member1);
//        librarian.loanBook(book2, member1);
//        librarian.loanBook(book3, member1);
//        librarian.loanBook(book4, member1);
//        librarian.loanBook(book5, member1);
//        librarian.loanBook(book6, member1);
//        librarian.loanBook(book7, member1);
//        librarian.loanBook(book8, member1);
        new LibraryGUI(librarian);
//        System.out.println("\nlibrary book before add: " + library.getBooks());
//        librarian.createBook("book", "author", "date");
//        System.out.println("library books after add: " + library.getBooks());
//
//        System.out.println("\nlibrary members before add: " + library.getMembers());
//        librarian.createMember(1, "name");
//        System.out.println("library members after add: " + library.getMembers());
//
//        System.out.println("\ntests:");
//        System.out.println("loan:");
//        System.out.println("library loans before: " + library.getLoans());
//        System.out.println("library books before: " + library.getBooks());
//        librarian.createLoan(library.findBook("book", "author", "date"),library.getMembers().getFirst());
//        System.out.println("library loans after loan: " + library.getLoans());
//        System.out.println("library books after loan: " + library.getBooks());
//
//        System.out.println("\nreturn:");
//        librarian.returnBook(library.findBook("book", "author", "date")
//                ,library.findMember(1, "name"));
//        System.out.println("library loans after return: " + library.getLoans());
//        System.out.println("library books after return: " + library.getBooks());
//
//        System.out.println("\nclone:");
//        System.out.println("library books before: " + library.getBooks());
//        librarian.addBook(library.findBook("book", "author", "date").clone());
//        System.out.println("library books after clone: " + library.getBooks());
//
//        System.out.println("\nremove book:");
//        librarian.removeBook(library.findBook("book", "author", "date"));
//        System.out.println("library books after remove: " + library.getBooks());
//        System.out.println("only one removed");
//        // Trying to remove a book that does not exist
//        System.out.println("\nremove non-existent book:");
//        librarian.removeBook(new Book("none", "author", "date"));
//        System.out.println("library books after remove of fake book: " + library.getBooks());
//        System.out.println("failed, none removed");
//
//        System.out.println("\nremove member:");
//        System.out.println("library members before: " + library.getMembers());
//        librarian.removeMember(library.findMember(1, "name"));
//        System.out.println("library members after remove: " + library.getMembers());
//
//        System.out.println("\nlibrary status:");
//        librarian.showLibraryStatus();

    }

}