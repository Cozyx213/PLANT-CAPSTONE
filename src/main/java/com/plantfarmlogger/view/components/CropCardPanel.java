package com.plantfarmlogger.view.components;

import com.plantfarmlogger.controller.CropController;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.model.subclasses.HerbCrop;
import com.plantfarmlogger.model.subclasses.LeafCrop;
import com.plantfarmlogger.model.subclasses.RootCrop;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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

    private JLabel cropNameLabel, datePlantedLabel, soilTypeLabel, sizeLabel, lastFertilizedLabel;
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
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        initInputMode();
    }

    public CropCardPanel(Crop crop, Consumer<Crop> onNavigate){
        onSave = null;
        this.onNavigate = onNavigate;
        onCancel = null;
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(0, 15, 0, 0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        switchToDisplayMode(crop);
    }

    private void initInputMode() {
        removeAll(); // Clear previous components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add a prompt/border style here if desired


        subclassCropField = new JComboBox<>(subclassTypes);
        subclassCropField.setSelectedIndex(0);
        subclassCropField.addActionListener(subclassCropField);
        subclassCropField.setRenderer(new CustomComboBoxRenderer());
        subclassCropField.setToolTipText("Choose a crop type!");

        plantTypeField = new JComboBox<>(herbTypes);
        plantTypeField.setRenderer(new CustomComboBoxRenderer());
        plantTypeField.setToolTipText("Choose a plant type!!");

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
            CropCardPanel displayCard = new CropCardPanel(newCrop, onNavigate);

            // Replace the input card in the container
            Container parent = CropCardPanel.this.getParent();
            int index = Arrays.asList(parent.getComponents()).indexOf(CropCardPanel.this);

            parent.remove(CropCardPanel.this);
            parent.add(displayCard, index);
            parent.revalidate();
            parent.repaint();
            onSave.run();
        });

        JButton cancelBtn = UIButtons.createTextButton("Cancel");
        cancelBtn.addActionListener(e -> onCancel.run());

        buttons.add(cancelBtn);
        buttons.add(saveBtn);

        add(Box.createVerticalStrut(15));
        add(subclassCropField);
        add(Box.createVerticalStrut(10));
        add(plantTypeField);
        add(Box.createVerticalStrut(10));
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
        add(Box.createVerticalStrut(10));
        add(labeled("Soil Type", soilTypeField));
        add(Box.createVerticalStrut(15));
        add(cropSpecificPanel);
        add(Box.createVerticalStrut(15));
        add(buttons, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    private void switchToDisplayMode(Crop crop) {
        if(crop==null){
            System.out.println("[CropCardPanel] Failed to create new crop");
            return;
        }
        removeAll();

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        cropNameLabel = new JLabel(crop.getPlantType());
        cropNameLabel.setFont(UIFont.lexend(Font.BOLD, 20));
        cropNameLabel.setForeground(UIColors.TEXT_DARK);

        datePlantedLabel = new JLabel("DATE PLANTED: " + crop.getDatePlanted());
        datePlantedLabel.setFont(UIFont.lexend(Font.PLAIN, 16));
        datePlantedLabel.setForeground(UIColors.BUTTON_COLOR);

        soilTypeLabel = new JLabel("SOIL TYPE: " + crop.getSoilType());
        soilTypeLabel.setFont(UIFont.lexend(Font.PLAIN, 16));
        soilTypeLabel.setForeground(UIColors.BUTTON_COLOR);

        sizeLabel = new JLabel("SIZE (LxHxW): " + crop.getWidth() + "x" + crop.getLength() + "x" + crop.getHeight());
        sizeLabel.setFont(UIFont.lexend(Font.PLAIN, 16));
        sizeLabel.setForeground(UIColors.BUTTON_COLOR);

        lastFertilizedLabel = new JLabel("LAST FERTILIZED: " + crop.getLastFertilized());
        lastFertilizedLabel.setFont(UIFont.lexend(Font.PLAIN, 16));
        lastFertilizedLabel.setForeground(UIColors.BUTTON_COLOR);

        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(cropNameLabel);
        infoPanel.add(datePlantedLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(soilTypeLabel);
        infoPanel.add(sizeLabel);
        infoPanel.add(lastFertilizedLabel);

        JButton viewLogsBtn = UIButtons.createRoundedButton("View Logs");
        viewLogsBtn.setPreferredSize(new Dimension(200, 35));
        viewLogsBtn.setMaximumSize(new Dimension(200, 35));
        viewLogsBtn.addActionListener(e -> onNavigate.accept(crop));

        RoundedRightImagePanel imageBGPanel = new RoundedRightImagePanel("/farm_1.png", 20);
        imageBGPanel.setLayout(new BoxLayout(imageBGPanel, BoxLayout.Y_AXIS));
        imageBGPanel.setBorder(new EmptyBorder(20, 20, 15, 20));

        // switch case depending if root, leaf, herb
        JButton setCalculatedPruningDateBtn = UIButtons.createRoundedButton("Auto-Pruning Date");
        setCalculatedPruningDateBtn.setPreferredSize(new Dimension(200, 35));
        setCalculatedPruningDateBtn.setMaximumSize(new Dimension(200, 35));
        JLabel pruningDateLabel = new JLabel();
        pruningDateLabel.setFont(UIFont.lexend(Font.PLAIN, 16));
        pruningDateLabel.setForeground(UIColors.BUTTON_COLOR);

        String cropType = crop.getClass().getSimpleName();
        JButton setManualPruningDate = UIButtons.createRoundedButton("Manual Pruning Date");
        switch (cropType) {
            case "HerbCrop":
                pruningDateLabel.setText("Pruning Date: " + ((HerbCrop)crop).getPruningDate());
                infoPanel.add(pruningDateLabel);

                JLabel activeCompoundsLabel = new JLabel();
                activeCompoundsLabel.setFont(UIFont.lexend(Font.PLAIN, 16));
                activeCompoundsLabel.setForeground(UIColors.BUTTON_COLOR);
                activeCompoundsLabel.setText("Active Compounds: " +
                        (((HerbCrop) crop).getActiveCompounds() != null ? ((HerbCrop) crop).getActiveCompounds() : "None"));
                infoPanel.add(activeCompoundsLabel);

                setCalculatedPruningDateBtn.addActionListener(e -> {
                    if (!(crop instanceof HerbCrop)) return;
                    HerbCrop herb = (HerbCrop) crop;

                    // Calculate pruning date
                    herb.setCalculatedPruningDate();

                    // Update label
                    pruningDateLabel.setText("Pruning Date: " + herb.getPruningDate());

                    // Persist via controller
                    CropController.getInstance().updateCrop(
                            "HerbCrop",
                            herb.getID(),
                            herb.getPlantType(),
                            herb.getSoilType(),
                            herb.getLastFertilized(),
                            herb.getDatePlanted(),
                            herb.getWidth(),
                            herb.getHeight(),
                            herb.getLength(),
                            herb.getUserId(),
                            herb.getPruningDate(),
                            herb.getUserBaseGrowingDays(),
                            herb.getActiveCompounds(),
                            null  // userRootDensity not used for HerbCrop
                    );
                });
                imageBGPanel.add(setCalculatedPruningDateBtn);
                imageBGPanel.add(Box.createVerticalStrut(8));
                setManualPruningDate.setPreferredSize(new Dimension(200, 35));
                setManualPruningDate.setMaximumSize(new Dimension(200, 35));
                setManualPruningDate.addActionListener(e -> {
                    if (!(crop instanceof HerbCrop)) return;
                    HerbCrop herb = (HerbCrop) crop;

                    String input = JOptionPane.showInputDialog(
                            CropCardPanel.this,
                            "Enter pruning date (YYYY-MM-DD):",
                            herb.getPruningDate() != null ? herb.getPruningDate() : LocalDate.now().toString()
                    );

                    if (input != null && !input.isBlank()) {
                        try {
                            LocalDate.parse(input);  // validate date format
                            herb.setExplicitPruningDate(input);
                            pruningDateLabel.setText("Pruning Date: " + herb.getPruningDate());

                            // Persist via controller
                            CropController.getInstance().updateCrop(
                                    "HerbCrop",
                                    herb.getID(),
                                    herb.getPlantType(),
                                    herb.getSoilType(),
                                    herb.getLastFertilized(),
                                    herb.getDatePlanted(),
                                    herb.getWidth(),
                                    herb.getHeight(),
                                    herb.getLength(),
                                    herb.getUserId(),
                                    herb.getPruningDate(),
                                    herb.getUserBaseGrowingDays(),
                                    herb.getActiveCompounds(),
                                    null  // userRootDensity not used for HerbCrop
                            );

                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(
                                    CropCardPanel.this,
                                    "Invalid date format. Use YYYY-MM-DD",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                });
                imageBGPanel.add(setManualPruningDate);
                imageBGPanel.add(Box.createVerticalStrut(8));                JButton setActiveCompoundsBtn = UIButtons.createRoundedButton("Edit Active Compounds");
                setActiveCompoundsBtn.setPreferredSize(new Dimension(200, 35));
                setActiveCompoundsBtn.setMaximumSize(new Dimension(200, 35));
                setActiveCompoundsBtn.addActionListener(e -> {
                    HerbCrop herb = (HerbCrop) crop;
                    String input = JOptionPane.showInputDialog(
                            CropCardPanel.this,
                            "Enter active compounds description:",
                            herb.getActiveCompounds() != null ? herb.getActiveCompounds() : ""
                    );
                    if (input != null) {
                        herb.setActiveCompounds(input);
                        activeCompoundsLabel.setText("Active Compounds: " + (input.isBlank() ? "None" : input));

                        // Persist via controller
                        CropController.getInstance().updateCrop(
                                "HerbCrop",
                                herb.getID(),
                                herb.getPlantType(),
                                herb.getSoilType(),
                                herb.getLastFertilized(),
                                herb.getDatePlanted(),
                                herb.getWidth(),
                                herb.getHeight(),
                                herb.getLength(),
                                herb.getUserId(),
                                herb.getPruningDate(),
                                herb.getUserBaseGrowingDays(),
                                herb.getActiveCompounds(),
                                null
                        );
                        activeCompoundsLabel.setText("Active Compounds: " + (input.isBlank() ? "None" : input));
                        activeCompoundsLabel.revalidate();
                        activeCompoundsLabel.repaint();
                    }
                });

                imageBGPanel.add(setActiveCompoundsBtn);
                imageBGPanel.add(Box.createVerticalStrut(8));                break;
            case "LeafCrop":
                pruningDateLabel.setText("Pruning Date: " + ((LeafCrop)crop).getPruningDate());
                infoPanel.add(pruningDateLabel);

                setCalculatedPruningDateBtn.addActionListener(e -> {
                    ((LeafCrop) crop).setCalculatedPruningDate();
                    pruningDateLabel.setText("Pruning Date: " + ((LeafCrop) crop).getPruningDate());

                    // Persist via controller
                    LeafCrop leaf = (LeafCrop) crop;
                    CropController.getInstance().updateCrop(
                            "LeafCrop",
                            leaf.getID(),
                            leaf.getPlantType(),
                            leaf.getSoilType(),
                            leaf.getLastFertilized(),
                            leaf.getDatePlanted(),
                            leaf.getWidth(),
                            leaf.getHeight(),
                            leaf.getLength(),
                            leaf.getUserId(),
                            leaf.getPruningDate(),
                            leaf.getUserBaseGrowingDays(),
                            null,  // activeCompounds not used for LeafCrop
                            null   // userRootDensity not used for LeafCrop
                    );
                });
                imageBGPanel.add(setCalculatedPruningDateBtn);
                imageBGPanel.add(Box.createVerticalStrut(8));
                setManualPruningDate.setPreferredSize(new Dimension(200, 35));
                setManualPruningDate.setMaximumSize(new Dimension(200, 35));
                setManualPruningDate.addActionListener(e -> {
                    LeafCrop leaf = (LeafCrop) crop;
                    String input = JOptionPane.showInputDialog(
                            CropCardPanel.this,
                            "Enter pruning date (YYYY-MM-DD):",
                            leaf.getPruningDate() != null ? leaf.getPruningDate() : LocalDate.now().toString()
                    );

                    if (input != null && !input.isBlank()) {
                        try {
                            LocalDate.parse(input);  // Validate format
                            leaf.setExplicitPruningDate(input);
                            pruningDateLabel.setText("Pruning Date: " + leaf.getPruningDate());

                            // Persist via controller
                            CropController.getInstance().updateCrop(
                                    "LeafCrop",
                                    leaf.getID(),
                                    leaf.getPlantType(),
                                    leaf.getSoilType(),
                                    leaf.getLastFertilized(),
                                    leaf.getDatePlanted(),
                                    leaf.getWidth(),
                                    leaf.getHeight(),
                                    leaf.getLength(),
                                    leaf.getUserId(),
                                    leaf.getPruningDate(),
                                    leaf.getUserBaseGrowingDays(),
                                    null,  // activeCompounds not used for LeafCrop
                                    null   // userRootDensity not used for LeafCrop
                            );

                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(
                                    CropCardPanel.this,
                                    "Invalid date format. Use YYYY-MM-DD",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                });
                imageBGPanel.add(setManualPruningDate);
                imageBGPanel.add(Box.createVerticalStrut(8));
                break;
            case "RootCrop":
                JButton getEstimatedMassBtn = UIButtons.createRoundedButton("Estimated Mass");
                getEstimatedMassBtn.setPreferredSize(new Dimension(200, 35));
                getEstimatedMassBtn.setMaximumSize(new Dimension(200, 35));
                getEstimatedMassBtn.addActionListener(e -> {
                    if (crop instanceof RootCrop rootCrop) {
                        double mass = rootCrop.estimateMass();
                        JOptionPane.showMessageDialog(CropCardPanel.this,
                                "Estimated Mass: " + mass + " kg",
                                "Estimated Mass",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(CropCardPanel.this,
                                "This crop is not a RootCrop!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });

                imageBGPanel.add(getEstimatedMassBtn);
                imageBGPanel.add(Box.createVerticalStrut(8));
                break;
            default:
                break;
        }

        imageBGPanel.add(viewLogsBtn);
        imageBGPanel.add(Box.createVerticalStrut(8));
        JButton deleteBtn = UIButtons.createRoundedButton("Delete Crop");
        deleteBtn.setPreferredSize(new Dimension(200, 35));
        deleteBtn.setMaximumSize(new Dimension(200, 35));
        deleteBtn.addActionListener(e -> {
            if (crop == null) return;

            try {
                boolean success = CropController.getInstance().delete(crop.getID());
                if (success) {
                    Container parent = CropCardPanel.this.getParent();
                    if (parent != null) {
                        parent.remove(CropCardPanel.this);
                        parent.revalidate();
                        parent.repaint();
                    }
                } else {
                    System.err.println("Failed to delete crop with ID: " + crop.getID());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


// Add the delete button to your imageBGPanel along with other buttons
        imageBGPanel.add(deleteBtn);

        imageBGPanel.setOpaque(false);
        imageBGPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        infoPanel.add(Box.createVerticalStrut(10));
        add(infoPanel, BorderLayout.CENTER);
        add(imageBGPanel, BorderLayout.EAST);

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

    class RoundedRightImagePanel extends JPanel {
        private Image backgroundImage;
        private int cornerRadius;

        public RoundedRightImagePanel(String imagePath, int cornerRadius) {
            this.cornerRadius = cornerRadius;
            try (InputStream is = getClass().getResourceAsStream(imagePath)) {
                if (is == null) {
                    System.err.println("Resource not found: " + imagePath);
                }
                backgroundImage = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.err.println("Could not read image data for: " + imagePath);
            }
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (backgroundImage != null) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float();

                path.moveTo(0, 0);
                path.lineTo(w - cornerRadius, 0);
                path.quadTo(w, 0, w, cornerRadius);
                path.lineTo(w, h - cornerRadius);
                path.quadTo(w, h, w - cornerRadius, h);
                path.lineTo(0, h);
                path.lineTo(0, 0);
                path.closePath();
                g2.setClip(path);

                g2.drawImage(backgroundImage, 0, 0, w, h, this);

                g2.dispose();
            }
            // Paint the children (The "View Logs" button)
            super.paintComponent(g);
        }
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

    public class CustomComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString()); // Set the text of the item
            setFont(UIFont.lexend(Font.PLAIN, 14));
            // Apply custom styling based on state or value
            if (isSelected) {
                setForeground(UIColors.TEXT_COLOR);
                setBackground(UIColors.BG_COLOR_GENERAL);
            } else {
                setBackground(list.getBackground());
                setForeground(UIColors.TEXT_DARK);
            }

            setOpaque(true); // Must be true for background to be visible
            return this;
        }
    }
}