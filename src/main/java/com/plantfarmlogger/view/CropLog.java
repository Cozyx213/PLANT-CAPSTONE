package com.plantfarmlogger.view;

import com.plantfarmlogger.model.User;

import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.components.BaseDashboardView;
import com.plantfarmlogger.view.components.LogCardPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CropLog extends BaseDashboardView {

    public record UILogEntry(String date, String age, String health, String growth, String actions) {}

    private JPanel logsContainer;
    private JLabel countLabel;
    private JLabel titleLabel;
    private JButton addBtn;

    public CropLog(User user) {
        super(user);
    }

    @Override
    protected JPanel createContentPanel() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIColors.BG_COLOR);

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

    public void setAddLogListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }

    public void setCropTitle(String name) {
        titleLabel.setText(name);
    }

    public void setLogData(List<UILogEntry> entries) {
        logsContainer.removeAll();
        countLabel.setText("Number of Crop Logs: " + entries.size());

        if (entries.isEmpty()) {
            logsContainer.add(Box.createVerticalGlue());
        } else {
            for (UILogEntry entry : entries) {
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
}