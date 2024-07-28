// Remove book panel class

package ui.management_panels;

import library.objects.Book;
import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveBookPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final Window window;
    // Combo box object
    private JComboBox<Book> booksBox;

    // Constructor
    public RemoveBookPanel(Window window) {
        // RemoveBookPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all RemoveBookPanel components
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Remove Book button is clicked
        if (command.equals("Remove Book")) {
            // Get the selected book
            Book bookToRemove = (Book) booksBox.getSelectedItem();

            // Remove the book from the library and combo box only if it's not loaned
            if (bookToRemove != null) {
                if (bookToRemove.isAvailable()) {
                window.getLibrarian().removeBook(bookToRemove);
                booksBox.removeItem(bookToRemove);
                } else {
                    // If book is loaned
                    window.getLibrarian().update("Can't remove loaned books!");
                }
            } else {
                window.getLibrarian().update("No book selected!");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    // Method to initialize all RemoveBookPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Book Removal");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Select a book label
        JLabel removeLabel = new JLabel("Select a book to remove:");
        removeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a combo box with all library books
        ArrayList<Book> books = window.getLibrarian().getBooks();
        booksBox = new JComboBox<>(books.toArray(new Book[0]));
        booksBox.setMaximumSize(new Dimension(620, 30));

        // Remove Book button to remove the book
        JButton removeButton = PanelHelper.createButton("Remove Book", this::handleButtonClicks);
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the label, combo box and remove button to the form panel
        formPanel.add(removeLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(booksBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        formPanel.add(removeButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);


        // Add both panels to RemoveBookPanel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}