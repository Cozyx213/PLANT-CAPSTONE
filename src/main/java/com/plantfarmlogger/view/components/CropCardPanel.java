package com.plantfarmlogger.view.components;

import com.plantfarmlogger.util.UIButtons;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.util.UIFont;
import com.plantfarmlogger.util.UIRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;

public class CropCardPanel extends JPanel {

    private JTextField nameField;
    private JLabel nameLabel;
    private JPanel actionPanel;

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
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Fixed height

        initInputMode();
    }

    private void initInputMode() {
        removeAll(); // Clear previous components

        // --- Input Field ---
        nameField = new JTextField();
        nameField.setFont(UIFont.lexend(Font.BOLD, 18));
        nameField.setForeground(UIColors.TEXT_DARK);
        // Add a prompt/border style here if desired

        // --- Buttons (Save / Cancel) ---
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setOpaque(false);

        JButton saveBtn = UIButtons.createPrimaryButton("Save");
        saveBtn.addActionListener(e -> {
            if (!nameField.getText().trim().isEmpty()) {
                switchToDisplayMode(nameField.getText());
                onSave.accept(nameField.getText()); // Tell Controller we are done editing
            }
        });

        JButton cancelBtn = UIButtons.createTextButton("Cancel");
        cancelBtn.addActionListener(e -> onCancel.run());

        buttons.add(cancelBtn);
        buttons.add(saveBtn);

        add(nameField, BorderLayout.CENTER);
        add(buttons, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    private void switchToDisplayMode(String name) {
        removeAll();

        nameLabel = new JLabel(name);
        nameLabel.setFont(UIFont.lexend(Font.BOLD, 20));
        nameLabel.setForeground(UIColors.TEXT_DARK);

        JButton viewLogsBtn = UIButtons.createRoundedButton("View Logs");
        viewLogsBtn.setPreferredSize(new Dimension(120, 35));
        viewLogsBtn.addActionListener(e -> onNavigate.accept(name));

        add(nameLabel, BorderLayout.WEST);
        add(viewLogsBtn, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        UIRenderer.paintRoundedPanel(g, this, 20, Color.WHITE);
        super.paintComponent(g);
    }
}