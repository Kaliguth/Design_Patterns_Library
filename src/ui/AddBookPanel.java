package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBookPanel extends JPanel {
    private final Window window;

    public AddBookPanel(Window window) {
        // Set the layout fit for spaces
        super(new BorderLayout());
        // Use main window object for button clicks (switching panels)
        this.window = window;
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClick(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Main Menu")) {
                window.switchToPanel(new MainMenu(window));
        }
    }

    public void initialize() {
        setLayout(new BorderLayout());

        // Title Panel in the top center for the title text
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        titlePanel.setBackground(Color.LIGHT_GRAY);

        // Add book label
        JLabel title = new JLabel("Add book");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(Color.BLACK);
        title.setBackground(Color.CYAN);
        title.setOpaque(true); // Used for making background visible
        titlePanel.add(title);

        // Create a panel for input fields and labels
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1, 5, 5));
        inputPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        // Title label and input field
        JPanel titleInputPanel = PanelHelper.createInputPanel("Title");

        // Author label and input field
        JPanel authorInputPanel = PanelHelper.createInputPanel("Author");

        // Publish Date label and input field
        JPanel publishDateInputPanel = PanelHelper.createInputPanel("Publish Date");

        // Add a button to submit the form
        JPanel addBookButtonPanel = PanelHelper.createButtonPanel("Submit",
                this::handleButtonClick);

        // Button to go Back to main menu
        JPanel backButtonPanel = PanelHelper.createButtonPanel("Main Menu",
                this::handleButtonClick);

        // Add labels and input fields to the input panel
        inputPanel.add(titleInputPanel);
        inputPanel.add(authorInputPanel);
        inputPanel.add(publishDateInputPanel);
        inputPanel.add(addBookButtonPanel);
        inputPanel.add(backButtonPanel);


        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
    }

}