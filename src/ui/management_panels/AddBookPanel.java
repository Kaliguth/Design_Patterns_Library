package ui.management_panels;

import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

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
        // AddBookPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all AddBookPanel components
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        // If Add Book button is clicked
        if (command.equals("Add Book")) {
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

    // Method to initialize all AddBookPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("New Book Creation");

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Input label
        JLabel addBookLabel = new JLabel("Enter book details:");
        addBookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields:
        // Title
        titleInput = PanelHelper.createInputBox("Title");
        titleInput.setMaximumSize(new Dimension(250, 30));
        // Author
        authorInput = PanelHelper.createInputBox("Author");
        authorInput.setMaximumSize(new Dimension(250, 30));
        // Publish date
        publishDateInput = PanelHelper.createInputBox("Publish Date");
        publishDateInput.setMaximumSize(new Dimension(250, 30));

        // Add Book button to add the book
        JButton addButton = PanelHelper.createButton("Add Book", this::handleButtonClicks);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add label, input fields and button to the form panel
        formPanel.add(addBookLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(titleInput);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(authorInput);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(publishDateInput);
        formPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        formPanel.add(addButton);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add both panels to AddBookPanel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

}