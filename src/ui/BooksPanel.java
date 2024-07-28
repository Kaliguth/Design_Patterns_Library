package ui;

import library.Book;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BooksPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final Window window;
    // Show back button boolean
    private final boolean showBackButton;

    public BooksPanel(Window window, boolean showBackButton) {
        // Set the layout fit for spaces
        super(new BorderLayout());
        this.window = window;
        this.showBackButton = showBackButton;
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Books");

        // Create a panel for book panels
        ArrayList<Book> books = window.getLibrarian().getBooks(); // Library's books list
        int rows = (int) Math.ceil((double) books.size() / 3);
        JPanel booksPanel = PanelHelper.createListPanel(rows, 3);

        // Wrap booksPanel with scrollable panel
        JScrollPane scrollableBooksPanel = new JScrollPane(booksPanel);
        scrollableBooksPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableBooksPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableBooksPanel.getVerticalScrollBar().setUnitIncrement(20);

        // Books list
        if (books.isEmpty()) {
            JPanel noBooksPanel = new JPanel();
            noBooksPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            noBooksPanel.setBackground(Color.LIGHT_GRAY);
            JLabel noBooksLabel = new JLabel("No books");
            noBooksLabel.setFont(new Font("Serif", Font.BOLD, 20));
            noBooksPanel.add(noBooksLabel);
            add(noBooksPanel, BorderLayout.CENTER);
        } else {
            for (Book book : books) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 1, 10, 10));
                TitledBorder titledBorder = BorderFactory.createTitledBorder(book.getTitle());
                titledBorder.setTitleFont(new Font("Serif", Font.BOLD, 18));
                titledBorder.setTitleColor(Color.BLUE);
                panel.setBorder(titledBorder);
                JLabel authorLabel = new JLabel("Author: " + book.getAuthor());
                JLabel publishDateLabel = new JLabel("Publish Date: " + book.getPublishDate());
                JLabel isAvailableLabel = new JLabel("Available: " + (book.isAvailable() ? "Yes" : "No"));
                panel.add(authorLabel);
                panel.add(publishDateLabel);
                panel.add(isAvailableLabel);
                panel.setBackground(Color.WHITE);
                booksPanel.add(panel);
            }
        }

        // Button to go Back to main menu
        if (showBackButton)
            PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollableBooksPanel, BorderLayout.CENTER);
    }

}
