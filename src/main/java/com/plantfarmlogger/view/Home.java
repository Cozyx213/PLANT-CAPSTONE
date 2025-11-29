package com.plantfarmlogger.view;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIFactory;
import com.plantfarmlogger.view.components.BaseDashboardView;
import com.plantfarmlogger.view.components.CropCardPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Home extends BaseDashboardView {

    private JPanel cardsContainer;
    private JLabel countLabel;
    private int cropBedCount = 0;

    // State Management
    private boolean isCreating = false; // Prevents multiple "Add" clicks
    private ArrayList<CropCardPanel> cropList;

    public Home(User user) {
        super(user);
        this.cropList = new ArrayList<>();
    }

    @Override
    protected JPanel createContentPanel() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIFactory.BG_COLOR);

        // --- Header ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIFactory.BG_COLOR);
        header.setBorder(new EmptyBorder(40, 40, 20, 40));

        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setOpaque(false);

        JLabel title = new JLabel("My Farm");
        title.setFont(UIFactory.getLexend(Font.BOLD, 36));
        title.setForeground(UIFactory.BUTTON_COLOR);

        countLabel = new JLabel("Number of Crop Beds: 0");
        countLabel.setFont(UIFactory.getLexend(Font.PLAIN, 14));
        countLabel.setForeground(UIFactory.TEXT_DARK);

        titleBlock.add(title);
        titleBlock.add(Box.createVerticalStrut(5));
        titleBlock.add(countLabel);

        JButton addBtn = UIFactory.createPrimaryButton("Add Cropbed");
        addBtn.addActionListener(e -> addNewCropCard());

        header.add(titleBlock, BorderLayout.WEST);
        header.add(addBtn, BorderLayout.EAST);

        content.add(header, BorderLayout.NORTH);

        // --- List Container ---
        cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        cardsContainer.setBackground(UIFactory.BG_COLOR);
        cardsContainer.setBorder(new EmptyBorder(0, 40, 40, 40));

        // Important: Use glue to push everything up, keeping sizes consistent
        cardsContainer.add(Box.createVerticalGlue());

        JScrollPane scroll = new JScrollPane(cardsContainer);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        content.add(scroll, BorderLayout.CENTER);

        return content;
    }

    private void addNewCropCard() {
        if (isCreating) {
            JOptionPane.showMessageDialog(this, "Please save or cancel the current Crop Bed first.");
            return;
        }

        isCreating = true; // Lock creation

        // Define callbacks for the new card

        // 1. On Save: Unlock creation, update count
        Runnable onSave = () -> {
            isCreating = false;
            cropBedCount++;
            countLabel.setText("Number of Crop Beds: " + cropBedCount);
            refreshLayout();
        };

        // 2. On Cancel: Unlock creation, remove the card from UI
        // We need a reference to the card, but we can't reference it before creation.
        // So we create a wrapper for the action.
        final CropCardPanel[] ref = new CropCardPanel[1];

        Runnable onCancel = () -> {
            isCreating = false;
            if (ref[0] != null) {
                cardsContainer.remove(ref[0]);
                // We also need to remove the strut (gap) we added.
                // Since we added at index 0 and 1, removing index 0 puts the strut at 0.
                // It's safer to just refresh the whole container or handle index carefully.
                // For simplicity, we remove the specific component ref[0].
                // The strut remains but it's just space. For a cleaner removal, we can remove the next component too.
                refreshLayout();
            }
        };

        CropCardPanel newCard = new CropCardPanel(onSave, onCancel);
        ref[0] = newCard; // Set reference for the cancel action

        cropList.add(newCard);

        // Add to UI: Index 0 (Top)
        cardsContainer.add(newCard, 0);
        cardsContainer.add(Box.createVerticalStrut(20), 1); // Spacer

        refreshLayout();

        // Scroll to top
        SwingUtilities.invokeLater(() -> ((JScrollPane)cardsContainer.getParent().getParent()).getVerticalScrollBar().setValue(0));
    }

    private void refreshLayout() {
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }
}