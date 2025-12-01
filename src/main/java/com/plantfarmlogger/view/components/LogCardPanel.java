package com.plantfarmlogger.view.components;

import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogCardPanel extends JPanel {

    // Data
    private String date, age, health, growth, actions;

    public LogCardPanel(String date, String age, String health, String growth, String actions) {
        this.date = date;
        this.age = age;
        this.health = health;
        this.growth = growth;
        this.actions = actions;

        setLayout(new GridBagLayout());
        setBackground(UIColors.CARD_COLOR); // Light Green

        // Fixed height for consistency, width expands
        setPreferredSize(new Dimension(0, 180));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        setBorder(new EmptyBorder(20, 25, 20, 25));

        initUI();
    }

    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Date (Title)
        JLabel dateLbl = new JLabel(date);
        dateLbl.setFont(UIFont.lexend(Font.BOLD, 22));
        dateLbl.setForeground(UIColors.BUTTON_COLOR); // Dark Green

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        add(dateLbl, gbc);

        // 2. Age (Subtitle)
        JLabel ageLbl = new JLabel(age);
        ageLbl.setFont(UIFont.lexend(Font.PLAIN, 14));
        ageLbl.setForeground(new Color(100, 120, 100)); // Muted Green

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0); // Spacing below title
        add(ageLbl, gbc);

        // 3. Status Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);

        detailsPanel.add(createDetailLabel("Health Status: ", health));
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(createDetailLabel("Growth Status: ", growth));
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(createDetailLabel("Actions: ", actions));

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(detailsPanel, gbc);

        // 4. Edit Button (Right Aligned)
        JButton editBtn = UIButtons.createRoundedButton("Edit");
        editBtn.setPreferredSize(new Dimension(120, 40)); // Smaller button
        editBtn.setFont(UIFont.lexend(Font.BOLD, 14));

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        add(editBtn, gbc);
    }

    private JPanel createDetailLabel(String label, String value) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        p.setOpaque(false);

        JLabel l = new JLabel(label);
        l.setFont(UIFont.lexend(Font.PLAIN, 14));
        l.setForeground(new Color(60, 80, 60));

        JLabel v = new JLabel(value);
        v.setFont(UIFont.lexend(Font.PLAIN, 14));
        v.setForeground(UIColors.TEXT_DARK);

        p.add(l);
        p.add(v);
        return p;
    }
}