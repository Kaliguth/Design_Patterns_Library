// Main class to create and run the library objects and application

import library.*;
import ui.LibraryGUI;

public class Main {
    public static void main(String[] args) {
        // Library object
        Library library = Library.getInstance();
        // Librarian object
        Librarian librarian = new Librarian();

        // Creating default library objects (books, members and loans)
        DefaultObjects.create(library);

        // Run the library application
        new LibraryGUI(librarian);

    }

}