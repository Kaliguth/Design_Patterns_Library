package ui;

import library.Member;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveMemberPanel extends JPanel {
    private final Window window;
    private JComboBox<Member> membersBox;

    public RemoveMemberPanel(Window window) {
        // Set RemoveMemberPanel layout fit for spaces
        super(new BorderLayout());
        // Use main window object for button clicks (switching panels)
        this.window = window;
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Remove button is clicked
        if (command.equals("Remove")) {
            Member memberToRemove = (Member) membersBox.getSelectedItem();
            if (memberToRemove != null) {
                window.getLibrarian().removeMember(memberToRemove);
                membersBox.removeItem(memberToRemove);
            } else {
                window.getLibrarian().update("No member selected!");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Remove Member");

        // Create a panel for the select box and remove button
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 5, 5));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Select box to select a book to remove
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(Color.LIGHT_GRAY);
        ArrayList<Member> books = window.getLibrarian().getMembers();
        membersBox = new JComboBox<>(books.toArray(new Member[0]));
        membersBox.setPreferredSize(new Dimension(420, 30));

        comboBoxPanel.add(membersBox);
        formPanel.add(comboBoxPanel);

        // Remove button to remove the member
        JButton removeMemberButton = PanelHelper.createButton("Remove", this::handleButtonClicks);
        JPanel removeMemberPanel = PanelHelper.createButtonPanel(removeMemberButton);
        formPanel.add(removeMemberPanel);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);


        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}