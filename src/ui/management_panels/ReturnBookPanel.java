// Return book panel class

package ui.management_panels;

import library.objects.Loan;
import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReturnBookPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final ui.Window window;
    // Combo box object
    private JComboBox<Loan> loansBox;

    // Constructor
    public ReturnBookPanel(Window window) {
        // ReturnBookPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all ReturnBookPanel components
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Return Book button is clicked
        if (command.equals("Return Book")) {
            // Get the input texts
            Loan loan = (Loan) loansBox.getSelectedItem();

            // Return the book and remove it from the combo box
            if (loan != null) {
                window.getLibrarian().returnBook(loan.getBook(), loan.getMember());
                loansBox.removeItem(loan);
            } else {
                window.getLibrarian().update("No book selected!");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    // Method to initialize all ReturnBookPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Return Loaned Books");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Select a book label
        JLabel returnLabel = new JLabel("Select a book to return:");
        returnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a combo box with all library's loans
        ArrayList<Loan> loans = window.getLibrarian().getLoans();
        loansBox = new JComboBox<>(loans.toArray(new Loan[0]));
        loansBox.setMaximumSize(new Dimension(720, 30));

        // Return Book button to return the book
        JButton returnButton = PanelHelper.createButton("Return Book", this::handleButtonClicks);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the label, combo box and return button to the form panel
        formPanel.add(returnLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(loansBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        formPanel.add(returnButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add both panels to ReturnBookPanel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}