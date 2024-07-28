// Default objects class

package library;

import library.objects.*;

import java.util.ArrayList;

public class DefaultObjects {

    // Method to create default library objects
    public static void create(Library library) {
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
        // Default members
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
        // Default loans (2 books for first, third and fifth member)
        ArrayList<Loan> defaultLoans = new ArrayList<>();
        for (int i = 0; i < 6; i += 2) {
            Loan loan1 = new Loan(library.getBooks().get(i), library.getMembers().get(i));
            defaultLoans.add(loan1);
            library.getMembers().get(i).getLoans().add(loan1);
            library.getBooks().get(i).setAvailability(false);
            Loan loan2 = new Loan(library.getBooks().get(i + 1), library.getMembers().get(i));
            defaultLoans.add(loan2);
            library.getMembers().get(i).getLoans().add(loan2);
            library.getBooks().get(i + 1).setAvailability(false);
        }
        library.setLoans(defaultLoans);
    }

}
