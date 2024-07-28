package ui;

import library.Librarian;

public class LibraryGUI implements Runnable{
    // Application window object
    private final Window window;

    // Constructor
    public LibraryGUI(Librarian librarian) {
        // Create a new application window for the librarian
        window = new Window(librarian);
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
