package ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel currentPanel;

    // Constructor
    public Window() {
        // Initialize the MainWindow components
        initialize();
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
        setSize(800, 600);
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
