// Members panel class

package ui.information_panels;

import library.objects.Loan;
import library.objects.Member;
import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MembersPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final ui.Window window;
    // Show back button boolean (does not show on library status panel)
    private final boolean showBackButton;

    public MembersPanel(Window window, boolean showBackButton) {
        // LoansPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Back button boolean
        this.showBackButton = showBackButton;
        // Initialize all MembersPanel components
        initialize();
    }

    // Method to initialize all MembersPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Members");

        // Main panel for member panels:
        // Library's members list
        ArrayList<Member> members = window.getLibrarian().getMembers();
        // Calculate rows by how many loans there are (max 3 books per row)
        int rows = (int) Math.ceil((double) members.size() / 3);
        JPanel membersPanel = PanelHelper.createListPanel(rows, 3);

        // Wrap membersPanel with scrollable panel for scrolling
        JScrollPane scrollableMembersPanel = new JScrollPane(membersPanel);
        scrollableMembersPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableMembersPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableMembersPanel.getVerticalScrollBar().setUnitIncrement(20);

        // If there are no members - show No members label
        if (members.isEmpty()) {
            JPanel noMembersPanel = new JPanel();
            noMembersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            noMembersPanel.setBackground(Color.LIGHT_GRAY);
            JLabel noMembersLabel = new JLabel("No members");
            noMembersLabel.setFont(new Font("Serif", Font.BOLD, 20));
            noMembersPanel.add(noMembersLabel);
            add(noMembersPanel, BorderLayout.CENTER);
        } else {
            // Member panels creation
            for (Member member : members) {
                JPanel panel = new JPanel();
                // Rows change based on number of loans the member has
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

                // If member has loans - show them
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
                    // If member does not have any loans - show No loans label
                    JLabel loansLabel = new JLabel("No loans");
                    panel.add(loansLabel);
                }

                // Wrap member panel with scrollable panel
                // This is to be able to see all loans without changing the panel size
                JScrollPane scrollablePanel = new JScrollPane(panel);
                scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollablePanel.getVerticalScrollBar().setUnitIncrement(15);
                scrollablePanel.setPreferredSize(new Dimension(150, 200));

                // Set color and add scrollable panel or normal panel based on loans
                panel.setBackground(Color.WHITE);
                if (!member.getLoans().isEmpty()) {
                    membersPanel.add(scrollablePanel, BorderLayout.CENTER);
                } else {
                    panel.setPreferredSize(new Dimension(150, 200));
                    membersPanel.add(panel, BorderLayout.CENTER);
                }
            }
        }

        // Button to go Back to main menu
        if (showBackButton)
            PanelHelper.createMainMenuButton(this, _ -> window.switchToPanel(new MainMenu(window)));

        // Add both panels to MembersPanel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollableMembersPanel, BorderLayout.CENTER);
    }

}
