// Loans panel class

package ui.information_panels;

import library.objects.Loan;
import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LoansPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final ui.Window window;
    // Show back button boolean (does not show on library status panel)
    private final boolean showBackButton;

    // Constructor
    public LoansPanel(Window window, boolean showBackButton) {
        // LoansPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Back button boolean
        this.showBackButton = showBackButton;
        // Initialize all LoansPanel components
        initialize();
    }

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Loans");

        // Main panel for loan panels:
        // Library's loans list
        ArrayList<Loan> loans = window.getLibrarian().getLoans();
        // Calculate rows by how many loans there are (max 3 books per row)
        int rows = (int) Math.ceil((double) loans.size() / 3);
        JPanel loansPanel = PanelHelper.createListPanel(rows, 3);

        // Wrap loansPanel with scrollable panel for scrolling
        JScrollPane scrollableLoansPanel = new JScrollPane(loansPanel);
        scrollableLoansPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableLoansPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableLoansPanel.getVerticalScrollBar().setUnitIncrement(20);

        // If there are no loans - show No loans label
        if (loans.isEmpty()) {
            JPanel noLoansPanel = new JPanel();
            noLoansPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            noLoansPanel.setBackground(Color.LIGHT_GRAY);
            JLabel noLoansLabel = new JLabel("No loans");
            noLoansLabel.setFont(new Font("Serif", Font.BOLD, 20));
            noLoansPanel.add(noLoansLabel);
            add(noLoansPanel, BorderLayout.CENTER);
        } else {
            // Loan panels creation
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

            // Add scrollable panel to LoansPanel
            add(scrollableLoansPanel, BorderLayout.CENTER);
        }

        // Button to go Back to main menu
        if (showBackButton) {
            PanelHelper.createMainMenuButton(this, _ -> window.switchToPanel(new MainMenu(window)));
        }

        // Add title panel to LoansPanel
        add(titlePanel, BorderLayout.NORTH);
    }

}
