// Register member panel class

package ui.management_panels;

import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterMemberPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final ui.Window window;
    // Input Text fields
    private JTextField idInput;
    private JTextField nameInput;

    // Constructor
    public RegisterMemberPanel(Window window) {
        // RegisterMemberPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all RegisterMemberPanel components
        initialize();
    }

        // Method to handle button clicks
        private void handleButtonClicks(ActionEvent e) {
            String command = e.getActionCommand();

            // If Register Member button is clicked
            if (command.equals("Register Member")) {
                // Get the input texts
                String idText = idInput.getText();
                String nameText = nameInput.getText();

                // Validate if the ID input is an integer
                try {
                    int memberId = Integer.parseInt(idText);

                    // Check if name is not empty and filled
                    if (!nameText.isEmpty() && !nameText.equals("Name")) {
                        // Create and add the member
                        window.getLibrarian().createMember(memberId, nameText);

                        // Reset the input fields
                        idInput.setText("ID");
                        idInput.setForeground(Color.GRAY);
                        nameInput.setText("Name");
                        nameInput.setForeground(Color.GRAY);
                    } else {
                        // If name input is empty or not filled
                        window.getLibrarian().update("Please fill in all the required fields");
                    }
                } catch (NumberFormatException ex) {
                    // If ID was not a number
                    window.getLibrarian().update("Invalid ID format - Please enter numbers only");
                    // Reset ID input field
                    idInput.setText("ID");
                    idInput.setForeground(Color.GRAY);
                }
                // If Main Menu button is clicked
            } else if (command.equals("Main Menu")) {
                // Change panel to main menu
                window.switchToPanel(new MainMenu(window));
            }
        }

    // Method to initialize all RegisterMemberPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Member Registration");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Enter details label
        JLabel registerLabel = new JLabel("Enter member details:");
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields:
        // ID
        idInput = PanelHelper.createInputBox("ID");
        idInput.setMaximumSize(new Dimension(250, 30));
        // Author
        nameInput = PanelHelper.createInputBox("Name");
        nameInput.setMaximumSize(new Dimension(250, 30));

        // Register Member button to register the member
        JButton registerButton = PanelHelper.createButton("Register Member", this::handleButtonClicks);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add labels, input fields and button to the form panel
        formPanel.add(registerLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(idInput);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(nameInput);
        formPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        formPanel.add(registerButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add both panels to RegisterMemberPanel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}