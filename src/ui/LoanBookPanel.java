package ui;

import library.Book;
import library.Member;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoanBookPanel extends JPanel {
    private final Window window;
    private JComboBox<Book> booksBox;
    private JComboBox<Member> membersBox;

    public LoanBookPanel(Window window) {
        // Set the layout fit for spaces
        super(new BorderLayout());
        // Use main window object for button clicks (switching panels)
        this.window = window;
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Submit button is clicked
        if (command.equals("Loan")) {
            // Get the input texts
            Book book = (Book) booksBox.getSelectedItem();
            Member member = (Member) membersBox.getSelectedItem();
            if (book != null && member != null) {
                window.getLibrarian().loanBook(book, member);
                booksBox.removeItem(book);
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
        JPanel titlePanel = PanelHelper.createTitlePanel("Loan Book");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel for book selection
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBackground(Color.LIGHT_GRAY);
        JLabel bookLabel = new JLabel("Book to loan:");
        bookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ArrayList<Book> books = window.getLibrarian().getBooks();
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        booksBox = new JComboBox<>(availableBooks.toArray(new Book[0]));
        booksBox.setMaximumSize(new Dimension(620, 30));
        bookPanel.add(bookLabel);
        bookPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        bookPanel.add(booksBox);
        bookPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel for member selection (if needed)
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        memberPanel.setBackground(Color.LIGHT_GRAY);
        JLabel memberLabel = new JLabel("Member to loan to:");
        memberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ArrayList<Member> members = window.getLibrarian().getMembers();
        membersBox = new JComboBox<>(members.toArray(new Member[0]));
        membersBox.setMaximumSize(new Dimension(420, 30));
        memberPanel.add(memberLabel);
        memberPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        memberPanel.add(membersBox);
        memberPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Loan button to loan the book
        JButton loanBookButtonButton = PanelHelper.createButton("Loan", this::handleButtonClicks);
        JPanel loanBookButtonPanel = PanelHelper.createButtonPanel(loanBookButtonButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        formPanel.add(bookPanel);
        formPanel.add(memberPanel);
        formPanel.add(loanBookButtonPanel);


        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}