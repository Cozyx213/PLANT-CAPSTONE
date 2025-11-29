package com.plantfarmlogger.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final AppNavigator navigator;

    public MainWindow() {
        // 1. Frame Setup
        setTitle("AniCore Lite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null); // Center on screen
        setResizable(true);

        // 2. Layout Setup
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // 3. Initialize Navigator
        // We pass the panel and layout so the navigator can control them
        navigator = new AppNavigator(mainPanel, cardLayout);

        // 4. Start Application
        navigator.showLogin();

        // Add inside MainWindow.java
    }

    public AppNavigator getNavigator() {
        return navigator;
    }
}