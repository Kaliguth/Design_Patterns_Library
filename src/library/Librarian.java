package library;

public class Librarian {
    // Singleton Library object
    private final Library library;

    // Constructor
    public Librarian() {
        library = Library.getInstance();
    }

}
