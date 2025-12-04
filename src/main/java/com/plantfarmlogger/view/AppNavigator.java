package com.plantfarmlogger.view;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.croplog.CropLogController;
import com.plantfarmlogger.view.home.HomeController;
import com.plantfarmlogger.view.home.HomeView;

import javax.swing.*;
import java.awt.*;


// mura ni siyag router, mag connect sa different modules without knowing their internal logic

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
        // standalone view for now
        Login view = new Login(this);
        mainPanel.add(view, VIEW_LOGIN);
        cardLayout.show(mainPanel, VIEW_LOGIN);
    }

    public void showRegister() {
        // standalone view for now
        Register view = new Register(this);
        mainPanel.add(view, VIEW_REGISTER);
        cardLayout.show(mainPanel, VIEW_REGISTER);
    }

    public void showHome(User user) {
        // Pass 'this' so HomeController can call navigator.showCropLogs()
        HomeController controller = new HomeController(user, this);

        JPanel homeView = controller.getView();
        mainPanel.add(homeView, VIEW_HOME);
        cardLayout.show(mainPanel, VIEW_HOME);
    }


    public void showCropLogs(User user) {
        // waiting to be refactored
        CropLogController controller = new CropLogController(user);
        mainPanel.add(controller.getView(), VIEW_CROPLOGS);
        cardLayout.show(mainPanel, VIEW_CROPLOGS);
    }

    // Inside AppNavigator class
    public void showCropLogs(User user, String cropName) {
        // 1. Create the controller
        com.plantfarmlogger.view.croplog.CropLogController controller
                = new com.plantfarmlogger.view.croplog.CropLogController(user);

        // 2. Inject the specific crop data
        controller.setCropName(cropName);

        // 3. Show the view
        mainPanel.add(controller.getView(), VIEW_CROPLOGS);
        cardLayout.show(mainPanel, VIEW_CROPLOGS);
    }

    public void logout() {
        showLogin();
    }
}