package com.plantfarmlogger.view;

import com.plantfarmlogger.controller.dao.UserDao;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class Login extends JPanel {

    private final AppNavigator navigator;
    private final JTextField userField;
    private final JPasswordField passField;

    public Login(AppNavigator navigator) {
        this.navigator = navigator;

        setLayout(new GridBagLayout());
        setBackground(UIColors.BG_COLOR_GENERAL); // Use Factory Color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. Logo
        JLabel logoLabel = new JLabel("ANiCore LITE");
        logoLabel.setFont(UIFont.lexend(Font.BOLD, 32));
        logoLabel.setForeground(Color.WHITE);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(logoLabel, gbc);

        // 2. Header
        JLabel loginHeader = new JLabel("Log In");
        loginHeader.setFont(UIFont.lexend(Font.BOLD, 28));
        loginHeader.setForeground(Color.WHITE);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(loginHeader, gbc);

        // 3. Form Container
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        // Username
        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(UIFont.lexend(Font.BOLD, 12));

        JPanel userLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        userLabelPanel.setOpaque(false);
        userLabelPanel.setMaximumSize(new Dimension(300, 20));
        userLabelPanel.add(userLabel);

        userField = UIFields.createRoundedField();
        // Enforce size consistency
        userField.setMaximumSize(new Dimension(300, 40));
        userField.setPreferredSize(new Dimension(300, 40));

        formPanel.add(userLabelPanel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(userField);
        formPanel.add(Box.createVerticalStrut(15));

        // Password
        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(UIFont.lexend(Font.BOLD, 12));

        JPanel passLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        passLabelPanel.setOpaque(false);
        passLabelPanel.setMaximumSize(new Dimension(300, 20));
        passLabelPanel.add(passLabel);

        passField = UIFields.createRoundedPasswordField();
        passField.setMaximumSize(new Dimension(300, 40));
        passField.setPreferredSize(new Dimension(300, 40));

        formPanel.add(passLabelPanel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(passField);
        formPanel.add(Box.createVerticalStrut(30)); // Gap before button

        // Login Button
        JButton loginBtn = UIButtons.createRoundedButton("Log In");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setFont(UIFont.lexend(Font.BOLD, 16));
        loginBtn.setPreferredSize(new Dimension(220, 50));
        // Override size for login specifically if needed, or keep factory default
        loginBtn.setMaximumSize(new Dimension(200, 45));
        loginBtn.setPreferredSize(new Dimension(200, 45));

        loginBtn.addActionListener(_ -> performLogin());

        formPanel.add(loginBtn);
        formPanel.add(Box.createVerticalStrut(30)); // Gap before link

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(formPanel, gbc);

        // 4. Register Link (No HTML)
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        linkPanel.setOpaque(false);

        JLabel noAccountLbl = new JLabel("No account yet?");
        noAccountLbl.setFont(UIFont.lexend(Font.PLAIN, 12));
        noAccountLbl.setForeground(Color.WHITE);

        JLabel createAccountLbl = new JLabel("Create an account");
        createAccountLbl.setFont(UIFont.lexend(Font.PLAIN, 12));
        createAccountLbl.setForeground(Color.WHITE);
        createAccountLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) createAccountLbl.getFont().getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        createAccountLbl.setFont(createAccountLbl.getFont().deriveFont(attributes));

        createAccountLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigator.showRegister();
            }
        });

        linkPanel.add(noAccountLbl);
        linkPanel.add(createAccountLbl);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(linkPanel, gbc);
    }

    private void performLogin() {
        String username = userField.getText();
        char[] password = passField.getPassword();

        UserDao ud = new UserDao();
        User u = ud.authenticate(username, new String(password));

        if (u != null) {
            JOptionPane.showMessageDialog(this, "Login successful.", "Status", JOptionPane.INFORMATION_MESSAGE);
            navigator.showHome(u); // Using showHome from AppNavigator
        } else {
            JOptionPane.showMessageDialog(this, "Login unsuccessful.", "Status", JOptionPane.ERROR_MESSAGE);
        }
    }
}