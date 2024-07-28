package ui;

import library.Loan;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LoansPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final Window window;
    // Show back button boolean
    private final boolean showBackButton;

    public LoansPanel(Window window, boolean showBackButton) {
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
        JPanel titlePanel = PanelHelper.createTitlePanel("Loans");

        // Create a panel for loan panels
        ArrayList<Loan> loans = window.getLibrarian().getLoans(); // Library's loans list
        int rows = (int) Math.ceil((double) loans.size() / 3);
        JPanel loansPanel = PanelHelper.createListPanel(rows, 3);

        // Wrap loansPanel with scrollable panel
        JScrollPane scrollableLoansPanel = new JScrollPane(loansPanel);
        scrollableLoansPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableLoansPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableLoansPanel.getVerticalScrollBar().setUnitIncrement(20);

        // Loans list
        if (loans.isEmpty()) {
            JPanel noLoansPanel = new JPanel();
            noLoansPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            noLoansPanel.setBackground(Color.LIGHT_GRAY);
            JLabel noLoansLabel = new JLabel("No loans");
            noLoansLabel.setFont(new Font("Serif", Font.BOLD, 20));
            noLoansPanel.add(noLoansLabel);
            add(noLoansPanel, BorderLayout.CENTER);
        } else {
            for (Loan loan : loans) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4, 1, 10, 10));
                TitledBorder titledBorder = BorderFactory.createTitledBorder("Loan " + loan.getId());
                titledBorder.setTitleFont(new Font("Serif", Font.BOLD, 18));
                titledBorder.setTitleColor(Color.BLUE);
                panel.setBorder(titledBorder);
                JLabel bookLabel = new JLabel("\"" + loan.getBook().getTitle() + "\"");
                JLabel memberLabel = new JLabel("Loaned to member " +
                        loan.getMember().getMemberNum() + " - " + loan.getMember().getName());
                JLabel loanDateLabel = new JLabel("Loan Date: " + loan.getLoanDate());
                JLabel returnDateLabel = new JLabel("Return Date: " + loan.getLoanDate());
                panel.add(bookLabel);
                panel.add(memberLabel);
                panel.add(loanDateLabel);
                panel.add(returnDateLabel);
                panel.setBackground(Color.WHITE);
                loansPanel.add(panel, BorderLayout.CENTER);
            }
            add(scrollableLoansPanel, BorderLayout.CENTER);
        }

        // Button to go Back to main menu
        if (showBackButton) {
            PanelHelper.createMainMenuButton(this, this::handleButtonClicks);
        }

        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
    }

}
