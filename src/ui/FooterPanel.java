package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FooterPanel extends JPanel {

    public FooterPanel() {
        // Footer layout
        super(new BorderLayout());
        setBackground(Color.ORANGE);

        // Footer left panel with credit text
        JPanel footerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        footerLeft.setBackground(Color.ORANGE);
        JLabel credit = new JLabel("Project created by Einav Kohavi & Tamar Pick");
        credit.setFont(new Font("Serif", Font.BOLD, 16));
        credit.setForeground(Color.BLACK);
        footerLeft.add(credit, BorderLayout.WEST);

        // Footer right panel with GitHub buttons
        JPanel footerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        footerRight.setBackground(Color.ORANGE);
        // GitHub links label
        JLabel githubLabel = new JLabel("Github links:");
        githubLabel.setFont(new Font("Serif", Font.BOLD, 16));
        githubLabel.setForeground(Color.BLACK);
        footerRight.add(githubLabel, BorderLayout.EAST);
        // GitHub buttons:
        // Einav
        JButton kaliBtn = new JButton("Einav");
        kaliBtn.setFont(new Font("Serif", Font.BOLD, 14));
        kaliBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        kaliBtn.addActionListener(e -> openGithub(e, "Kaliguth"));
        footerRight.add(kaliBtn, BorderLayout.EAST);
        // Tamar
        JButton tamarBtn = new JButton("Tamar");
        tamarBtn.setFont(new Font("Serif", Font.BOLD, 14));
        tamarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tamarBtn.addActionListener(e -> openGithub(e, "Tamarpick"));
        footerRight.add(tamarBtn, BorderLayout.EAST);

        // Add both panels into the footer panel
        add(footerLeft, BorderLayout.WEST);
        add(footerRight, BorderLayout.EAST);
    }

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

}
