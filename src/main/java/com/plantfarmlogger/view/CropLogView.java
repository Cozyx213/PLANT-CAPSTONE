package com.plantfarmlogger.view;

import com.plantfarmlogger.controller.CropLogController;
import com.plantfarmlogger.enums.Action;
import com.plantfarmlogger.enums.GrowthStatus;
import com.plantfarmlogger.enums.HealthStatus;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.CropLog;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.components.BaseDashboardView;
import com.plantfarmlogger.view.components.LogCardPanel;
//import com.plantfarmlogger.view.croplog.CropLogModel.CropLogEntry; // Import the DTO

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class CropLogView extends BaseDashboardView {

    private JPanel logsContainer;
    private JLabel countLabel;
    private JLabel titleLabel;
    private JButton addBtn;
    private static Crop selectedCrop;

    public CropLogView(User user, AppNavigator navigator) {
        super(user, navigator);
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

        JButton navigateHome = UIButtons.createTextButton("< Back to Home");
        navigateHome.addActionListener(e -> navigator.showHome(user));

        titleLabel = new JLabel("Loading...");
        titleLabel.setFont(UIFont.lexend(Font.BOLD, 36));
        titleLabel.setForeground(UIColors.BUTTON_COLOR);

        countLabel = new JLabel("Number of Crop Logs: 0");
        countLabel.setFont(UIFont.lexend(Font.PLAIN, 14));
        countLabel.setForeground(UIColors.TEXT_DARK);

        titleBlock.add(navigateHome);
        titleBlock.add(Box.createVerticalStrut(15));
        titleBlock.add(titleLabel);
        titleBlock.add(Box.createVerticalStrut(5));
        titleBlock.add(countLabel);

        JPanel addBtnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addBtnWrapper.setOpaque(false);
        addBtn = UIButtons.createRoundedButton("Add Log");
        addBtn.setPreferredSize(new Dimension(140, 40));
        addBtn.setMaximumSize(new Dimension(140, 40));
        addBtnWrapper.add(addBtn);

        header.add(titleBlock, BorderLayout.WEST);
        header.add(addBtnWrapper, BorderLayout.EAST);
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

        setAddLogListener(e -> handleAddLog());
        updateView(selectedCrop, cropLogController.getCropLogsByCropId(selectedCrop.getID()));

        return content;
    }

    // --- Interaction Methods ---

    private void handleAddLog() {
        // get user input
        showAddLogDialog();
        updateView(getSelectedCrop(), cropLogController.getCropLogsByCropId(selectedCrop.getID()));

    }

    public void setAddLogListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }

    public void updateView(Crop crop, List<CropLog> entries) {
        titleLabel.setText(crop.getPlantType());
        countLabel.setText("Number of Crop Logs: " + entries.size());
        logsContainer.removeAll();


        if (entries.isEmpty()) {
            // Optional: Add a "No logs yet" label here
            logsContainer.add(Box.createVerticalGlue());
        } else {
            for (CropLog entry : entries) {
                LocalDate logDate = LocalDate.parse(entry.getDate());
                int age = (int) java.time.temporal.ChronoUnit.DAYS.between(LocalDate.parse(selectedCrop.getDatePlanted()), logDate);
                System.out.println("Age: " + age);
                String[] actions = new String[entry.getActions().size()];
                int i = 0;
                for(Action action : entry.getActions()) {
                    actions[i++] = action.getValue();
                }
                LogCardPanel card = new LogCardPanel(
                        entry.getNotes(), entry.getDate(), String.valueOf(age),
                        entry.getHealthStatus().getValue(), entry.getGrowthStatus().getValue(),
                        actions);
                logsContainer.add(card);
                logsContainer.add(Box.createVerticalStrut(20));
            }
        }
        logsContainer.revalidate();
        logsContainer.repaint();
    }


    // Opens a dialog to get data from the user.
    // Returns null if cancelled, or a CropLogEntry if valid.

    public void showAddLogDialog() {
        JComboBox<String> healthDropdown = new JComboBox<>();
        for (HealthStatus hs : HealthStatus.values()) {
            healthDropdown.addItem(hs.name());
        }

        JComboBox<String> growthDropdown = new JComboBox<>();
        for (GrowthStatus gs : GrowthStatus.values()) {
            growthDropdown.addItem(gs.name());
        }

        // Multi-select list for Actions
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        JCheckBox[] actionCheckboxes = new JCheckBox[Action.values().length];
        int i = 0;
        for (Action action : Action.values()) {
            JCheckBox cb = new JCheckBox(action.getValue());
            actionsPanel.add(cb);
            actionCheckboxes[i++] = cb;
        }


        // Notes field
        JTextField notesField = new JTextField();
        Object[] message = {
                "Health Status (e.g. Good, Yellowing):", healthDropdown,
                "Growth Stage (e.g. Sprouting, Flowering):", growthDropdown,
                "Actions Taken (e.g. Watered, Pruned):", actionsPanel,
                "Notes:", notesField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "New Crop Log", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            List<String> selectedActions = new ArrayList<>();
            for (JCheckBox cb : actionCheckboxes) {
                if (cb.isSelected()) {
                    selectedActions.add(cb.getText());
                }
            }
            String[] actionsArray = selectedActions.toArray(new String[0]);
            boolean res = cropLogController.addCropLog(
                    notesField.getText(),
                    (String) healthDropdown.getSelectedItem(),
                    (String) growthDropdown.getSelectedItem(),
                    actionsArray,
                    user.getId(),
                    selectedCrop.getID()
            );
            if(res) System.out.println("Successfully added Crop Log");
            else System.out.println("Failed to add Crop Log");
        }
    }


    public void setCropTitle(String name) {
        if (titleLabel != null) {
            titleLabel.setText(name);
        }
    }


    public Crop getSelectedCrop() {
        return selectedCrop;
    }

    public static void setSelectedCrop(Crop selectedCrop) {
        CropLogView.selectedCrop = selectedCrop;
    }
}