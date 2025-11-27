package com.plantfarmlogger.view;

import javax.swing.*;
import java.awt.*;

public class FarmMainUI extends JFrame {

    public FarmMainUI() {
        setTitle("Farm Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        SideBar sidebar = new SideBar();
        add(sidebar, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(250, 246, 236)); // cream bg

        // Title
        JLabel pageTitle = new JLabel("My Farm");
        pageTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        pageTitle.setForeground(new Color(20, 100, 40));
        pageTitle.setBorder(BorderFactory.createEmptyBorder(30, 20, 5, 20));

        JLabel subtitle = new JLabel("Number of Crop Beds: 3");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        rightPanel.add(pageTitle);
        rightPanel.add(subtitle);

        rightPanel.add(createCropCard());
        rightPanel.add(createCropCard());
        rightPanel.add(createCropCard());

        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(280, 800));
        panel.setBackground(new Color(27, 115, 56));
        return panel;
    }

    private JPanel createCropCard() {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(900, 180));
        card.setBackground(new Color(232, 247, 217));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        JLabel title = new JLabel("Kamote 1");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel plantType = new JLabel("Plant Type");
        JLabel datePlanted = new JLabel("Date Planted: May 1, 2025");
        JLabel soil = new JLabel("Soil Type: Loam");
        JLabel size = new JLabel("Size: W × H × L");
        JLabel lastFertilized = new JLabel("Last fertilized: October 26, 2025");

        content.add(title);
        content.add(Box.createVerticalStrut(10));
        content.add(plantType);
        content.add(datePlanted);
        content.add(Box.createVerticalStrut(10));
        content.add(soil);
        content.add(size);
        content.add(lastFertilized);

        JButton viewBtn = new JButton("View Logs");
        viewBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        viewBtn.setBackground(new Color(20, 100, 40));
        viewBtn.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(viewBtn);

        card.add(content, BorderLayout.WEST);
        card.add(buttonPanel, BorderLayout.EAST);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        wrapper.add(card);

        return wrapper;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(FarmMainUI::new);
    }
}

