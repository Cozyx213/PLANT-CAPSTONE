package com.plantfarmlogger.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import com.plantfarmlogger.controller.CropController;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.AppNavigator;

public class SideBar extends JPanel {
    private final JButton addCropBed;
    private final JButton logOut;
    private final Consumer<Crop> onNavigate;

    public SideBar(User user, AppNavigator appNavigator, Consumer<Crop> onNavigate) {
        this.onNavigate = onNavigate;

        // Load logo from classpath root (resources/png)
        java.net.URL imageUrl = getClass().getResource("/png/logo_lite.png");
        if (imageUrl == null) {
            imageUrl = getClass().getResource("/png/logo.png"); // fallback if lite variant missing
        }
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File(".").getAbsolutePath());

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
            Image scaledLogoImage = image.getScaledInstance(200, 30, Image.SCALE_SMOOTH);
            ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
            logoLabel = new JLabel(scaledLogoIcon);
        } else {
            System.err.println("Error: Image resource not found at /png/logo.png");
            logoLabel.setForeground(UIColors.BG_COLOR);
            logoLabel.setFont(UIFont.lexend(Font.BOLD, 26));
        }

        JLabel uName = new JLabel(user.getName());
        uName.setForeground(UIColors.TEXT_COLOR);
        uName.setBorder(new EmptyBorder(new Insets(20, 0, 5, 0)));
        uName.setFont(UIFont.lexend(Font.BOLD, 20));
        userInfoPanel.add(uName);

        JLabel uUsername = new JLabel("@" + user.getUsername());
        uUsername.setForeground(UIColors.SUB_TEXT_COLOR);
        uUsername.setBorder(new EmptyBorder(new Insets(0, 0, 30, 0)));
        uUsername.setFont(UIFont.lexend(Font.PLAIN, 14));
        userInfoPanel.add(uUsername);

        JLabel uAddress = new JLabel("<html>" + user.getAddress() + "</html>");
        uAddress.setForeground(UIColors.SUB_TEXT_COLOR);
        uAddress.setFont(UIFont.lexend(Font.PLAIN, 16));
        userInfoPanel.add(uAddress);

        JPanel cropBeds = new JPanel();
        cropBeds.setLayout(new BoxLayout(cropBeds, BoxLayout.Y_AXIS));
        cropBeds.setMinimumSize(new Dimension(230, 90));
        cropBeds.setPreferredSize(new Dimension(230, 90));
        cropBeds.setOpaque(false);
        cropBeds.setFont(UIFont.lexend(Font.BOLD, 32));

        CropController cropController = CropController.getInstance();
        ArrayList<Crop> cropsOfUser = cropController.getAllByUserId(user.getId());
        for (Crop crop : cropsOfUser) {
            JButton cropBedButton = UIButtons.createSettingsButton(crop.getPlantType());
            cropBedButton.addActionListener(e -> {
                if (this.onNavigate != null) {
                    this.onNavigate.accept(crop);
                }
            });
            cropBeds.add(cropBedButton);
        }

        JScrollPane cropBedsList = new JScrollPane(cropBeds);
        cropBedsList.setOpaque(false);
        cropBedsList.getViewport().setOpaque(false);

        cropBedsList.setMinimumSize(new Dimension(230, 90));
        cropBedsList.setPreferredSize(new Dimension(230, 90));
        cropBedsList.setBorder(BorderFactory.createEmptyBorder());

        JPanel cropsTitleRow = new JPanel(new GridLayout(1, 2));
        cropsTitleRow.setSize(230, 100);
        JLabel cropBedsLabel = new JLabel("Crop Beds");
        cropBedsLabel.setForeground(UIColors.TEXT_COLOR);
        cropBedsLabel.setFont(UIFont.lexend(Font.BOLD, 16));
        cropsTitleRow.add(cropBedsLabel);
        cropsTitleRow.setOpaque(false);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addCropBed = UIButtons.createSettingsButton("Add Crop Bed");

        // buttonWrapper.add(this.addCropBed);
        buttonWrapper.setOpaque(false);
        addCropBed.setMaximumSize(new Dimension(20, 20));
        cropsTitleRow.add(buttonWrapper);

        JPanel cropsPanel = new JPanel();
        cropsPanel.setLayout(new BoxLayout(cropsPanel, BoxLayout.Y_AXIS));
        cropsPanel.add(cropsTitleRow);
        cropsPanel.add(Box.createVerticalStrut(10));
        cropsPanel.add(cropBedsList);
        cropsPanel.setOpaque(false);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setOpaque(false);
        settingsPanel.setSize(200, 500);

        settingsPanel.add(Box.createVerticalStrut(180));

        logOut = UIButtons.createSettingsButton("Log Out");
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appNavigator.showLogin();
            }
        });

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

    public static void setUIFont(FontUIResource f) {
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

}