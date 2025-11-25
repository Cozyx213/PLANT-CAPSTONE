package com.plantfarmlogger.view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import com.plantfarmlogger.controller.LoginController;
public class LoginForm extends JFrame {
    private JTextField usernameTextField;
    private JButton loginButton;
    private JPanel contentPane;
    private JPasswordField passwordField;

    public LoginForm() {
        super("LoginForm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout(8, 8));

        JPanel fields = new JPanel(new GridLayout(2, 2, 6, 6));
        usernameTextField = new JTextField();
        fields.add(new JLabel("Username:"));
        fields.add(usernameTextField);
        passwordField = new JPasswordField();
        fields.add(new JLabel("Password:"));
        fields.add(passwordField);

        contentPane.add(fields, BorderLayout.CENTER);

        loginButton = new JButton("Login");
        
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        south.add(loginButton);
        
        contentPane.add(south, BorderLayout.SOUTH);


        setContentPane(contentPane);
        
        pack();

        setLocationRelativeTo(null);

        // Use a homemade controller to validate credentials of ze users
        LoginController controller = new LoginController();

        loginButton.addActionListener(e -> {

            String username = usernameTextField.getText();
            char[] password = passwordField.getPassword();

            boolean ok = controller.authenticate(username, password);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Login successful.", "Status", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                JOptionPane.showMessageDialog(this, "Login unsuccessful.", "Status", JOptionPane.ERROR_MESSAGE);
            }

            Arrays.fill(password, '\0'); // clear sensitive data cuz 
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm frame = new LoginForm();
            frame.setSize(500, 500);
            frame.setVisible(true);
        });
    }
}