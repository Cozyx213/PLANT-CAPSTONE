package com.plantfarmlogger.view;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.view.components.BaseDashboardView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Home extends BaseDashboardView {
    private JPanel cardsContainer;
    private JLabel countLabel;
    private JButton addBtn;
    private JScrollPane scrollPane;

    public Home(User user) {
        super(user);
    }

    @Override
    protected JPanel createContentPanel() {
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
            System.err.println("Error: Image resource not found at /files/logo.png");
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

        return content;
    }

    public void setAddButtonListener(ActionListener action) {
        addBtn.addActionListener(action);
    }

    public void setCountLabelText(String text) {
        countLabel.setText(text);
    }

    public void addCardComponent(JComponent card) {
        cardsContainer.add(card, 0);
        cardsContainer.add(Box.createVerticalStrut(20), 1);
        refreshLayout();
    }

    public void removeCardComponent(JComponent card) {
        cardsContainer.remove(card);
        refreshLayout();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    private void refreshLayout() {
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }
}