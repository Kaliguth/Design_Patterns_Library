package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBookPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final Window window;
    // Input Text fields
    private JTextField titleInput;
    private JTextField authorInput;
    private JTextField publishDateInput;

    // Constructor
    public AddBookPanel(Window window) {
        // Set the layout fit for spaces
        super(new BorderLayout());
        this.window = window;
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Submit button is clicked
        if (command.equals("Submit")) {
            // Check if the inputs are not empty and filled
            if (!titleInput.getText().isEmpty() && !titleInput.getText().equals("Title") &&
                    !authorInput.getText().isEmpty() && !authorInput.getText().equals("Author") &&
                    !publishDateInput.getText().isEmpty() && !publishDateInput.getText().equals("Publish Date")) {
                // Get the input texts
                String bookTitle = titleInput.getText();
                String bookAuthor = authorInput.getText();
                String bookPublishDate = publishDateInput.getText();
                // Create and add the book
                window.getLibrarian().createBook(bookTitle, bookAuthor, bookPublishDate);
                // Reset the input fields
                titleInput.setText("Title");
                titleInput.setForeground(Color.GRAY);
                authorInput.setText("Author");
                authorInput.setForeground(Color.GRAY);
                publishDateInput.setText("Publish Date");
                publishDateInput.setForeground(Color.GRAY);
            } else {
                // If any of the inputs is empty or not filled
                window.getLibrarian().update("Please fill in all the required fields");
            }
            // If Main Menu button is clicked
        } else if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Add Book");

        // Create a panel for input fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 5, 5));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        // Input fields - seperated to different panels for positioning
        // Title input field and panel
        titleInput = PanelHelper.createInputBox("Title");
        JPanel titleInputPanel = PanelHelper.createInputPanel(titleInput);

        // Author input field and panel
        authorInput = PanelHelper.createInputBox("Author");
        JPanel authorInputPanel = PanelHelper.createInputPanel(authorInput);

        // Publish date input filed and panel
        publishDateInput = PanelHelper.createInputBox("Publish Date");
        JPanel publishDateInputPanel = PanelHelper.createInputPanel(publishDateInput);

        // Submit button to add the book
        JButton addBookButton = PanelHelper.createButton("Submit", this::handleButtonClicks);
        JPanel addBookButtonPanel = PanelHelper.createButtonPanel(addBookButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add labels and input fields to the input panel
        formPanel.add(titleInputPanel);
        formPanel.add(authorInputPanel);
        formPanel.add(publishDateInputPanel);
        formPanel.add(addBookButtonPanel);


        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}