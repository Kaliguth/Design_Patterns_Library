// Loan book panel class

package ui.management_panels;

import library.objects.Book;
import library.objects.Member;
import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoanBookPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final Window window;
    // Combo box objects
    private JComboBox<Book> booksBox;
    private JComboBox<Member> membersBox;

    // Constructor
    public LoanBookPanel(Window window) {
        // LoanBookPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all LoanBookPanel components
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Loan Book button is clicked
        if (command.equals("Loan Book")) {
            // Get the selected book and member
            Book book = (Book) booksBox.getSelectedItem();
            Member member = (Member) membersBox.getSelectedItem();

            // Create and add a loan with selected book and member and remove the book from the combo box
            if (book != null && member != null) {
                window.getLibrarian().loanBook(book, member);
                booksBox.removeItem(book);
            } else {
                // Failure case
                window.getLibrarian().update("No book selected!");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    // Method to initialize all LoanBookPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Loan Books to Members");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Book loan label
        JLabel bookLabel = new JLabel("Book to loan:");
        bookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a combo box with only the books available to loan
        ArrayList<Book> books = window.getLibrarian().getBooks();
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        booksBox = new JComboBox<>(availableBooks.toArray(new Book[0]));
        booksBox.setMaximumSize(new Dimension(620, 30));

        // Panel for member selection
        JLabel memberLabel = new JLabel("Member to loan to:");
        memberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a combo box with all members
        ArrayList<Member> members = window.getLibrarian().getMembers();
        membersBox = new JComboBox<>(members.toArray(new Member[0]));
        membersBox.setMaximumSize(new Dimension(420, 30));

        // Loan button to loan the book
        JButton loanButton = PanelHelper.createButton("Loan Book", this::handleButtonClicks);
        loanButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add all labels, combo boxes and loan button to the form panel
        formPanel.add(bookLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(booksBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(memberLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(membersBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        formPanel.add(loanButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add both panels to LoanBookPanel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}