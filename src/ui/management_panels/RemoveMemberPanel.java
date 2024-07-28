// Remove member panel class

package ui.management_panels;

import library.objects.Member;
import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveMemberPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final ui.Window window;
    // Combo box object
    private JComboBox<Member> membersBox;

    // Constructor
    public RemoveMemberPanel(Window window) {
        // RemoveMemberPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all RemoveMemberPanel components
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Remove Member button is clicked
        if (command.equals("Remove Member")) {
            // Get the selected member
            Member memberToRemove = (Member) membersBox.getSelectedItem();

            // Remove the member from the library and the combo box only if it doesn't have loans
            if (memberToRemove != null) {
                if (memberToRemove.getLoans().isEmpty()) {
                    window.getLibrarian().removeMember(memberToRemove);
                    membersBox.removeItem(memberToRemove);
                } else {
                    // If member has loans
                    window.getLibrarian().update("Can't remove members with loans!");
                }
            } else {
                window.getLibrarian().update("No member selected!");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    // Method to initialize all RemoveMemberPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Member Removal");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Select a member label
        JLabel removeLabel = new JLabel("Select a member to remove:");
        removeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a combo box with all library's members
        ArrayList<Member> books = window.getLibrarian().getMembers();
        membersBox = new JComboBox<>(books.toArray(new Member[0]));
        membersBox.setMaximumSize(new Dimension(420, 30));

        // Remove Member button to remove the member
        JButton removeButton = PanelHelper.createButton("Remove Member", this::handleButtonClicks);
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the label, combo box and remove button to the form panel
        formPanel.add(removeLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(membersBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        formPanel.add(removeButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);


        // Add both panels to RemoveMemberPanel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}