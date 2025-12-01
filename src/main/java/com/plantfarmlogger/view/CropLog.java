package com.plantfarmlogger.view;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFactory;
import com.plantfarmlogger.view.components.BaseDashboardView;
import com.plantfarmlogger.view.components.LogCardPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CropLog extends BaseDashboardView {

    private JPanel logsContainer;
    private JLabel countLabel;
    private ArrayList<LogCardPanel> logList; // The ArrayList Concept reused
    private String cropName = "Tomato"; // Dynamically passed in real app

    public CropLog(User user) {
        super(user);
        this.logList = new ArrayList<>();

        // Add Dummy Data for demonstration
        addLog("November 29, 2025", "1 month, 3 days", "Healthy", "30 cm", "Watered, Fertilized");
        addLog("November 28, 2025", "1 month, 2 days", "Healthy", "30 cm", "Watered, Fertilized");
        addLog("November 27, 2025", "1 month, 1 day", "Yellowing", "29 cm", "Watered");
    }

    @Override
    protected JPanel createContentPanel() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIColors.BG_COLOR);

        // --- 1. Header Section ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIColors.BG_COLOR);
        header.setBorder(new EmptyBorder(40, 40, 20, 40));

        // Title Block
        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setOpaque(false);

        JLabel title = new JLabel(cropName);
        title.setFont(UIFactory.getLexend(Font.BOLD, 36));
        title.setForeground(UIColors.BUTTON_COLOR);

        countLabel = new JLabel("Number of Crop Logs: 0");
        countLabel.setFont(UIFactory.getLexend(Font.PLAIN, 14));
        countLabel.setForeground(UIColors.TEXT_DARK);

        titleBlock.add(title);
        titleBlock.add(Box.createVerticalStrut(5));
        titleBlock.add(countLabel);

        // Add Log Button
        UIFactory.RoundedButton addBtn = new UIFactory.RoundedButton("Add Log");
        addBtn.setPreferredSize(new Dimension(140, 40));
        addBtn.addActionListener(e -> {
            // In a real app, this would open a dialog or input form
            addLog("New Log Entry", "Just now", "N/A", "N/A", "None");
        });

        header.add(titleBlock, BorderLayout.WEST);
        header.add(addBtn, BorderLayout.EAST);

        content.add(header, BorderLayout.NORTH);

        // --- 2. Scrollable List Section ---
        logsContainer = new JPanel();
        logsContainer.setLayout(new BoxLayout(logsContainer, BoxLayout.Y_AXIS));
        logsContainer.setBackground(UIColors.BG_COLOR);
        logsContainer.setBorder(new EmptyBorder(0, 40, 40, 40));

        logsContainer.add(Box.createVerticalGlue()); // Push items to top

        JScrollPane scroll = new JScrollPane(logsContainer);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        content.add(scroll, BorderLayout.CENTER);

        return content;
    }

    // Reuse ArrayList concept: Add to list, then update UI
    private void addLog(String date, String age, String health, String growth, String actions) {
        LogCardPanel newLog = new LogCardPanel(date, age, health, growth, actions);

        logList.add(newLog);

        // Add to UI at index 0 (Top)
        logsContainer.add(newLog, 0);
        logsContainer.add(Box.createVerticalStrut(20), 1); // Spacer

        updateCount();
        refreshUI();
    }

    private void updateCount() {
        countLabel.setText("Number of Crop Logs: " + logList.size());
    }

    private void refreshUI() {
        logsContainer.revalidate();
        logsContainer.repaint();
    }
}