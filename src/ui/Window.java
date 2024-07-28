package ui;

import library.Librarian;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    // Currently displayed panel
    private JPanel currentPanel;
    // Librarian object
    private final Librarian librarian;

    // Constructor
    public Window(Librarian librarian) {
        this.librarian = librarian;
        // Initialize the MainWindow components
        initialize();
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    // Method to switch to a different panel after buttons are clicked
    public void switchToPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        currentPanel = newPanel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Method to initialize all component of MainWindow
    public void initialize() {
        // Window configurations:
        // Set window caption text, default close action, window size, window location and disable resizing
        setTitle("Kali Library");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create and initialize header and footer
        HeaderPanel header = new HeaderPanel(this);
        FooterPanel footer = new FooterPanel();

        // Add header and footer panels to the window
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);

        // Create and initialize main menu
        // (If the current panel was not changed by button clicks)
        if (!(currentPanel instanceof MainMenu)) {
            currentPanel = new MainMenu(this);
            add(currentPanel, BorderLayout.CENTER);
        }

        // Show window
        setVisible(true);
    }

}
