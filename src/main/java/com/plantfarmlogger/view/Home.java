package com.plantfarmlogger.view;

import com.plantfarmlogger.controller.CropController;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.components.BaseDashboardView;
import com.plantfarmlogger.view.components.CropCardPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Home extends BaseDashboardView {
    private JPanel cardsContainer;
    private JLabel countLabel;
    private JButton addBtn;
    private JScrollPane scrollPane;
    private boolean isCreating = false;
    private Runnable onSave;
    private Consumer<Crop> onNavigate;
    private Runnable onCancel;

    public Home(User user, AppNavigator appNavigator) {
        super(user,  appNavigator);
    }

    @Override
    protected JPanel createContentPanel() {
        onSave = () -> {
            updateCountLabel(cropController.getCountByUser(user.getId()));
            isCreating = false;
            toggleAddButton(true); // Unlock
        };
        onNavigate = crop -> {
            System.out.println("Navigating to logs for: " + crop.getPlantType() + "-" + crop.getID());
            navigator.showCropLogs(user, crop);
        };
        onCancel = () -> {
            removeTopCard(); // Helper method to remove the temp card
            isCreating = false;
            toggleAddButton(true); // Unlock
        };
        int CropBeds = 0;
        java.net.URL imageUrl = getClass().getResource("/plant-icon.png");
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIColors.BG_COLOR);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIColors.BG_COLOR);
        header.setBorder(new EmptyBorder(40, 40, 20, 40));

        JPanel farmBlock = new JPanel();
        farmBlock.setLayout(new BoxLayout(farmBlock, BoxLayout.X_AXIS));
        farmBlock.setOpaque(false);
        farmBlock.setAlignmentY(Component.LEFT_ALIGNMENT);
        farmBlock.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setOpaque(false);
        titleBlock.setAlignmentY(Component.LEFT_ALIGNMENT);
        titleBlock.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel plantIcon = new JLabel("");
        if (imageUrl != null) {
            ImageIcon logoIcon = new ImageIcon(imageUrl);
            Image image = logoIcon.getImage();
            Image scaledLogoImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
            plantIcon = new JLabel(scaledLogoIcon);
        } else {
            System.err.println("Error: Image resource not found at /logo.png");
        }

        JLabel title = new JLabel("My Farm");
        title.setFont(UIFont.lexend(Font.BOLD, 36));
        title.setForeground(UIColors.BUTTON_COLOR);
        title.setOpaque(false);
        title.setBorder(null);

        countLabel = new JLabel("Number of Crop Beds: " + CropBeds);
        countLabel.setFont(UIFont.lexend(Font.PLAIN, 14));
        countLabel.setForeground(UIColors.TEXT_DARK);

        farmBlock.add(plantIcon);
        farmBlock.add(Box.createHorizontalStrut(10));
        farmBlock.add(title);
        titleBlock.add(farmBlock);
        titleBlock.add(countLabel);

        addBtn = UIButtons.createRoundedButton("+ Add crop bed");

        header.add(titleBlock, BorderLayout.WEST);
        header.add(addBtn, BorderLayout.EAST);
        content.add(header, BorderLayout.NORTH);

        cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        cardsContainer.setBackground(UIColors.BG_COLOR);
        cardsContainer.setBorder(new EmptyBorder(0, 40, 40, 40));
        cardsContainer.add(Box.createVerticalGlue());

        scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        content.add(scrollPane, BorderLayout.CENTER);

        CropController  cropController = CropController.getInstance();
        ArrayList<Crop> cropsOfUser= cropController.getAllByUserId(user.getId());
        for(Crop crop : cropsOfUser){
            CropCardPanel  cardPanel = new CropCardPanel(crop, onNavigate);
            addCardToTop(cardPanel);
        }
        setAddButtonListener(e->handleAddNewCard());

        return content;
    }

    private void handleAddNewCard() {
        if (isCreating) return; // Prevent multiple cards

        // lock the UI
        isCreating = true;
        toggleAddButton(false); // Helper method we need to add to View

        // create,add card
        CropCardPanel card = new CropCardPanel(user, onSave, onNavigate, onCancel);
        addCardToTop(card);
    }
    // --- Exposed Methods for Controller ---

    public void setAddButtonListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }

    public void updateCountLabel(int count) {
        countLabel.setText("Number of Crop Beds: " + count);
    }

    public void addCardToTop(JPanel card) {
        // Index 0 puts it at the top
        cardsContainer.add(card, 0);
        cardsContainer.add(Box.createVerticalStrut(20), 1); // Spacer
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    // Inside HomeView class

    public void toggleAddButton(boolean enabled) {
        addBtn.setEnabled(enabled);
        // visually dim the button if needed,
        // though setEnabled usually handles that.
    }

    public void removeTopCard() {
        // Index 0 is the new card, Index 1 is the spacer
        try {
            cardsContainer.remove(0);
            cardsContainer.remove(0); // Remove the spacer too
            cardsContainer.revalidate();
            cardsContainer.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}