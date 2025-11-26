package com.plantfarmlogger.view;

import javax.swing.*;
import java.awt.*;
import com.plantfarmlogger.model.User;

public class SideBar extends JFrame {
    private JButton addCropBed;
    private JButton changeName;
    private JButton changeAddress;
    private JButton changePass;
    private JButton logOut;
    private JPanel contentPane;
    private User user;

    public SideBar() {
        super("LoginForm");
        addCropBed = new JButton("Add Crop Bed");

        //edit to get user
        this.user = new User("John Doe", "johndoe", "123", "Cebu Institute of Technology, Cebu City", 24);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel(new GridLayout(5, 1));

        JPanel userInfoPanel = new JPanel (new GridLayout(3, 1));
        userInfoPanel.setSize(230, 200);
        userInfoPanel.add(new JLabel(user.getName()));
        userInfoPanel.add(new JLabel(user.getUsername()));
        userInfoPanel.add(new JLabel("<html>" + user.getAddress() + "</html>"));

        JPanel cropBeds = new JPanel (new GridLayout(0,1));
        cropBeds.setSize(230, 200);
        cropBeds.add(new JLabel("Crop Beds"));
        //edit to make loop for number of crop beds
        cropBeds.add(new JLabel("Kamote 1"));
        cropBeds.add(new JLabel("Kamote 1"));
        cropBeds.add(new JLabel("Kamote 1"));

        JPanel cropsPanel = new JPanel (new BorderLayout(2, 2));
        cropsPanel.add(cropBeds, BorderLayout.WEST);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(this.addCropBed);
        addCropBed.setMaximumSize(new Dimension(20,20));
        cropsPanel.add(buttonWrapper, BorderLayout.EAST);

        //SETTINGS PANEL
        this.changeName = new JButton("Change Name");
        this.changePass = new JButton("Change Password");
        this.changeAddress = new JButton("Change Address");

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setSize(200, 500);
        settingsPanel.add(new JLabel("Settings"));
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeName);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeAddress);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changePass);

        logOut = new JButton("Log Out");

        contentPane.add(userInfoPanel);
        contentPane.add(cropsPanel);
        contentPane.add(settingsPanel);
        contentPane.add(logOut);
        //contentPane.add(buttonWrapper);
        this.setContentPane(contentPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SideBar frame = new SideBar();
            frame.setSize(230, 800);
            frame.setVisible(true);
        });
    }
}
