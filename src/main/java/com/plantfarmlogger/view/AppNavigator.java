package com.plantfarmlogger.view;

import com.plantfarmlogger.view.Login;
import com.plantfarmlogger.view.Home;
import com.plantfarmlogger.model.User;
import javax.swing.*;
import java.awt.*;

public class AppNavigator {
    private JFrame current;

    public void showLogin() {
        SwingUtilities.invokeLater(() -> {
            disposeCurrent();
            Login login = new Login(this);
            current = login;
            login.setVisible(true);
        });
    }

    public void showRegister() {
        SwingUtilities.invokeLater(() -> {
            disposeCurrent();
            // implement Register form later and call it here:
            // RegisterForm reg = new RegisterForm(this);
            // current = reg;
            // reg.setVisible(true);
            JOptionPane.showMessageDialog(null, "Register not implemented yet");
        });
    }

    public void showMain(User u) {
        SwingUtilities.invokeLater(() -> {
            disposeCurrent();
            Home main = new Home(u);
            current = main;
            main.setVisible(true);
        });
    }

    private void disposeCurrent() {
        if (current != null) {
            current.dispose();
            current = null;
        }
    }
}