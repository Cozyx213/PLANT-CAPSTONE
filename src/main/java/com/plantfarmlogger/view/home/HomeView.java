package com.plantfarmlogger.view.home;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.components.BaseDashboardView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends BaseDashboardView {

    private JPanel cardsContainer;
    private JLabel countLabel;
    private JButton addBtn;

    public HomeView(User user) {
        super(user);
    }

    @Override
    protected JPanel createContentPanel() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIColors.BG_COLOR);

        // --- Header ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIColors.BG_COLOR);
        header.setBorder(new EmptyBorder(40, 40, 20, 40));

        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setOpaque(false);

        JLabel title = new JLabel("My Farm");
        title.setFont(UIFont.lexend(Font.BOLD, 36));
        title.setForeground(UIColors.BUTTON_COLOR);

        countLabel = new JLabel("Number of Crop Beds: 0");
        countLabel.setFont(UIFont.lexend(Font.PLAIN, 14));
        countLabel.setForeground(UIColors.TEXT_DARK);

        titleBlock.add(title);
        titleBlock.add(Box.createVerticalStrut(5));
        titleBlock.add(countLabel);

        addBtn = UIButtons.createPrimaryButton("Add Cropbed");

        header.add(titleBlock, BorderLayout.WEST);
        header.add(addBtn, BorderLayout.EAST);
        content.add(header, BorderLayout.NORTH);

        // --- List Section ---
        cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        cardsContainer.setBackground(UIColors.BG_COLOR);
        cardsContainer.setBorder(new EmptyBorder(0, 40, 40, 40));
        cardsContainer.add(Box.createVerticalGlue()); // Push items up

        JScrollPane scroll = new JScrollPane(cardsContainer);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        content.add(scroll, BorderLayout.CENTER);

        return content;
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