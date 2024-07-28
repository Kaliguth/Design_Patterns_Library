package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterMemberPanel extends JPanel {
    private final Window window;
    private JTextField idInput;
    private JTextField nameInput;

    public RegisterMemberPanel(Window window) {
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
            if (command.equals("Register")) {
                // Get the input texts
                String idText = idInput.getText();
                String nameText = nameInput.getText();

                // Validate if the ID input is an integer
                try {
                    int memberId = Integer.parseInt(idText);

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

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Register Member");

        // Create a panel for input fields and labels
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 5, 5));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        // Input fields - seperated to different panels for positioning
        // Title input field and panel
        idInput = PanelHelper.createInputBox("ID");
        JPanel titleInputPanel = PanelHelper.createInputPanel(idInput);

        // Author input field and panel
        nameInput = PanelHelper.createInputBox("Name");
        JPanel authorInputPanel = PanelHelper.createInputPanel(nameInput);

        // Submit button to add the book
        JButton addBookButton = PanelHelper.createButton("Register", this::handleButtonClicks);
        JPanel addBookButtonPanel = PanelHelper.createButtonPanel(addBookButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add labels and input fields to the input panel
        formPanel.add(titleInputPanel);
        formPanel.add(authorInputPanel);
        formPanel.add(addBookButtonPanel);


        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}