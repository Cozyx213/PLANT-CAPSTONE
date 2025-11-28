package com.plantfarmlogger.view;

import com.plantfarmlogger.model.User;

import javax.swing.*;

public class AppNavigator {
    private JFrame current;

    public void showLogin() {
        // SwingUtilities.invokeLater(() -> {
        disposeCurrent();
        Login login = new Login(this);
        current = login;
        login.setVisible(true);
        // });
    }

    public void showRegister() {
        disposeCurrent();
        Register reg = new Register(this);
        current = reg;
        reg.setVisible(true);

    }

    public void showMain(User u) {
        disposeCurrent();
        Home main = new Home(u);
        current = main;
        main.setVisible(true);

    }

    private void disposeCurrent() {
        if (current != null) {
            current.dispose();
            current = null;
        }
    }
}