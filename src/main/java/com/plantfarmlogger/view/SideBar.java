package com.plantfarmlogger.view;

import javax.swing.*;
import java.awt.*;
import com.plantfarmlogger.model.User;

public class SideBar extends JPanel {

    private JButton addCropBed;
    private JButton changeName;
    private JButton changeAddress;
    private JButton changePass;
    private JButton logOut;
    private User user;

    public SideBar() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(260, 800));   // sidebar width
        setBackground(new Color(27, 115, 56));       // green background (match your UI)

        addCropBed = new JButton("+ Add Crop Bed");

        // sample user
        this.user = new User("John Doe", "johndoe", "123",
                "Dela Rosa Street, Greenbelt\nRadissons, Metro Manila, Makati, Philippines", 24);

        // -------------------------
        // USER INFO PANEL
        // -------------------------
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(getBackground());
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel(user.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        JLabel usernameLabel = new JLabel("@" + user.getUsername());
        usernameLabel.setForeground(Color.WHITE);

        JLabel addressLabel = new JLabel("<html>" + user.getAddress() + "</html>");
        addressLabel.setForeground(Color.WHITE);

        userInfoPanel.add(nameLabel);
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(Box.createVerticalStrut(10));
        userInfoPanel.add(addressLabel);

        // -------------------------
        // CROP BEDS LIST
        // -------------------------
        JPanel cropBedsPanel = new JPanel();
        cropBedsPanel.setLayout(new BoxLayout(cropBedsPanel, BoxLayout.Y_AXIS));
        cropBedsPanel.setBackground(getBackground());
        cropBedsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel cropTitle = new JLabel("Crop Beds");
        cropTitle.setForeground(Color.WHITE);
        cropTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        cropBedsPanel.add(cropTitle);

        // Example entries
        cropBedsPanel.add(makeCropLink("Kamote 1"));
        cropBedsPanel.add(makeCropLink("Carrot 1"));
        cropBedsPanel.add(makeCropLink("Malunggay 1"));

        // add crop button
        addCropBed.setAlignmentX(Component.LEFT_ALIGNMENT);
        cropBedsPanel.add(Box.createVerticalStrut(10));
        cropBedsPanel.add(addCropBed);

        // -------------------------
        // SETTINGS PANEL
        // -------------------------
        changeName = new JButton("Change Name");
        changePass = new JButton("Change Password");
        changeAddress = new JButton("Change Address");
        logOut = new JButton("Log Out");

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBackground(getBackground());
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setForeground(Color.WHITE);
        settingsLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        settingsPanel.add(settingsLabel);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeName);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeAddress);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changePass);

        // logout
        logOut.setAlignmentX(Component.LEFT_ALIGNMENT);

        // -------------------------
        // ADD ALL SECTIONS TO SIDEBAR
        // -------------------------
        add(userInfoPanel);
        add(cropBedsPanel);
        add(settingsPanel);
        add(Box.createVerticalGlue());
        add(logOut);
        add(Box.createVerticalStrut(20));
    }

<<<<<<< HEAD
    private JLabel makeCropLink(String name) {
        JLabel label = new JLabel("<html><u>" + name + "</u></html>");
        label.setForeground(Color.WHITE);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
=======
      static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SideBar frame = new SideBar();
            frame.setSize(230, 800);
            frame.setVisible(true);
        });
>>>>>>> ec84d0965676d945030e8c2faa7d1504c2d7abb1
    }
}
