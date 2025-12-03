package com.plantfarmlogger.view.components;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import java.io.File;
import java.util.Enumeration;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.Home;
import com.plantfarmlogger.view.MainWindow;

// Change from 'extends JFrame' to 'extends JPanel'
public class SideBar extends JPanel {
    private final JButton addCropBed;
    private final JButton changeName;
    private final JButton changeAddress;
    private final JButton changePass;
    private final JButton logOut;

    public SideBar(User user) {
        java.net.URL imageUrl = getClass().getResource("src/main/java/resources/logo.png");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File(".").getAbsolutePath());

        // The contentPane setup is now the setup for the SideBar JPanel itself
        // (this) refers to the SideBar JPanel
        setLayout(new GridBagLayout());
        setBackground(UIColors.BG_COLOR_GENERAL);
        setMaximumSize(new Dimension(400, 720));
        setMinimumSize(new Dimension(400, 720));
        setFont(UIFont.lexend(Font.BOLD, 20));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setSize(230, 200);
        userInfoPanel.setOpaque(false);

        JLabel logoLabel = new JLabel("ANiCore LITE");
        if (imageUrl != null) {
            ImageIcon logoIcon = new ImageIcon(imageUrl);
            Image image = logoIcon.getImage();
            Image scaledLogoImage = image.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
            logoLabel = new JLabel(scaledLogoIcon);
        } else {
            System.err.println("Error: Image resource not found at /files/logo.png");
            logoLabel.setForeground(UIColors.BG_COLOR);
            logoLabel.setFont(UIFont.lexend(Font.BOLD, 26));
        }

//        JLabel logoLabel = new JLabel("ANICore LITE");
//        logoLabel.setForeground(TEXT_COLOR);

        JLabel uName = new JLabel(user.getName());
        uName.setForeground(UIColors.TEXT_COLOR);
        uName.setBorder(new EmptyBorder(new Insets(20, 0, 5, 0)));
        uName.setFont(UIFont.lexend(Font.BOLD, 20));
        userInfoPanel.add(uName);

        JLabel uUsername = new JLabel("@" + user.getUsername());
        uUsername.setForeground(UIColors.SUB_TEXT_COLOR);
        uUsername.setBorder(new EmptyBorder(new Insets(0, 0, 30, 0)));
        uUsername.setFont(UIFont.lexend(Font.BOLD, 20));
        userInfoPanel.add(uUsername);

        JLabel uAddress = new JLabel("<html>" + user.getAddress() + "</html>");
        uAddress.setForeground(UIColors.SUB_TEXT_COLOR);
        uAddress.setFont(UIFont.lexend(Font.BOLD, 20));
        userInfoPanel.add(uAddress);

        JPanel cropBeds = new JPanel();
        cropBeds.setLayout(new BoxLayout(cropBeds, BoxLayout.Y_AXIS));
        cropBeds.setMinimumSize(new Dimension(230, 100));
        cropBeds.setPreferredSize(new Dimension(230, 100));
        cropBeds.setOpaque(false);
        cropBeds.setFont(UIFont.lexend(Font.BOLD, 32));

        JScrollPane cropBedsList = new JScrollPane(cropBeds);
        cropBedsList.setOpaque(false);
        cropBedsList.getViewport().setOpaque(false);
        // Setting the preferred size of the scroll pane itself controls its visible
        // area
        cropBedsList.setMinimumSize(new Dimension(230, 100));
        cropBedsList.setPreferredSize(new Dimension(230, 100));
        cropBedsList.setBorder(BorderFactory.createEmptyBorder());

        JPanel cropsTitleRow = new JPanel(new GridLayout(1, 2));
        cropsTitleRow.setSize(230, 100);
        JLabel cropBedsLabel = new JLabel("Crop Beds");
        cropBedsLabel.setForeground(UIColors.SUB_TEXT_COLOR);
        cropsTitleRow.add(cropBedsLabel);
        cropsTitleRow.setOpaque(false);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addCropBed = UIButtons.createSettingsButton("Add Crop Bed");

        buttonWrapper.add(this.addCropBed);
        buttonWrapper.setOpaque(false);
        addCropBed.setMaximumSize(new Dimension(20, 20));
        cropsTitleRow.add(buttonWrapper);

        JPanel cropsPanel = new JPanel();
        cropsPanel.setLayout(new BoxLayout(cropsPanel, BoxLayout.Y_AXIS));
        cropsPanel.add(cropsTitleRow);
        cropsPanel.add(Box.createVerticalStrut(10));
        cropsPanel.add(cropBedsList);
        cropsPanel.setOpaque(false);

        // SETTINGS PANEL
        this.changeName = UIButtons.createSettingsButton("Change Name");
        this.changePass = UIButtons.createSettingsButton("Change Password");
        this.changeAddress = UIButtons.createSettingsButton("Change Address");

        JPanel settingsPanel = new JPanel();
        settingsPanel.setOpaque(false);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setSize(200, 500);
        JLabel settingsLabel = new JLabel("Settings");

        settingsLabel.setForeground(UIColors.SUB_TEXT_COLOR);
        settingsPanel.add(settingsLabel);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeName);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeAddress);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changePass);

        logOut = UIButtons.createSettingsButton("Log Out");

        gbc.gridy = 0;
        add(logoLabel, gbc);
        gbc.gridy = 1;
        add(userInfoPanel, gbc);
        gbc.gridy = 2;
        add(cropsPanel, gbc);
        gbc.gridy = 3;
        add(settingsPanel, gbc);
        gbc.gridy = 4;
        add(logOut, gbc);

    }

    public static void setUIFont(FontUIResource f){
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource originalFont) {
                Font newFont = new Font(f.getFontName(), originalFont.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(newFont));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to ensure proper painting
        Graphics2D g2d = (Graphics2D) g;
        // Define start and end colors
        Color startColor = new Color(76, 139, 63); // Steel Blue
        Color endColor = new Color(102, 177, 75); // Light Blue

        // Create a vertical gradient from top to bottom
        GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);

        // Set the paint and fill the rectangle
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

     public static void main(String[] args) {
     SwingUtilities.invokeLater(() -> {
     JFrame frame = new JFrame("AniCore Lite Sidebar");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // Create the new SideBar JPanel instance
     SideBar sideBarPanel = new SideBar(new User("John", "john123", "Farm Address", 19, "123"));
         Home home = new Home(new User("John", "john123", "Farm Address", 19, "123"));
     // Add the JPanel to the JFrame's content pane
     frame.getContentPane().add(sideBarPanel);
     frame.getContentPane().add(home);

     frame.setSize(1280, 720);
     frame.setMinimumSize(new Dimension(1280, 720));
     frame.setVisible(true);
     });
     }

}