package com.plantfarmlogger.view.components;

import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogCardPanel extends JPanel {

    public LogCardPanel(String date, String age, String health, String growth, String actions) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Or UIColors.CARD_BG
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                new EmptyBorder(15, 20, 15, 20)
        ));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        // Top Row: Date and Age
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);

        JLabel dateLbl = new JLabel(date);
        dateLbl.setFont(UIFont.lexend(Font.BOLD, 16));
        dateLbl.setForeground(UIColors.TEXT_DARK);

        JLabel ageLbl = new JLabel(age);
        ageLbl.setFont(UIFont.lexend(Font.PLAIN, 12));
        ageLbl.setForeground(Color.GRAY);

        topRow.add(dateLbl, BorderLayout.WEST);
        topRow.add(ageLbl, BorderLayout.EAST);

        // Content Row: Details
        JPanel detailsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        detailsPanel.setOpaque(false);
        detailsPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        detailsPanel.add(createDetailLabel("Health", health));
        detailsPanel.add(createDetailLabel("Growth", growth));
        detailsPanel.add(createDetailLabel("Actions", actions));

        add(topRow, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
    }

    private JPanel createDetailLabel(String title, String value) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);

        JLabel t = new JLabel(title);
        t.setFont(UIFont.lexend(Font.BOLD, 12));
        t.setForeground(UIColors.BUTTON_COLOR);

        JLabel v = new JLabel(value);
        v.setFont(UIFont.lexend(Font.PLAIN, 14));
        v.setForeground(UIColors.TEXT_DARK);

        p.add(t, BorderLayout.NORTH);
        p.add(v, BorderLayout.CENTER);
        return p;
    }
}