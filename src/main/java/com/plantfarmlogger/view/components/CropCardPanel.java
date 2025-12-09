package com.plantfarmlogger.view.components;

import com.plantfarmlogger.controller.CropController;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Consumer;

import static javax.swing.JOptionPane.showMessageDialog;

public class CropCardPanel extends JPanel {

    private String cropName, plantType, soilType, size;
    private LocalDate datePlanted, lastFertilized;

    private JPanel cropSpecificPanel;
    private JPanel herbPanel;
    private JPanel leafPanel;
    private JPanel rootPanel;
    private JTextField soilTypeField;
    private JComboBox<String> subclassCropField, plantTypeField, soilCombo;
    private JSpinner widthSpinner,  heightSpinner, lengthSpinner;
    private JSpinner herbBaseGrowingDaysSpinner;
    private JTextField herbActiveCompoundsField;
    private JSpinner leafBaseGrowingDaysSpinner;
    private JSpinner rootDensitySpinner;


    private JLabel cropNameLabel, plantTypeLabel;
    private User currentUser;
    private String subclassTypes[] = {"HerbCrop", "LeafCrop", "RootCrop"};
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

    private final Runnable onSave;   // Callback to unlock Home "Add" button
    private final Consumer<Crop> onNavigate; // Callback to switch screens
    private final Runnable onCancel;         // Callback to remove card if cancelled

    public CropCardPanel(User user, Runnable onSave, Consumer<Crop> onNavigate, Runnable onCancel) {
        this.currentUser = user;
        this.onSave = onSave;
        this.onNavigate = onNavigate;
        this.onCancel = onCancel;

        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(15, 20, 15, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));


        initInputMode();
    }

    public CropCardPanel(Crop crop, Consumer<Crop> onNavigate){
        onSave = null;
        this.onNavigate = onNavigate;
        onCancel = null;
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(15, 20, 15, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        switchToDisplayMode(crop);
    }

    private void initInputMode() {
        removeAll(); // Clear previous components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // --- Input Field ---
//        cropNameField = new JTextField();
//        cropNameField.setFont(UIFont.lexend(Font.BOLD, 18));
//        cropNameField.setText("Enter Crop Name");
//        cropNameField.setForeground(UIColors.TEXT_DARK);
        // Add a prompt/border style here if desired

        subclassCropField = new JComboBox<>(subclassTypes);
        subclassCropField.setSelectedIndex(0);
        subclassCropField.addActionListener(subclassCropField);

        plantTypeField = new JComboBox<>(herbTypes);

        soilTypeField = new JTextField();
        soilTypeField.setFont(UIFont.lexend(Font.BOLD, 18));
        soilTypeField.setText("Enter Soil Type");
        soilTypeField.setForeground(UIColors.TEXT_DARK);


        widthSpinner = new JSpinner(
                new SpinnerNumberModel(0.5, 0.0, 10.0, 0.1)
        );
        heightSpinner = new JSpinner(
                new SpinnerNumberModel(0.3, 0.0, 10.0, 0.1)
        );
        lengthSpinner = new JSpinner(
                new SpinnerNumberModel(0.4, 0.0, 10.0, 0.1)
        );

        subclassCropField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) subclassCropField.getSelectedItem();
                CardLayout cl = (CardLayout) cropSpecificPanel.getLayout();
                cl.show(cropSpecificPanel, selectedCategory);

                // Update plantTypeField list
                String[] newItems;
                switch (selectedCategory) {
                    case "HerbCrop" -> newItems = herbTypes;
                    case "LeafCrop" -> newItems = leafTypes;
                    default -> newItems = rootTypes;
                }
                plantTypeField.setModel(new DefaultComboBoxModel<>(newItems));
            }
        });
        // --- Buttons (Save / Cancel) ---
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setOpaque(false);


        JButton saveBtn = UIButtons.createPrimaryButton("Save");

        saveBtn.addActionListener(e -> {
                String selectedCategory = (String) subclassCropField.getSelectedItem();
                String plantType = (String) plantTypeField.getSelectedItem();
                String soilType = soilTypeField.getText();
                double width = (double) widthSpinner.getValue();
                double height = (double) heightSpinner.getValue();
                double length = (double) lengthSpinner.getValue();

                Integer userBaseDays = null;
                String activeCompounds = null;
                Double userRootDensity = null;
                switch (selectedCategory) {
                    case "HerbCrop" -> {
                        userBaseDays = (Integer) herbBaseGrowingDaysSpinner.getValue();
                        activeCompounds = herbActiveCompoundsField.getText();
                    }
                    case "LeafCrop" -> {
                        userBaseDays = (Integer) leafBaseGrowingDaysSpinner.getValue();
                    }
                    case "RootCrop" -> {
                        userRootDensity = (Double) rootDensitySpinner.getValue();
                    }
                }
                CropController cropController = CropController.getInstance();
                Crop newCrop = cropController.addCrop(
                        selectedCategory,
                        plantType,
                        soilType,
                        width,
                        height,
                        length,
                        currentUser.getId(),
                        LocalDate.now().toString(),  // pruningDate, can be null for Root
                        userBaseDays,
                        activeCompounds,
                        userRootDensity
                );
                onSave.run(); // Tell Controller we are done editing
                switchToDisplayMode(newCrop);
//                showMessageDialog(this, "All field must be filled", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                // alert that all fields must be filled

        });

        JButton cancelBtn = UIButtons.createTextButton("Cancel");
        cancelBtn.addActionListener(e -> onCancel.run());

        buttons.add(cancelBtn);
        buttons.add(saveBtn);


        add(plantTypeField);
        add(subclassCropField);
        add(labeled("Width (m)", widthSpinner));
        add(labeled("Height (m)", heightSpinner));
        add(labeled("Length (m)", lengthSpinner));

        cropSpecificPanel = new JPanel(new CardLayout());
        herbPanel = createHerbPanel();
        leafPanel = createLeafPanel();
        rootPanel = createRootPanel();

        cropSpecificPanel.add(herbPanel, "HerbCrop");
        cropSpecificPanel.add(leafPanel, "LeafCrop");
        cropSpecificPanel.add(rootPanel, "RootCrop");

        add(buttons, BorderLayout.EAST);
        add(cropSpecificPanel);




        revalidate();
        repaint();
    }

    private void switchToDisplayMode(Crop crop) {
        if(crop==null){
            System.out.println("[CropCardPanel] Failed to create new crop");
            return;
        }
        removeAll();
        //should update sidebar

        cropNameLabel = new JLabel(crop.getPlantType());
        cropNameLabel.setFont(UIFont.lexend(Font.BOLD, 20));
        cropNameLabel.setForeground(UIColors.TEXT_DARK);

        JButton viewLogsBtn = UIButtons.createRoundedButton("View Logs");
        viewLogsBtn.setPreferredSize(new Dimension(120, 35));
        viewLogsBtn.addActionListener(e -> onNavigate.accept(crop));

        add(cropNameLabel, BorderLayout.WEST);
        add(viewLogsBtn, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    private JPanel labeled(String label, JComponent field) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel l = new JLabel(label);
        l.setFont(UIFont.lexend(Font.BOLD, 16));
        p.add(l, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        p.setOpaque(false);
        return p;
    }

    private JPanel createHerbPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1, 5, 5));

        herbBaseGrowingDaysSpinner = new JSpinner(new SpinnerNumberModel(30, 1, 365, 1));
        herbActiveCompoundsField = new JTextField();

        p.add(labeled("Base Growing Days", herbBaseGrowingDaysSpinner));
        p.add(labeled("Active Compounds", herbActiveCompoundsField));

        return p;
    }


    private JPanel createLeafPanel() {
        JPanel p = new JPanel(new GridLayout(0, 1, 5, 5));
        leafBaseGrowingDaysSpinner = new JSpinner(new SpinnerNumberModel(30, 1, 365, 1));
        p.add(labeled("Base Growing Days", leafBaseGrowingDaysSpinner));
        return p;
    }


    private JPanel createRootPanel() {
        JPanel p = new JPanel(new GridLayout(0, 1, 5, 5));
        rootDensitySpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
        p.add(labeled("Root Density", rootDensitySpinner));
        return p;
    }



    @Override
    protected void paintComponent(Graphics g) {
        UIRenderer.paintRoundedPanel(g, this, 20, UIColors.CARD_COLOR);
        super.paintComponent(g);
    }
}