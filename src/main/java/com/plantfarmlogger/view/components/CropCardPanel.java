package com.plantfarmlogger.view.components;

import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CropCardPanel extends JPanel {

    private String cropName, plantType, soilType, size;
    private LocalDate datePlanted, lastFertilized;

    private JTextField cropNameField, plantTypeField, datePlantedField, sizeField, fertField;
    private JComboBox<String> soilCombo;

    private Runnable onSaveCallback;
    private Runnable onCancelCallback;

    public CropCardPanel(Runnable onSaveCallback, Runnable onCancelCallback) {
        this.onSaveCallback = onSaveCallback;
        this.onCancelCallback = onCancelCallback;

        setLayout(new CardLayout());
        setBackground(UIColors.BG_COLOR);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        setPreferredSize(new Dimension(0, 350));

        resizePanel(350);

        add(createInputPanel(), "INPUT");
    }

    private JPanel createInputPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(UIColors.CARD_COLOR);
        p.setBorder(new EmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Enter Crop Name");
        titleLabel.setFont(UIFont.lexend(Font.BOLD, 18));
        titleLabel.setForeground(UIColors.TEXT_DARK);

        cropNameField = UIFields.createStyledField("e.g. Tomato Bed 1");

        plantTypeField = UIFields.createStyledField("Enter Plant Type");
        datePlantedField = UIFields.createDateField("Date Planted");

        String[] soils = {"Loam", "Clay", "Sandy", "Silt", "Peat"};
        soilCombo = new JComboBox<>(soils);
        soilCombo.setFont(UIFont.lexend(Font.PLAIN, 14));
        soilCombo.setBackground(Color.WHITE);

        sizeField = UIFields.createStyledField("Enter Size");
        fertField = UIFields.createDateField("Last Fertilized");

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        p.add(titleLabel, gbc);

        gbc.gridy = 1;
        p.add(cropNameField, gbc);

        gbc.gridy = 2; p.add(plantTypeField, gbc);
        gbc.gridy = 3; p.add(datePlantedField, gbc);

        JPanel soilPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        soilPanel.setOpaque(false);
        JLabel soilLbl = new JLabel("Soil Type: ");
        soilLbl.setFont(UIFont.lexend(Font.PLAIN, 14));
        soilPanel.add(soilLbl);
        soilPanel.add(soilCombo);

        gbc.gridy = 4; p.add(soilPanel, gbc);

        gbc.gridy = 5; p.add(sizeField, gbc);
        gbc.gridy = 6; p.add(fertField, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);

        JButton cancelBtn = UIButtons.createTextButton("Cancel");
        cancelBtn.addActionListener(e -> {
            if (onCancelCallback != null) onCancelCallback.run();
        });

        JButton saveBtn = UIButtons.createPrimaryButton("Save");
        saveBtn.setPreferredSize(new Dimension(100, 35));

        saveBtn.addActionListener(e -> attemptSave());

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        gbc.gridy = 7; gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        p.add(btnPanel, gbc);

        return p;
    }

    private JPanel createViewPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(UIColors.CARD_COLOR);
        p.setBorder(new EmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(2, 0, 2, 0);

        JLabel nameLbl = new JLabel(this.cropName);
        nameLbl.setFont(UIFont.lexend(Font.BOLD, 24));
        nameLbl.setForeground(UIColors.TEXT_DARK);

        JLabel typeLbl = new JLabel(this.plantType);
        typeLbl.setFont(UIFont.lexend(Font.PLAIN, 16));
        typeLbl.setForeground(new Color(60, 100, 60));

        JLabel dateLbl = new JLabel("Date Planted: " + this.datePlanted);
        JLabel soilLbl = new JLabel("Soil Type: " + this.soilType);
        JLabel sizeLbl = new JLabel("Size: " + (this.size.isEmpty() ? "N/A" : this.size));
        JLabel fertLbl = new JLabel("Last Fertilized: " + (this.lastFertilized == null ? "Never" : this.lastFertilized));

        Font detailFont = UIFont.lexend(Font.PLAIN, 14);
        for(JLabel l : new JLabel[]{dateLbl, soilLbl, sizeLbl, fertLbl}) {
            l.setFont(detailFont);
            l.setForeground(UIColors.TEXT_DARK);
        }

        gbc.gridx = 0; gbc.gridy = 0; p.add(nameLbl, gbc);
        gbc.gridy = 1; p.add(typeLbl, gbc);
        gbc.gridy = 2; p.add(Box.createVerticalStrut(10), gbc);
        gbc.gridy = 3; p.add(dateLbl, gbc);
        gbc.gridy = 4; p.add(soilLbl, gbc);
        gbc.gridy = 5; p.add(sizeLbl, gbc);
        gbc.gridy = 6; p.add(fertLbl, gbc);

        JButton viewLogsBtn = UIButtons.createPrimaryButton("View Logs");
        gbc.gridx = 1; gbc.gridy = 5; gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        p.add(viewLogsBtn, gbc);

        return p;
    }

    private void attemptSave() {
        String nameRaw = cropNameField.getText();
        String typeRaw = plantTypeField.getText();
        String dateRaw = datePlantedField.getText();
        String fertRaw = fertField.getText();

        if (isPlaceholder(nameRaw) || isPlaceholder(typeRaw) || isPlaceholder(dateRaw)) {
            JOptionPane.showMessageDialog(this, "Please fill in Name, Plant Type, and Date Planted.");
            return;
        }

        try {

            this.datePlanted = LocalDate.parse(dateRaw);

            if (!isPlaceholder(fertRaw) && !fertRaw.isEmpty()) {
                this.lastFertilized = LocalDate.parse(fertRaw);
            } else {
                this.lastFertilized = null;
            }

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid Date Format. Please use YYYY-MM-DD (e.g., 2025-01-30).");
            return;
        }

        this.cropName = nameRaw;
        this.plantType = typeRaw;
        this.soilType = (String) soilCombo.getSelectedItem();
        this.size = isPlaceholder(sizeField.getText()) ? "" : sizeField.getText();

        add(createViewPanel(), "VIEW");

        resizePanel(260);

        ((CardLayout) getLayout()).show(this, "VIEW");

        if (onSaveCallback != null) onSaveCallback.run();
    }

    private boolean isPlaceholder(String text) {
        return text.contains("Enter") || text.contains("YYYY-MM-DD");
    }

    private void resizePanel(int height) {
        setPreferredSize(new Dimension(0, height));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        revalidate();

        repaint();
    }
}