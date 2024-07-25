package library.factory;

import library.Book;
import library.Loan;
import library.Member;

public class LibraryFactory {

    // Factory method to create a book
    public static Book createBook(String title, String author, String publishDate) {
        if (title == null || title.isEmpty() ||
                author == null || author.isEmpty() ||
                publishDate == null || publishDate.isEmpty()) {
            return null;
        }
        return new Book(title, author, publishDate);
    }

    // Factory method to create a member
    public static Member createMember(int id, String name) {
        if (id <= 0 || name == null || name.isEmpty()) {
            return null;
        }
        return new Member(id, name);
    }

    // Factory method to create a loan
    public static Loan createLoan(Book book, Member member) {
        if (book == null || member == null) {
            return null;
        }
        return new Loan(book, member);
    }

}
