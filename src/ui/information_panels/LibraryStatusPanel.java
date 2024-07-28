// Library status panel class

package ui.information_panels;

import ui.Window;
import ui.MainMenu;
import ui.util.PanelHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryStatusPanel extends JPanel {
    // Use main window object for button clicks (switching panels)
    private final ui.Window window;

    public LibraryStatusPanel(Window window) {
        // LibraryStatusPanel layout
        super(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all LibraryStatusPanel components
        initialize();
    }

    // Method to initialize all LibraryStatusPanel components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Library Status");

        // Create a panel for library info panels (books, members and loans)
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBackground(Color.LIGHT_GRAY);

        // Wrap statusPanel with scrollable panel for scrolling
        JScrollPane scrollableStatusPanel = new JScrollPane(statusPanel);
        scrollableStatusPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableStatusPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableStatusPanel.getVerticalScrollBar().setUnitIncrement(20);

        // Info panels without main menu buttons:
        // Books panel
        JPanel booksPanel = new BooksPanel(window, false);
        // Members panel
        JPanel membersPanel = new MembersPanel(window, false);
        // Loans panel
        JPanel loansPanel = new LoansPanel(window, false);

        // Add panels to status panel
        statusPanel.add(booksPanel);
        statusPanel.add(membersPanel);
        statusPanel.add(loansPanel);

        // Button to go Back to main menu
        PanelHelper.createMainMenuButton(this, _ -> window.switchToPanel(new MainMenu(window)));

        // Add both panels to LibraryStatusPanel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollableStatusPanel, BorderLayout.CENTER);
    }

}
