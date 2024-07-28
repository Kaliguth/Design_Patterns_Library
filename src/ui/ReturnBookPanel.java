package ui;

import library.Loan;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReturnBookPanel extends JPanel {
    private final Window window;
    private JComboBox<Loan> loansBox;

    public ReturnBookPanel(Window window) {
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
        if (command.equals("Return Book")) {
            // Get the input texts
            Loan loan = (Loan) loansBox.getSelectedItem();
            if (loan != null) {
                window.getLibrarian().returnBook(loan.getBook(), loan.getMember());
                loansBox.removeItem(loan);
            } else {
                window.getLibrarian().update("No loan selected!");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Return Book");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel for loan selection
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
        returnPanel.setBackground(Color.LIGHT_GRAY);
        JLabel returnLabel = new JLabel("Book loan to return:");
        returnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ArrayList<Loan> loans = window.getLibrarian().getLoans();
        loansBox = new JComboBox<>(loans.toArray(new Loan[0]));
        loansBox.setMaximumSize(new Dimension(620, 30));
        returnPanel.add(returnLabel);
        returnPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        returnPanel.add(loansBox);
        returnPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        // Return book button to return the book
        JButton returnBookButtonButton = PanelHelper.createButton("Return Book", this::handleButtonClicks);
        JPanel returnBookButtonPanel = PanelHelper.createButtonPanel(returnBookButtonButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        formPanel.add(returnPanel);
        formPanel.add(returnBookButtonPanel);


        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}