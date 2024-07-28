package ui;

import library.Book;

import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

// Utility class that holds repetitive methods to help minimize lines of code
public class PanelHelper {

    // Method to create a titled panel with all given buttons (for Main Menu)
    public static JPanel createManagePanel(String title, ArrayList<JButton> buttons, ActionListener listener) {
        // Panel creation and configurations:
        // Set GridLayout with one column and spaces between buttons,
        // Border for the panel with the title and background color
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(buttons.size(), 1, 10, 10));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
        titledBorder.setTitleFont(new Font("Serif", Font.BOLD, 18));
        titledBorder.setTitleColor(Color.BLUE);
        panel.setBorder(titledBorder);
        panel.setBackground(Color.WHITE);

        // Loop to set all buttons and add them to the panel
        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.addActionListener(listener); // Action listener
            button.setActionCommand(button.getText());
            panel.add(button);
        }

        return panel;
    }

    // Method to create a title label panel
    public static JPanel createTitlePanel(String text) {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        titlePanel.setBackground(Color.LIGHT_GRAY);

        // Remove book label
        JLabel title = new JLabel(text);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(Color.BLACK);
        title.setBackground(Color.CYAN);
        title.setOpaque(true); // Used for making background visible
        titlePanel.add(title);

        return titlePanel;
    }

    // Button to go Back to main menu
    public static void createMainMenuButton(JPanel panel, ActionListener listener) {
        JButton backButton = PanelHelper.createButton("Main Menu", listener);
        JPanel backButtonPanel = PanelHelper.createButtonPanel(backButton);
        panel.add(backButtonPanel, BorderLayout.SOUTH);
    }

    // Panels with input methods:
    // Method to create an input box
    public static JTextField createInputBox(String placeholder) {
        JTextField inputBox = new JTextField(15);
        inputBox.setForeground(Color.DARK_GRAY);
        inputBox.setText(placeholder);
        inputBox.setForeground(Color.GRAY);
        // Placeholder listener to handle placeholder text
        inputBox.addFocusListener(placeholderListener(inputBox, placeholder));

        return inputBox;
    }

    // Method to create an input panel (because it was repetitive)
    public static JPanel createInputPanel(JTextField inputBox) {
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(inputBox);

        return panel;
    }

    // Method to handle focus events for placeholder text
    // Found on the internets :)
    public static FocusAdapter placeholderListener(final JTextField textField, final String placeholder) {
        return new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Set text color when typing
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY); // Set placeholder color
                }
            }
        };
    }

    // Method to create a combo box panel

    // Method to create a single button for forms
    public static JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(listener);
        button.setActionCommand(button.getText());

        return button;
    }

    // Method to create a panel of a single button
    public static JPanel createButtonPanel(JButton button) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }

    // Methods for information panels:
    // Method to create a panel for a list of objects
    public static JPanel createListPanel(int rows, int columns) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, columns, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return panel;
    }

}
