package com.plantfarmlogger.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.plantfarmlogger.model.User;

import com.plantfarmlogger.controller.dao.UserDao;;

public class Login extends JFrame {

    private static final Color BG_COLOR = new Color(113, 165, 84);
    private static final Color BUTTON_COLOR = new Color(54, 85, 59);
    private static final Color BUTTON_HOVER_COLOR = new Color(64, 95, 69);
    private static final Color TEXT_FIELD_BG = new Color(220, 220, 220);
    private static final Color TEXT_COLOR = Color.WHITE;

    public Login(AppNavigator an) {
        setTitle("AniCore Lite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel logoLabel = new JLabel("ANICore LITE");
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        logoLabel.setForeground(TEXT_COLOR);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        mainPanel.add(logoLabel, gbc);

        JLabel loginHeader = new JLabel("Log In");
        loginHeader.setFont(new Font("SansSerif", Font.BOLD, 28));
        loginHeader.setForeground(TEXT_COLOR);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainPanel.add(loginHeader, gbc);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(TEXT_COLOR);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel userLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        userLabelPanel.setOpaque(false);
        userLabelPanel.setMaximumSize(new Dimension(300, 20));
        userLabelPanel.add(userLabel);

        RoundedTextField userField = new RoundedTextField(20);
        userField.setMaximumSize(new Dimension(300, 40));
        userField.setPreferredSize(new Dimension(300, 40));

        formPanel.add(userLabelPanel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(userField);
        formPanel.add(Box.createVerticalStrut(15));

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(TEXT_COLOR);
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel passLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        passLabelPanel.setOpaque(false);
        passLabelPanel.setMaximumSize(new Dimension(300, 20));
        passLabelPanel.add(passLabel);

        RoundedPasswordField passField = new RoundedPasswordField(20);
        passField.setMaximumSize(new Dimension(300, 40));
        passField.setPreferredSize(new Dimension(300, 40));

        formPanel.add(passLabelPanel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(passField);
        formPanel.add(Box.createVerticalStrut(5));

        RoundedButton loginBtn = new RoundedButton("Log In");
        loginBtn.setMaximumSize(new Dimension(200, 45));
        loginBtn.setPreferredSize(new Dimension(200, 45));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 16));

        formPanel.add(loginBtn);

        UserDao ud = new UserDao();

        loginBtn.addActionListener(e -> {
            System.out.println(userField.getText());
            System.out.println(passField.getPassword());

            String username = userField.getText();
            char[] password = passField.getPassword();

            User u = ud.authenticate(username, new String(password));
            if (u != null) {

                JOptionPane.showMessageDialog(this, "Login successful.", "Status", JOptionPane.INFORMATION_MESSAGE);
                an.showMain(u);

            } else {
                JOptionPane.showMessageDialog(this, "Login unsuccessful.", "Status", JOptionPane.ERROR_MESSAGE);
            }

        });
        formPanel.add(Box.createVerticalStrut(80));

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(formPanel, gbc);

        // Replace the label with a button styled as a link
        JButton registerBtn = new JButton("<html>No account yet? <u>Create an account</u></html>");
        registerBtn.setForeground(TEXT_COLOR);
        registerBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);

        registerBtn.addActionListener(e -> {
            an.showRegister(); // Assumes AppNavigator has a showRegister() method
            System.out.println("reg");
        });

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(registerBtn, gbc);
    }

    static class RoundedTextField extends JTextField {
        public RoundedTextField(int size) {
            super(size);
            setOpaque(false);
            setBorder(new EmptyBorder(5, 15, 5, 15));
            setFont(new Font("SansSerif", Font.PLAIN, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(TEXT_FIELD_BG);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {

        }
    }

    static class RoundedPasswordField extends JPasswordField {
        public RoundedPasswordField(int size) {
            super(size);
            setOpaque(false);
            setBorder(new EmptyBorder(5, 15, 5, 15));
            setFont(new Font("SansSerif", Font.PLAIN, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(TEXT_FIELD_BG);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {

        }
    }

    static class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.WHITE);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    setForeground(new Color(230, 230, 230));
                }

                public void mouseExited(MouseEvent evt) {
                    setForeground(Color.WHITE);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(BUTTON_COLOR.darker());
            } else if (getModel().isRollover()) {
                g2.setColor(BUTTON_HOVER_COLOR);
            } else {
                g2.setColor(BUTTON_COLOR);
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            g2.dispose();

            super.paintComponent(g);
        }
    }

}