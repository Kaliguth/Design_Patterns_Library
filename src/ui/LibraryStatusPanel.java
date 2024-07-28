package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryStatusPanel extends JPanel {
    private final Window window;

    public LibraryStatusPanel(Window window) {
        // Set the layout fit for spaces
        super(new BorderLayout());
        // Use main window object for button clicks (switching panels)
        this.window = window;
        initialize();
    }

    // Method to handle button clicks
    private void handleButtonClicks(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Main Menu")) {
            // Change panel to main menu
            window.switchToPanel(new MainMenu(window));
        }
    }

    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Library Status");

        // Create a panel for library info panels
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBackground(Color.LIGHT_GRAY);

        // Wrap statusPanel with scrollable panel
        JScrollPane scrollableStatusPanel = new JScrollPane(statusPanel);
        scrollableStatusPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableStatusPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableStatusPanel.getVerticalScrollBar().setUnitIncrement(20);

        // Panel to show books
        JPanel booksPanel = new BooksPanel(window, false);
        // Panel to show members
        JPanel membersPanel = new MembersPanel(window, false);
        // Panel to show loans
        JPanel loansPanel = new LoansPanel(window, false);
        // Add panels to library status panel
        statusPanel.add(booksPanel);
        statusPanel.add(membersPanel);
        statusPanel.add(loansPanel);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, this::handleButtonClicks);

        // Add the input panel and title to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollableStatusPanel, BorderLayout.CENTER);
    }

}
