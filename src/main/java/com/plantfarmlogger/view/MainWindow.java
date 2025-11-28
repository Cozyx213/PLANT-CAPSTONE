package com.plantfarmlogger.view;

import javax.swing.*;

import com.plantfarmlogger.view.SideBar;
import com.plantfarmlogger.view.home_dashboard;

public class MainWindow extends JFrame {
    public MainWindow() {
       
    JFrame frame = new JFrame("AniCore Lite Sidebar");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the new SideBar JPanel instance
    SideBar sideBarPanel = new SideBar();

    // Add the JPanel to the JFrame's content pane
    frame.getContentPane().add(sideBarPanel);

    frame.setSize(300, 800);
    frame.setMinimumSize(new Dimension(300, 600));
    frame.setVisible(true);
    }
    
}
