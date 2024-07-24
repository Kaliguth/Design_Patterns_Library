// Main

import library.Book;
import library.Librarian;
import library.Library;
import library.Member;

public class Main {
    public static void main(String[] args) {
        Library lib = Library.getInstance();
        Librarian librarian = new Librarian();
        System.out.println("\nlibrary book before add: " + lib.getBooks());
        Book book = new Book("book", "author", "date");
        System.out.println("new book: " + book);
        librarian.addBook(book);
        System.out.println("library books after add: " + lib.getBooks());

        System.out.println("\nlibrary members before add: " + lib.getMembers());
        Member member = new Member("name", 1);
        System.out.println("new member: " + member);
        librarian.registerMember(member);
        System.out.println("library members after add: " + lib.getMembers());

        System.out.println("\ntests:");
        System.out.println("loan:");
        System.out.println("library loans before: " + lib.getLoans());
        System.out.println("member loans before: " + member.getLoans());
        System.out.println("book availability before: " + book.isAvailable());
        librarian.loanBook(book, member);
        System.out.println("library loans after loan: " + lib.getLoans());
        System.out.println("member loans after loan: " + member.getLoans());
        System.out.println(member);
        System.out.println("book availability after loan: " + book.isAvailable());

        System.out.println("\nreturn:");
        librarian.returnBook(book, member);
        System.out.println("library loans after return: " + lib.getLoans());
        System.out.println("member loans after return: " + member.getLoans());
        System.out.println("book availability after return: " + book.isAvailable());

        System.out.println("\nclone:");
        System.out.println("library books before: " + lib.getBooks());
        librarian.addBook(book.clone());
        System.out.println("library books after clone: " + lib.getBooks());

        System.out.println("\nremove book:");
        librarian.removeBook(book);
        System.out.println("library books after remove: " + lib.getBooks());
        System.out.println("Only one removed");

        System.out.println("\nremove member:");
        System.out.println("library members before: " + lib.getMembers());
        librarian.removeMember(member);
        System.out.println("library members after remove: " + lib.getMembers());

    }
}