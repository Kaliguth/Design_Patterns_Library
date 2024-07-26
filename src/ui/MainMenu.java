package ui;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel {
    private final Window window;

    // Constructor
    public MainMenu(Window window) {
        // Set the layout fit for spaces
        super.setLayout(new BorderLayout());
        // Use main window object for button clicks (switching panels)
        this.window = window;
        // Initialize all MainMenu components
        initialize();
    }

    // Method to handle button clicks and switch to the right panel
    private void handleButtonClick(ActionEvent e) {
        String command = e.getActionCommand();
        JPanel newPanel;

        switch (command) {
            case "Add Book":
                newPanel = new AddBookPanel(window);
                break;
//            case "Remove Book":
//                newPanel = new RemoveBookPanel();
//                break;
//            case "Register Member":
//                newPanel = new RegisterMemberPanel();
//                break;
//            case "Remove Member":
//                newPanel = new RemoveMemberPanel();
//                break;
//            case "Loan Book":
//                newPanel = new LoanBookPanel();
//                break;
//            case "Return Book":
//                newPanel = new ReturnBookPanel();
//                break;
//            case "Show Books":
//                newPanel = new ShowBooksPanel();
//                break;
//            case "Show Members":
//                newPanel = new ShowMembersPanel();
//                break;
//            case "Show Loans":
//                newPanel = new ShowLoansPanel();
//                break;
//            case "Show Library Status":
//                newPanel = new ShowLibraryStatusPanel();
//                break;
            default:
                newPanel = new MainMenu(window);
        }

        window.switchToPanel(newPanel);
    }

    // Method to initialize all MainMenu components
    public void initialize() {
        // Title Panel in the top center for the title text
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        titlePanel.setBackground(Color.LIGHT_GRAY);

        // MainMenu label
        JLabel title = new JLabel("Main Menu");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(Color.BLACK);
        title.setBackground(Color.CYAN);
        title.setOpaque(true); // Used for making background visible
        titlePanel.add(title);

        // 2x2 grid panel for management panels
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 2, 20, 20)); // 2 rows, 2 columns, 20px gaps
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
