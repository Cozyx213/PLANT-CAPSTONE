package com.plantfarmlogger.view;


import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFields;
import com.plantfarmlogger.util.UIFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;


public class Register extends JPanel {

    private final AppNavigator navigator;

    private JTextField nameField, usernameField, ageField, farmField;
    private JTextField addressField;
    private JPasswordField passField, confirmPassField;

    public Register(AppNavigator navigator) {
        this.navigator = navigator;
        java.net.URL imageUrl = getClass().getResource("/logo_lite.png");
        setLayout(new GridBagLayout());
        setBackground(UIColors.BG_COLOR_GENERAL);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel logoLabel = new JLabel("ANiCore LITE");
        if (imageUrl != null) {
            ImageIcon logoIcon = new ImageIcon(imageUrl);
            Image image = logoIcon.getImage();
            Image scaledLogoImage = image.getScaledInstance(200, 30, Image.SCALE_SMOOTH);
            ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
            logoLabel = new JLabel(scaledLogoIcon);
        } else {
            System.err.println("Error: Image resource not found at /files/logo.png");
            logoLabel.setForeground(UIColors.BG_COLOR);
            logoLabel.setFont(UIFont.lexend(Font.BOLD, 26));
        }
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        add(logoLabel, gbc);

        JLabel title = new JLabel("Create Account");
        title.setFont(UIFont.lexend(Font.BOLD, 24));
        title.setForeground(UIColors.TEXT_COLOR);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(title, gbc);

        JPanel columnsPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        columnsPanel.setOpaque(false);

        JPanel leftPanel = createColumnPanel();
        nameField = addFieldToPanel(leftPanel, "Name");
        usernameField = addFieldToPanel(leftPanel, "Username");
        ageField = addFieldToPanel(leftPanel, "Age");
        farmField = addFieldToPanel(leftPanel, "Farm Name");

        JPanel rightPanel = createColumnPanel();
        addressField = addFieldToPanel(rightPanel, "Address");
        passField = (JPasswordField) addPasswordFieldToPanel(rightPanel, "Password", false);
        confirmPassField = (JPasswordField) addPasswordFieldToPanel(rightPanel, "Confirm Password", true);

        rightPanel.add(Box.createVerticalStrut(80));
        rightPanel.add(Box.createVerticalGlue());
        columnsPanel.add(leftPanel);
        columnsPanel.add(rightPanel);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(columnsPanel, gbc);

        JButton regBtn = UIButtons.createRoundedButton("Sign Up");
        regBtn.setFont(UIFont.lexend(Font.BOLD, 16));
        regBtn.setPreferredSize(new Dimension(220, 50));
        regBtn.addActionListener(e -> onRegisterClicked());
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(regBtn, gbc);

        JLabel loginLink = createLoginLink();
        gbc.gridy = 4;
        add(loginLink, gbc);
    }

    private JPanel createColumnPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JLabel createLoginLink() {
        JLabel loginLink = new JLabel("Already have an account? Log In");
        loginLink.setFont(UIFont.lexend(Font.PLAIN, 12));
        loginLink.setForeground(UIColors.TEXT_COLOR);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) loginLink.getFont().getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        loginLink.setFont(loginLink.getFont().deriveFont(attributes));

        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigator.showLogin();
            }
        });

        return loginLink;
    }

    private JTextField addFieldToPanel(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setForeground(UIColors.TEXT_COLOR);
        label.setFont(UIFont.lexend(Font.BOLD, 12));

        JPanel labelWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelWrapper.setOpaque(false);
        labelWrapper.add(label);

        JTextField field = UIFields.createRoundedField();

        panel.add(labelWrapper);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));

        return field;
    }

    private JTextField addPasswordFieldToPanel(JPanel panel, String labelText, boolean isLast) {
        JLabel label = new JLabel(labelText);
        label.setForeground(UIColors.TEXT_COLOR);
        label.setFont(UIFont.lexend(Font.BOLD, 12));

        JPanel labelWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelWrapper.setOpaque(false);
        labelWrapper.add(label);

        JPasswordField field = UIFields.createRoundedPasswordField();


        panel.add(labelWrapper);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        if (!isLast) panel.add(Box.createVerticalStrut(15));

        return field;
    }

    //making the background a gradient
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to ensure proper painting
        Graphics2D g2d = (Graphics2D) g;
        // Define start and end colors
        Color startColor = new Color(76, 139, 63); // Steel Blue
        Color endColor = new Color(102, 177, 75); // Light Blue

        // Create a vertical gradient from top to bottom
        GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);

        // Set the paint and fill the rectangle
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void onRegisterClicked() {
        navigator.showLogin();

    }
}

