package ui;

import library.Loan;
import library.Member;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MembersPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final Window window;
    // Show back button boolean
    private final boolean showBackButton;

    public MembersPanel(Window window, boolean showBackButton) {
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
        JPanel titlePanel = PanelHelper.createTitlePanel("Members");

        // Create a panel for member panels
        ArrayList<Member> members = window.getLibrarian().getMembers(); // Library's members list
        int rows = (int) Math.ceil((double) members.size() / 3);
        JPanel membersPanel = PanelHelper.createListPanel(rows, 3);

        // Wrap members panel with scrollable panel
        JScrollPane scrollableMembersPanel = new JScrollPane(membersPanel);
        scrollableMembersPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableMembersPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableMembersPanel.getVerticalScrollBar().setUnitIncrement(20);

        // Members list
        for (Member member : members) {
            JPanel panel = new JPanel();
            int memberPanelRows = member.getLoans().isEmpty() ? 5 : 3 + (member.getLoans().size() * 3);
            panel.setLayout(new GridLayout(memberPanelRows, 1, 10, 10));
            TitledBorder titledBorder = BorderFactory.createTitledBorder("Member " + member.getMemberNum());
            titledBorder.setTitleFont(new Font("Serif", Font.BOLD, 18));
            titledBorder.setTitleColor(Color.BLUE);
            panel.setBorder(titledBorder);

            JLabel idLabel = new JLabel("ID: " + member.getId());
            JLabel nameLabel = new JLabel("Name: " + member.getName());
            panel.add(idLabel);
            panel.add(nameLabel);

            if (!member.getLoans().isEmpty()) {
                JLabel loansLabel = new JLabel("Loans:");
                panel.add(loansLabel);
                for (Loan loan : member.getLoans()) {
                    JLabel bookLabel = new JLabel("Book: \"" + loan.getBook().getTitle() + "\"");
                    JLabel loanDateLabel = new JLabel("Loan Date: " + loan.getLoanDate());
                    JLabel returnDateLabel = new JLabel("Return Date: " + loan.getReturnDate());
                    panel.add(bookLabel);
                    panel.add(loanDateLabel);
                    panel.add(returnDateLabel);
                }
            } else {
                JLabel loansLabel = new JLabel("No loans");
                panel.add(loansLabel);
            }

            // Wrap member panel with scrollable panel
            JScrollPane scrollablePanel = new JScrollPane(panel);
            scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollablePanel.getVerticalScrollBar().setUnitIncrement(15);
            scrollablePanel.setPreferredSize(new Dimension(150, 200));

            panel.setBackground(Color.WHITE);
            if (!member.getLoans().isEmpty()) {
                membersPanel.add(scrollablePanel, BorderLayout.CENTER);
            } else {
                panel.setPreferredSize(new Dimension(150, 200));
                membersPanel.add(panel, BorderLayout.CENTER);
            }
        }

        // Button to go Back to main menu
        if (showBackButton)
            PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollableMembersPanel, BorderLayout.CENTER);
    }

}
