package com.plantfarmlogger.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JFrame {

    private static final Color BG_COLOR = new Color(113, 165, 84);
    private static final Color BUTTON_COLOR = new Color(54, 85, 59);
    private static final Color BUTTON_HOVER_COLOR = new Color(64, 95, 69);
    private static final Color TEXT_FIELD_BG = new Color(220, 220, 220);
    private static final Color TEXT_COLOR = Color.WHITE;

    public Register(AppNavigator an) {
        setTitle("AniCore Lite - Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BG_COLOR);
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel logoLabel = new JLabel("ANICore LITE");
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        logoLabel.setForeground(TEXT_COLOR);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(logoLabel, gbc);

        JLabel headerLabel = new JLabel("Create Account");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        headerLabel.setForeground(TEXT_COLOR);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainPanel.add(headerLabel, gbc);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        addField(formPanel, "Name");
        addField(formPanel, "Username");


        addPasswordField(formPanel, "Password");
        addPasswordField(formPanel, "Confirm Password");

        RoundedButton signUpBtn = new RoundedButton("Sign Up");
        signUpBtn.setMaximumSize(new Dimension(200, 45));
        signUpBtn.setPreferredSize(new Dimension(200, 45));
        signUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpBtn.setFont(new Font("SansSerif", Font.BOLD, 16));

        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(signUpBtn);
        formPanel.add(Box.createVerticalStrut(40));

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(formPanel, gbc);

        JButton footerLabel = new JButton("<html>Already have an account? <u>Log In</u></html>");
        footerLabel.setForeground(TEXT_COLOR);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        footerLabel.setContentAreaFilled(false);
        footerLabel.setBorderPainted(false);
        footerLabel.setFocusPainted(false);

        footerLabel.addActionListener(e ->{
            an.showLogin();
        });
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(footerLabel, gbc);
    }

    private void addField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setForeground(TEXT_COLOR);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.setOpaque(false);
        labelPanel.setMaximumSize(new Dimension(300, 20));
        labelPanel.add(label);

        RoundedTextField field = new RoundedTextField(20);
        field.setMaximumSize(new Dimension(300, 40));
        field.setPreferredSize(new Dimension(300, 40));

        panel.add(labelPanel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
    }

    private void addPasswordField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setForeground(TEXT_COLOR);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.setOpaque(false);
        labelPanel.setMaximumSize(new Dimension(300, 20));
        labelPanel.add(label);

        RoundedPasswordField field = new RoundedPasswordField(20);
        field.setMaximumSize(new Dimension(300, 40));
        field.setPreferredSize(new Dimension(300, 40));

        panel.add(labelPanel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
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
            setBorder(new EmptyBorder(7, 15, 7, 15));
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
            if (getModel().isPressed())
                g2.setColor(BUTTON_COLOR.darker());
            else if (getModel().isRollover())
                g2.setColor(BUTTON_HOVER_COLOR);
            else
                g2.setColor(BUTTON_COLOR);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
