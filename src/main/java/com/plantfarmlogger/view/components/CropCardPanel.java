package com.plantfarmlogger.view.components;

import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.util.UIRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.function.Consumer;

import static javax.swing.JOptionPane.showMessageDialog;

public class CropCardPanel extends JPanel {

    private String cropName, plantType, soilType, size;
    private LocalDate datePlanted, lastFertilized;

    private JTextField cropNameField, datePlantedField, sizeField, fertField;
    private JComboBox<String> subclassCropField, plantTypeField, soilCombo;
    private JLabel cropNameLabel, plantTypeLabel;

    private String subclassTypes[] = {"herb", "leaf", "root"};
    private String herbTypes[] = {"basil",
            "mint",
            "oregano",
            "thyme",
            "rosemary",
            "lavender",
            "sage",
            "chamomile",
            "lemon balm",
            "cilantro",
            "dill",
            "parsley",
            "tarragon"};
    private String leafTypes[] = {"lettuce",
            "spinach",
            "kale",
            "arugula",
            "bok choy",
            "swiss chard",
            "mustard greens",
            "collard greens",
            "mizuna",
            "endive",
            "romaine lettuce",
            "watercress",
            "tatsoi",
            "red leaf lettuce"};
    private String rootTypes[] = {"potato",
            "carrot",
            "beetroot",
            "radish",
            "turnip",
            "sweet potato",
            "cassava",
            "ginger",
            "onion",
            "garlic"};

    private final Consumer<String> onSave;   // Callback to unlock Home "Add" button
    private final Consumer<String> onNavigate; // Callback to switch screens
    private final Runnable onCancel;         // Callback to remove card if cancelled

    public CropCardPanel(Consumer<String> onSave, Consumer<String> onNavigate, Runnable onCancel) {
        this.onSave = onSave;
        this.onNavigate = onNavigate;
        this.onCancel = onCancel;

        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(15, 20, 15, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));


        initInputMode();
    }

    private void initInputMode() {
        removeAll(); // Clear previous components

        // --- Input Field ---
        cropNameField = new JTextField();
        cropNameField.setFont(UIFont.lexend(Font.BOLD, 18));
        cropNameField.setText("Enter Crop Name");
        cropNameField.setForeground(UIColors.TEXT_DARK);
        // Add a prompt/border style here if desired

        subclassCropField = new JComboBox<>(subclassTypes);
        subclassCropField.setSelectedIndex(0);
        subclassCropField.addActionListener(subclassCropField);

        plantTypeField = new JComboBox<>(herbTypes);

        subclassCropField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String selectedCategory = (String) subclassCropField.getSelectedItem();
                    if (selectedCategory != null) {
                        // Get the new string array based on the selection
                        String[] newItems;
                        if(selectedCategory.equals("herb")){
                            newItems = herbTypes;
                        } else if(selectedCategory.equals("leaf")){
                            newItems = leafTypes;
                        } else {
                            newItems = rootTypes;
                        }

                        // Create a new model and set it to the itemComboBox
                        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(newItems);
                        plantTypeField.setModel(model); // This updates the entire list of items
                    }

            }
        });
        // --- Buttons (Save / Cancel) ---
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setOpaque(false);

        JButton saveBtn = UIButtons.createPrimaryButton("Save");
        saveBtn.addActionListener(e -> {
            if (!cropNameField.getText().trim().isEmpty()) {
                switchToDisplayMode(cropNameField.getText());
                onSave.accept(cropNameField.getText()); // Tell Controller we are done editing
            } else {
                showMessageDialog(this, "All field must be filled", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                // alert that all fields must be filled
            }
        });

        JButton cancelBtn = UIButtons.createTextButton("Cancel");
        cancelBtn.addActionListener(e -> onCancel.run());

        buttons.add(cancelBtn);
        buttons.add(saveBtn);

        add(cropNameField, BorderLayout.CENTER);
        add(buttons, BorderLayout.EAST);
        add(subclassCropField, BorderLayout.SOUTH);
        add(plantTypeField, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    private void switchToDisplayMode(String name) {
        removeAll();
        //should update sidebar

        cropNameLabel = new JLabel(name);
        cropNameLabel.setFont(UIFont.lexend(Font.BOLD, 20));
        cropNameLabel.setForeground(UIColors.TEXT_DARK);

        JButton viewLogsBtn = UIButtons.createRoundedButton("View Logs");
        viewLogsBtn.setPreferredSize(new Dimension(120, 35));
        viewLogsBtn.addActionListener(e -> onNavigate.accept(name));

        add(cropNameLabel, BorderLayout.WEST);
        add(viewLogsBtn, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        UIRenderer.paintRoundedPanel(g, this, 20, UIColors.CARD_COLOR);
        super.paintComponent(g);
    }
}