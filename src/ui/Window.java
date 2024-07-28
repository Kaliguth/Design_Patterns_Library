// Window class - main application window frame

package ui;

import library.Librarian;
import ui.layout.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    // Currently displayed panel
    private JPanel currentPanel;
    // Librarian object (like logged in user)
    private final Librarian librarian;

    // Constructor
    public Window(Librarian librarian) {
        this.librarian = librarian;
        // Initialize the MainWindow components
        initialize();
    }

    // Get librarian method
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
        // Rerun the window with new panel
        revalidate();
        repaint();
    }

    // Method to initialize all components
    public void initialize() {
        // Set window caption text, default close action, window size, window location and disable resizing
        setTitle("Kali Library");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Header and Footer panels creation
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
