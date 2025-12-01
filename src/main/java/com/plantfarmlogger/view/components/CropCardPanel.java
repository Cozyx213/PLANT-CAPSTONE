package com.plantfarmlogger.view.components;

import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CropCardPanel extends JPanel {

    // Data Fields
    private String cropName, plantType, soilType, size;
    private LocalDate datePlanted, lastFertilized;

    // UI Inputs
    private JTextField cropNameField, plantTypeField, datePlantedField, sizeField, fertField;
    private JComboBox<String> soilCombo;

    // Callbacks
    private Runnable onSaveCallback;
    private Runnable onCancelCallback;

    public CropCardPanel(Runnable onSaveCallback, Runnable onCancelCallback) {
        this.onSaveCallback = onSaveCallback;
        this.onCancelCallback = onCancelCallback;

        setLayout(new CardLayout());
        setBackground(UIColors.BG_COLOR);

        // Strict Size Enforcement: width expands, height fixed
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

        // 1. Header (Crop Name Input)
        JLabel titleLabel = new JLabel("Enter Crop Name");
        titleLabel.setFont(UIFont.lexend(Font.BOLD, 18));
        titleLabel.setForeground(UIColors.TEXT_DARK);

        // not sure what method this is supposed to be? please fix
        cropNameField = UIFields.createStyledField("e.g. Tomato Bed 1");

        // 2. Other Inputs
        plantTypeField = UIFactory.createStyledField("Enter Plant Type");
        datePlantedField = UIFactory.createDateField("Date Planted");

        String[] soils = {"Loam", "Clay", "Sandy", "Silt", "Peat"};
        soilCombo = new JComboBox<>(soils);
        soilCombo.setFont(UIFont.lexend(Font.PLAIN, 14));
        soilCombo.setBackground(Color.WHITE);

        sizeField = UIFactory.createStyledField("Enter Size");
        fertField = UIFactory.createDateField("Last Fertilized");

        // --- Layout Placement ---

        // Row 0: Header & Crop Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        p.add(titleLabel, gbc);

        gbc.gridy = 1;
        p.add(cropNameField, gbc);

        // Row 2: Plant Type & Date (Side by side if space permits, or stacked)
        // Stacking for simplicity and robustness
        gbc.gridy = 2; p.add(plantTypeField, gbc);
        gbc.gridy = 3; p.add(datePlantedField, gbc);

        // Row 3: Soil
        JPanel soilPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        soilPanel.setOpaque(false);
        JLabel soilLbl = new JLabel("Soil Type: ");
        soilLbl.setFont(UIFont.lexend(Font.PLAIN, 14));
        soilPanel.add(soilLbl);
        soilPanel.add(soilCombo);

        gbc.gridy = 4; p.add(soilPanel, gbc);

        // Row 4: Size & Fert
        gbc.gridy = 5; p.add(sizeField, gbc);
        gbc.gridy = 6; p.add(fertField, gbc);

        // Row 7: Buttons (Save & Cancel)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);

        JButton cancelBtn = UIButtons.createTextButton("Cancel");
        cancelBtn.addActionListener(e -> {
            if (onCancelCallback != null) onCancelCallback.run();
        });

        JButton saveBtn = UIFactory.createPrimaryButton("Save");
        saveBtn.setPreferredSize(new Dimension(100, 35)); // Smaller button
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

        // Use stored data to create labels
        JLabel nameLbl = new JLabel(this.cropName);
        nameLbl.setFont(UIFactory.getLexend(Font.BOLD, 24));
        nameLbl.setForeground(UIColors.TEXT_DARK);

        JLabel typeLbl = new JLabel(this.plantType);
        typeLbl.setFont(UIFactory.getLexend(Font.PLAIN, 16));
        typeLbl.setForeground(new Color(60, 100, 60)); // Darker green for subtitle

        JLabel dateLbl = new JLabel("Date Planted: " + this.datePlanted);
        JLabel soilLbl = new JLabel("Soil Type: " + this.soilType);
        JLabel sizeLbl = new JLabel("Size: " + (this.size.isEmpty() ? "N/A" : this.size));
        JLabel fertLbl = new JLabel("Last Fertilized: " + (this.lastFertilized == null ? "Never" : this.lastFertilized));

        Font detailFont = UIFactory.getLexend(Font.PLAIN, 14);
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

        JButton viewLogsBtn = UIFactory.createPrimaryButton("View Logs");
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

        // 1. Validation for Required Fields
        if (isPlaceholder(nameRaw) || isPlaceholder(typeRaw) || isPlaceholder(dateRaw)) {
            JOptionPane.showMessageDialog(this, "Please fill in Name, Plant Type, and Date Planted.");
            return;
        }

        // 2. Parse Dates
        try {
            // Flexible parser? For now strict YYYY-MM-DD as requested "aesthetic" inputs usually imply standard formats
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

        // 3. Store Data
        this.cropName = nameRaw;
        this.plantType = typeRaw;
        this.soilType = (String) soilCombo.getSelectedItem();
        this.size = isPlaceholder(sizeField.getText()) ? "" : sizeField.getText();

        // 4. Switch View
        add(createViewPanel(), "VIEW");

        resizePanel(260);

        ((CardLayout) getLayout()).show(this, "VIEW");

        // 5. Notify Parent
        if (onSaveCallback != null) onSaveCallback.run();
    }

    private boolean isPlaceholder(String text) {
        return text.contains("Enter") || text.contains("YYYY-MM-DD");
    }

    private void resizePanel(int height) {
        setPreferredSize(new Dimension(0, height));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        revalidate(); // Recalculate layout
        repaint();
    }
}