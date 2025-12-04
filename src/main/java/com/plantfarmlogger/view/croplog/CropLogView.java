package com.plantfarmlogger.view.croplog;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.components.BaseDashboardView;
import com.plantfarmlogger.view.components.LogCardPanel;
import com.plantfarmlogger.view.croplog.CropLogModel.CropLogEntry; // Import the DTO

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CropLogView extends BaseDashboardView {

    private JPanel logsContainer;
    private JLabel countLabel;
    private JLabel titleLabel;
    private JButton addBtn;

    public CropLogView(User user) {
        super(user);
    }

    @Override
    protected JPanel createContentPanel() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIColors.BG_COLOR);

        // header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIColors.BG_COLOR);
        header.setBorder(new EmptyBorder(40, 40, 20, 40));

        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setOpaque(false);

        titleLabel = new JLabel("Loading...");
        titleLabel.setFont(UIFont.lexend(Font.BOLD, 36));
        titleLabel.setForeground(UIColors.BUTTON_COLOR);

        countLabel = new JLabel("Number of Crop Logs: 0");
        countLabel.setFont(UIFont.lexend(Font.PLAIN, 14));
        countLabel.setForeground(UIColors.TEXT_DARK);

        titleBlock.add(titleLabel);
        titleBlock.add(Box.createVerticalStrut(5));
        titleBlock.add(countLabel);

        addBtn = UIButtons.createRoundedButton("Add Log");
        addBtn.setPreferredSize(new Dimension(140, 40));

        header.add(titleBlock, BorderLayout.WEST);
        header.add(addBtn, BorderLayout.EAST);
        content.add(header, BorderLayout.NORTH);

        // container for the list of crop log panels
        logsContainer = new JPanel();
        logsContainer.setLayout(new BoxLayout(logsContainer, BoxLayout.Y_AXIS));
        logsContainer.setBackground(UIColors.BG_COLOR);
        logsContainer.setBorder(new EmptyBorder(0, 40, 40, 40));

        JScrollPane scroll = new JScrollPane(logsContainer);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        content.add(scroll, BorderLayout.CENTER);

        return content;
    }

    // --- Interaction Methods ---

    public void setAddLogListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }

    public void updateView(String cropName, List<CropLogEntry> entries) {
        titleLabel.setText(cropName);
        countLabel.setText("Number of Crop Logs: " + entries.size());

        logsContainer.removeAll();

        if (entries.isEmpty()) {
            // Optional: Add a "No logs yet" label here
            logsContainer.add(Box.createVerticalGlue());
        } else {
            for (CropLogEntry entry : entries) {
                LogCardPanel card = new LogCardPanel(
                        entry.date(), entry.age(), entry.health(), entry.growth(), entry.actions()
                );
                logsContainer.add(card);
                logsContainer.add(Box.createVerticalStrut(20));
            }
        }
        logsContainer.revalidate();
        logsContainer.repaint();
    }


     // Opens a dialog to get data from the user.
     // Returns null if cancelled, or a CropLogEntry if valid.

    public CropLogEntry showAddLogDialog() {
        JTextField healthField = new JTextField();
        JTextField growthField = new JTextField();
        JTextField actionsField = new JTextField();

        Object[] message = {
                "Health Status (e.g. Good, Yellowing):", healthField,
                "Growth Stage (e.g. Sprouting, Flowering):", growthField,
                "Actions Taken (e.g. Watered, Pruned):", actionsField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "New Crop Log", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            // In a real app, calculate Date and Age dynamically
            return new CropLogEntry(
                    java.time.LocalDate.now().toString(),
                    "Day 45", // Simulated age
                    healthField.getText(),
                    growthField.getText(),
                    actionsField.getText()
            );
        }
        return null;
    }

    public void setCropTitle(String name) {
        if (titleLabel != null) {
            titleLabel.setText(name);
        }
    }
}