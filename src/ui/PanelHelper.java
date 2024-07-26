package ui;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

// Utility class that holds repetitive and general methods to help minimize lines of code
public class PanelHelper {

    // Footer methods:
    // Action listener to open GitHub for the buttons
    public static void openGithub(ActionEvent e, String username) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/" + username));
            System.out.println(e.getActionCommand() + "'s GitHub page opened");
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(null,
                    "Website not found!","Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }

    // Main Menu methods:
    // Method to create a titled panel with all given buttons
    public static JPanel createManagePanel(String title, ArrayList<JButton> buttons, ActionListener listener) {
        // Panel creation and configurations:
        // Set GridLayout with one column and spaces between buttons,
        // Border for the panel with the title and background color
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(buttons.size(), 1, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(title));
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

    // Panels with input methods:
    // Method to create an input panel (because it was repetitive)
    public static JPanel createInputPanel(String placeholder) {
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.setBackground(Color.LIGHT_GRAY);
        JTextField textField = new JTextField(placeholder);
        textField.setForeground(Color.DARK_GRAY);
        // Placeholder listener to handle placeholder text
        textField.addFocusListener(placeholderListener(textField, placeholder));

        textField.setPreferredSize(new Dimension(200, 30));
        panel.add(textField);

        return panel;
    }

    // Method to handle focus events for placeholder text
    // Found on the internets :)
    private static FocusAdapter placeholderListener(final JTextField textField, final String placeholder) {
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

    // Method to create a panel of a single button
    public static JPanel createButtonPanel(String text, ActionListener listener) {
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.setBackground(Color.LIGHT_GRAY);
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(listener); // Action listener
        button.setActionCommand(button.getText());
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }

}
