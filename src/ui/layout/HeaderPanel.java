// Header panel class

package ui.layout;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    // Window object to handle closing on X button click
    private final Window window;

    // Constructor
    public HeaderPanel(Window window) {
        // Header layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all header components
        initialize();
    }

    // Method to initialize all header components
    public void initialize() {
        // Empty panel in the left of the header panel for alignment of components
        JPanel emptyLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emptyLeftPanel.setBackground(Color.ORANGE);
        // Set preferred size to push the title to the center
        emptyLeftPanel.setPreferredSize(new Dimension(70, 0));

        // Title panel in the center of header panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.ORANGE);
        JLabel title = new JLabel("Kali Library");
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
        titlePanel.add(title);

        // Exit button panel in the right of the header panel
        JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        exitButtonPanel.setBackground(Color.ORANGE);
        JButton exitBtn = new JButton("X");
        exitBtn.setFont(new Font("Serif", Font.BOLD, 14));
        exitBtn.setBackground(Color.RED);
        exitBtn.setForeground(Color.DARK_GRAY);
        exitBtn.setOpaque(true); // Used for making background visible
        exitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exitBtn.addActionListener(_ -> window.dispose()); // Action listener to close the application
        exitButtonPanel.add(exitBtn);

        // Add all panels into the header panel
        add(emptyLeftPanel, BorderLayout.WEST);
        add(titlePanel, BorderLayout.CENTER);
        add(exitButtonPanel, BorderLayout.EAST);
    }

}
