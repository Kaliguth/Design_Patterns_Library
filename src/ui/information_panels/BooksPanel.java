// Books info panel

package ui.information_panels;

import library.objects.Book;
import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BooksPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final Window window;
    // Show back button boolean (does not show on library status panel)
    private final boolean showBackButton;

    // Constructor
    public BooksPanel(Window window, boolean showBackButton) {
        // Books panel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Back button boolean
        this.showBackButton = showBackButton;
        // Initialize all BooksPanel components
        initialize();
    }

    // Method to initialize all BooksPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Books");

        // Main panel for book panels:
        // Library's books list
        ArrayList<Book> books = window.getLibrarian().getBooks();
        // Calculate rows by how many books there are (max 3 books per row)
        int rows = (int) Math.ceil((double) books.size() / 3);
        JPanel booksPanel = PanelHelper.createListPanel(rows, 3);

        // Wrap booksPanel with scrollable panel for scrolling
        JScrollPane scrollableBooksPanel = new JScrollPane(booksPanel);
        scrollableBooksPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableBooksPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableBooksPanel.getVerticalScrollBar().setUnitIncrement(20);

        // If there are no books - show No books label
        if (books.isEmpty()) {
            JPanel noBooksPanel = new JPanel();
            noBooksPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            noBooksPanel.setBackground(Color.LIGHT_GRAY);
            JLabel noBooksLabel = new JLabel("No books");
            noBooksLabel.setFont(new Font("Serif", Font.BOLD, 20));
            noBooksPanel.add(noBooksLabel);
            add(noBooksPanel, BorderLayout.CENTER);
        } else {
            // Book panels creation
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
            PanelHelper.createMainMenuButton(this, _ -> window.switchToPanel(new MainMenu(window)));

        // Add both panels to BooksPanel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollableBooksPanel, BorderLayout.CENTER);
    }

}
