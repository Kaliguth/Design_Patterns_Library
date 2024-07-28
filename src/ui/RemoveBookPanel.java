package ui;

import library.Book;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveBookPanel extends JPanel {
    private final Window window;
    private JComboBox<Book> booksBox;

    public RemoveBookPanel(Window window) {
        // Set RemoveBookPanel layout fit for spaces
        super(new BorderLayout());
        // Use main window object for button clicks (switching panels)
        this.window = window;
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Remove button is clicked
        if (command.equals("Remove")) {
            Book bookToRemove = (Book) booksBox.getSelectedItem();
            if (bookToRemove != null) {
                window.getLibrarian().removeBook(bookToRemove);
                booksBox.removeItem(bookToRemove);
            } else {
                window.getLibrarian().update("No book selected!");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Remove Book");

        // Create a panel for the select box and remove button
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 5, 5));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Select box to select a book to remove
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(Color.LIGHT_GRAY);
        ArrayList<Book> books = window.getLibrarian().getBooks();
        booksBox = new JComboBox<>(books.toArray(new Book[0]));
        booksBox.setPreferredSize(new Dimension(620, 30));

        comboBoxPanel.add(booksBox);
        formPanel.add(comboBoxPanel);

        // Remove button to remove the book
        JButton removeBookButton = PanelHelper.createButton("Remove", this::handleButtonClicks);
        JPanel removeBookPanel = PanelHelper.createButtonPanel(removeBookButton);
        formPanel.add(removeBookPanel);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);


        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}