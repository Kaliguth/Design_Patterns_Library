package library;

public class Librarian {
    private final Library library;

    public Librarian() {
        library = Library.getInstance();
    }

    public boolean addBook(Book book) {
        if (book != null) {
            library.getBooks().add(book);
            return true;
        }
        return false;
    }

    public boolean removeBook(Book bookToRemove) {
        if (bookToRemove != null) {
            for (Book book : library.getBooks()) {
                if (book.equals(bookToRemove)) {
                    library.getBooks().remove(bookToRemove);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean registerMember(Member member) {
        if (member != null) {
            library.getMembers().add(member);
            return true;
        }
        return false;
    }

    public boolean removeMember(Member memberToRemove) {
        if (memberToRemove != null) {
            for (Member member : library.getMembers()) {
                if (member.equals(memberToRemove)) {
                    library.getMembers().remove(memberToRemove);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean loanBook(Book book, Member member) {
        if (book != null && member != null) {
            Book bookToLoan = library.findBook(book);
            if (bookToLoan != null && bookToLoan.isAvailable()) {
                bookToLoan.setAvailability(false);

                Loan loan = new Loan(book, member);
                library.getLoans().add(loan);
                member.getLoans().add(loan);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(Book book, Member member) {
        if (book != null && member != null) {
            Loan loan = library.findLoan(book, member);
            if (loan != null) {
                Book bookToReturn = loan.getBook();
                bookToReturn.setAvailability(true);

                library.getLoans().remove(loan);
                member.getLoans().remove(loan);
                return true;
            }
        }
        return false;
    }
}
