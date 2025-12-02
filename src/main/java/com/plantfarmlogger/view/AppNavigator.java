package com.plantfarmlogger.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.plantfarmlogger.model.User;

public class AppNavigator {

    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    private static final String VIEW_LOGIN = "LOGIN";
    private static final String VIEW_REGISTER = "REGISTER";
    private static final String VIEW_HOME = "HOME";
    private static final String VIEW_CROPLOGS = "CROPLOGS";

    public AppNavigator(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
    }

    public void showLogin() {
        Login view = new Login(this);
        mainPanel.add(view, VIEW_LOGIN);
        cardLayout.show(mainPanel, VIEW_LOGIN);
    }

    public void showRegister() {
        Register view = new Register(this);
        mainPanel.add(view, VIEW_REGISTER);
        cardLayout.show(mainPanel, VIEW_REGISTER);
    }

    public void showHome(User user) {
        Home view = new Home(user, this::logout);
        mainPanel.add(view, VIEW_HOME);
        cardLayout.show(mainPanel, VIEW_HOME);
    }

    public void showCropLogs(User user) {
        CropLog view = new CropLog(user, this::logout);
        mainPanel.add(view, VIEW_CROPLOGS);
        cardLayout.show(mainPanel, VIEW_CROPLOGS);
    }

    public void logout() {
        showLogin();
    }
}