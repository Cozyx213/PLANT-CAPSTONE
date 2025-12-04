package com.plantfarmlogger.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final AppNavigator navigator;

    public MainWindow() {
        setTitle("AniCore Lite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(true);

        // layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // navigation
        navigator = new AppNavigator(mainPanel, cardLayout);

        // first view will always be login
        // wala pay user kay molog-in pa man
        navigator.showLogin();
    }

    public AppNavigator getNavigator() {
        return navigator;
    }
}