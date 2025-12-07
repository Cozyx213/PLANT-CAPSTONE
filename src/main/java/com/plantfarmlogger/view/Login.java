package com.plantfarmlogger.view;

import com.plantfarmlogger.controller.UserController;
import com.plantfarmlogger.controller.dao.UserDao;
import com.plantfarmlogger.model.User;

import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFields;
import com.plantfarmlogger.util.UIFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Arrays;
import java.util.Map;


public class Login extends JPanel {

    private final AppNavigator navigator;
    private final JTextField userField;
    private final JPasswordField passField;

    public Login(AppNavigator navigator) {
        java.net.URL imageUrl = getClass().getResource("/logo_lite.png");
        this.navigator = navigator;

        setLayout(new GridBagLayout());
        setBackground(UIColors.BG_COLOR_GENERAL);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
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
//        logoLabel.setFont(UIFont.lexend(Font.BOLD, 32));
//        logoLabel.setForeground(Color.WHITE);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(logoLabel, gbc);

        JLabel loginHeader = new JLabel("Log In");
        loginHeader.setFont(UIFont.lexend(Font.BOLD, 28));
        loginHeader.setForeground(Color.WHITE);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(loginHeader, gbc);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(UIFont.lexend(Font.BOLD, 12));

        JPanel userLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        userLabelPanel.setOpaque(false);
        userLabelPanel.setMaximumSize(new Dimension(300, 20));
        userLabelPanel.add(userLabel);

        userField = UIFields.createRoundedField();

        userField.setMaximumSize(new Dimension(300, 40));
        userField.setPreferredSize(new Dimension(300, 40));

        formPanel.add(userLabelPanel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(userField);
        formPanel.add(Box.createVerticalStrut(15));

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
        formPanel.add(Box.createVerticalStrut(30));

        JButton loginBtn = UIButtons.createRoundedButton("Log In");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setFont(UIFont.lexend(Font.BOLD, 16));
        loginBtn.setPreferredSize(new Dimension(220, 50));

        loginBtn.setMaximumSize(new Dimension(200, 45));
        loginBtn.setPreferredSize(new Dimension(200, 45));

        loginBtn.addActionListener(event -> performLogin());

        formPanel.add(loginBtn);
        formPanel.add(Box.createVerticalStrut(30));

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(formPanel, gbc);

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

    private void performLogin() {
        String username = userField.getText();
        char[] password = passField.getPassword();
        String passwordStr = new String(password);
        UserController userController = UserController.getInstance();
        User u = userController.authenticateLogin(username, passwordStr);
        Arrays.fill(password, '0');

        if (u != null) {
            JOptionPane.showMessageDialog(this, "Login successful.", "Status", JOptionPane.INFORMATION_MESSAGE);
            navigator.showHome(u);

        } else {
            JOptionPane.showMessageDialog(this, "Login unsuccessful.", "Status", JOptionPane.ERROR_MESSAGE);
        }
    }
}