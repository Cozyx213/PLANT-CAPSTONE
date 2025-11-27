package com.plantfarmlogger.view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class home_dashboard extends JFrame {

    public home_dashboard() {
        setTitle("My Farm");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main scrollable container
        JPanel homeDashBoard = new JPanel();
        homeDashBoard.setLayout(new BoxLayout(homeDashBoard, BoxLayout.X_AXIS));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(250, 246, 236)); // light cream background

        // Title section
        JLabel title = new JLabel("My Farm");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(new Color(20, 100, 40));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        JLabel countLabel = new JLabel("Number of Crop Beds: 3");
        countLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        countLabel.setForeground(new Color(40, 100, 40));
        countLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        mainPanel.add(title);
        mainPanel.add(countLabel);

        // Add multiple crop bed cards
        mainPanel.add(createCropCard());
        mainPanel.add(createCropCard());
        mainPanel.add(createCropCard());

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);

//        add(scrollPane);

        homeDashBoard.add(new SideBar());
        homeDashBoard.add(scrollPane);

        add(homeDashBoard);
    }

    private JPanel createCropCard() {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(850, 210));
        card.setBackground(new Color(232, 247, 217)); // light green
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        // name, username, age, farm, address, password, confirm password

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        JLabel title = new JLabel("Kamote 1");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(20, 100, 40));

        JLabel plantType = new JLabel("Plant Type");
        JLabel datePlanted = new JLabel("Date Planted: May 1, 2025");

        JLabel soilType = new JLabel("Soil Type: Loam");
        JLabel size = new JLabel("Size: W x H x L");
        JLabel fertilizerDate = new JLabel("Last fertilized: October 26, 2025");

        Font infoFont = new Font("SansSerif", Font.PLAIN, 18);
        plantType.setFont(infoFont);
        datePlanted.setFont(infoFont);
        soilType.setFont(infoFont);
        size.setFont(infoFont);
        fertilizerDate.setFont(infoFont);

        content.add(title);
        content.add(Box.createVerticalStrut(10));
        content.add(plantType);
        content.add(datePlanted);
        content.add(Box.createVerticalStrut(10));
        content.add(soilType);
        content.add(size);
        content.add(fertilizerDate);

        JButton viewBtn = new JButton("View Logs");
        viewBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        viewBtn.setBackground(new Color(20, 100, 40));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setFocusPainted(false);
        viewBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(viewBtn);

        card.add(content, BorderLayout.WEST);
        card.add(buttonPanel, BorderLayout.EAST);


        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(15, 20, 15, 20));
        wrapper.add(card);

        return wrapper;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new home_dashboard().setVisible(true);
        });
    }
}
