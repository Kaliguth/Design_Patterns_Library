package ui;

import library.Librarian;

public class LibraryGUI implements Runnable{
    // Application window object
    private final Window window;
    // Librarian object
    private final Librarian librarian;

    // Constructor
    public LibraryGUI(Librarian librarian) {
        // Create a new application window
        window = new Window();
        // Set the librarian to library's librarian
        this.librarian = librarian;
        // Run the application
        run();
    }

    // Method to run the library application
    // Override of runnable run
    @Override
    public void run() {
        window.initialize();
    }
}
