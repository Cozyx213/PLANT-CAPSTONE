package com.plantfarmlogger.view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.plantfarmlogger.controller.dao.UserDao;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFields;
import com.plantfarmlogger.util.UIFont;

public class Register extends JPanel {

    private static final Dimension FIELD_SIZE = new Dimension(280, 40);

    private final AppNavigator navigator;

    // Left Column
    private JTextField nameField, usernameField, ageField;
    // Right Column
    private JTextField addressField;
    private JPasswordField passField, confirmPassField;

    public Register(AppNavigator navigator) {
        this.navigator = navigator;

        setLayout(new GridBagLayout());
        setBackground(UIColors.BG_COLOR_GENERAL);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. Logo
        JLabel logo = new JLabel("ANiCore LITE");
        logo.setFont(UIFont.lexend(Font.BOLD, 32));
        logo.setForeground(UIColors.TEXT_COLOR);

        gbc.gridy = 0;
        add(logo, gbc);

        // 2. Title
        JLabel title = new JLabel("Create Account");
        title.setFont(UIFont.lexend(Font.BOLD, 24));
        title.setForeground(UIColors.TEXT_COLOR);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(title, gbc);

        // 3. Main Form Container (Two Columns)
        JPanel columnsPanel = new JPanel(new GridLayout(1, 2, 40, 0)); // 1 row, 2 cols, 40px gap
        columnsPanel.setOpaque(false);

        // --- LEFT COLUMN ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        nameField = addFieldToPanel(leftPanel, "Name");
        usernameField = addFieldToPanel(leftPanel, "Username");
        ageField = addFieldToPanel(leftPanel, "Age");
      
        // --- RIGHT COLUMN ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);

        addressField = addFieldToPanel(rightPanel, "Address");
        passField = (JPasswordField) addPasswordFieldToPanel(rightPanel, "Password", false);
        confirmPassField = (JPasswordField) addPasswordFieldToPanel(rightPanel, "Confirm Password", true);

        // Add dummy spacer to right panel to match height if fields are uneven
        rightPanel.add(Box.createVerticalGlue());

        columnsPanel.add(leftPanel);
        columnsPanel.add(rightPanel);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(columnsPanel, gbc);

        // 4. Register Button
        JButton regBtn = UIButtons.createRoundedButton("Sign Up");
        regBtn.setFont(UIFont.lexend(Font.BOLD, 16));
        regBtn.setPreferredSize(new Dimension(220, 50));
        regBtn.addActionListener(e -> performRegister());
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(regBtn, gbc);

        // 5. Back to Login Link
        JLabel loginLink = new JLabel("Already have an account? Log In");
        loginLink.setFont(UIFont.lexend(Font.PLAIN, 12));
        loginLink.setForeground(UIColors.TEXT_COLOR);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Underline effect
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) loginLink.getFont().getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        loginLink.setFont(loginLink.getFont().deriveFont(attributes));

        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigator.showLogin();
            }
        });

        gbc.gridy = 4;
        add(loginLink, gbc);
    }

    // Helper to add label + field cleanly
    private JTextField addFieldToPanel(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setForeground(UIColors.TEXT_COLOR);
        label.setFont(UIFont.lexend(Font.BOLD, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel labelP = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelP.setOpaque(false);
        labelP.add(label);
        labelP.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField field = UIFields.createRoundedField();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setPreferredSize(FIELD_SIZE);
        field.setMaximumSize(FIELD_SIZE);

        panel.add(labelP);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));

        return field;
    }

    // Helper for password fields
    private JTextField addPasswordFieldToPanel(JPanel panel, String labelText, boolean isConfirm) {
        JLabel label = new JLabel(labelText);
        label.setForeground(UIColors.TEXT_COLOR);
        label.setFont(UIFont.lexend(Font.BOLD, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel labelP = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelP.setOpaque(false);
        labelP.add(label);
        labelP.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPasswordField field = UIFields.createRoundedPasswordField();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setAlignmentY(Component.TOP_ALIGNMENT);

        field.setPreferredSize(FIELD_SIZE);
        field.setMaximumSize(FIELD_SIZE);

        panel.add(labelP);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        // Add extra space if it's not the last field
        if (!isConfirm)
            panel.add(Box.createVerticalStrut(15));

        return field;
    }

    private void performRegister() {
        String name = nameField.getText();
        String username = usernameField.getText();
        String ageStr = ageField.getText();
        String address = addressField.getText();
        String pass = new String(passField.getPassword());
        String confirmPass = new String(confirmPassField.getPassword());

        if (name.isEmpty() || username.isEmpty() || ageStr.isEmpty() || address.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (!pass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Age.");
            return;
        }

        User newUser = new User(name, username, address, age, pass);
        UserDao dao = new UserDao();
        dao.create(newUser);

        JOptionPane.showMessageDialog(this, "Account Created! Please Login.");
        navigator.showLogin();
    }
}