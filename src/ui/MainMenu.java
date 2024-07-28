// Main menu class

package ui;

import ui.information_panels.*;
import ui.management_panels.*;
import ui.util.PanelHelper;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel {
    // Window object to handle button clicks (switching panels)
    private final Window window;

    // Constructor
    public MainMenu(Window window) {
        // Main menu layout
        super.setLayout(new BorderLayout());
        // Window object
        this.window = window;
        // Initialize all MainMenu components
        initialize();
    }

    // Method to handle button clicks and switch to the right panel
    private void handleButtonClick(ActionEvent e) {
        String command = e.getActionCommand();
        JPanel newPanel = switch (command) {
            case "Add Book" -> new AddBookPanel(window);
            case "Remove Book" -> new RemoveBookPanel(window);
            case "Register Member" -> new RegisterMemberPanel(window);
            case "Remove Member" -> new RemoveMemberPanel(window);
            case "Loan Book" -> new LoanBookPanel(window);
            case "Return Book" -> new ReturnBookPanel(window);
            case "Show Books" -> new BooksPanel(window, true);
            case "Show Members" -> new MembersPanel(window, true);
            case "Show Loans" -> new LoansPanel(window, true);
            case "Show Library Status" -> new LibraryStatusPanel(window);
            default -> new MainMenu(window);
        };

        window.switchToPanel(newPanel);
    }

    // Method to initialize all MainMenu components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = PanelHelper.createTitlePanel("Main Menu");

        // 2x2 grid panel for management panels
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 2, 20, 20));
        gridPanel.setBackground(Color.LIGHT_GRAY);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        // Panels and buttons:
        // Manage books panel
        ArrayList<JButton> manageBooksButtons = new ArrayList<>();
        manageBooksButtons.add(new JButton("Add Book"));
        manageBooksButtons.add(new JButton("Remove Book"));
        JPanel manageBooksPanel = PanelHelper.createManagePanel("Manage Books",
                manageBooksButtons, this::handleButtonClick);

        // Manage members panel
        ArrayList<JButton> manageMembersButtons = new ArrayList<>();
        manageMembersButtons.add(new JButton("Register Member"));
        manageMembersButtons.add(new JButton("Remove Member"));
        JPanel manageMembersPanel = PanelHelper.createManagePanel("Manage Members",
                manageMembersButtons, this::handleButtonClick);

        // Manage loans panel
        ArrayList<JButton> manageLoansButtons = new ArrayList<>();
        manageLoansButtons.add(new JButton("Loan Book"));
        manageLoansButtons.add(new JButton("Return Book"));
        JPanel manageLoansPanel = PanelHelper.createManagePanel("Manage Loans",
                manageLoansButtons, this::handleButtonClick);

        // Library information panel
        ArrayList<JButton> libraryInfoButtons = new ArrayList<>();
        libraryInfoButtons.add(new JButton("Show Books"));
        libraryInfoButtons.add(new JButton("Show Members"));
        libraryInfoButtons.add(new JButton("Show Loans"));
        libraryInfoButtons.add(new JButton("Show Library Status"));
        JPanel libraryInfoPanel = PanelHelper.createManagePanel("Library Info",
                libraryInfoButtons, this::handleButtonClick);

        // Add all management panels to the grid panel
        gridPanel.add(manageBooksPanel);
        gridPanel.add(manageMembersPanel);
        gridPanel.add(manageLoansPanel);
        gridPanel.add(libraryInfoPanel);

        // Add both panels to the MainMenu panel
        add(titlePanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }

}
