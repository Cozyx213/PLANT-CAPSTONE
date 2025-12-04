package com.plantfarmlogger.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIButtons {

    public static JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                // Assuming UIRenderer handles the background painting completely
                UIRenderer.paintRoundedButton(g, this, 15);
                super.paintComponent(g); // Draw text/icon on top
            }
        };

        applyCommonStyles(btn, UIFont.lexend(Font.PLAIN, 16), UIColors.TEXT_COLOR);
        btn.setBackground(UIColors.BUTTON_COLOR);
        return btn;
    }


    // rounded button with hover styles
    public static JButton createRoundedButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                UIRenderer.paintDynamicButton(g, this);
                super.paintComponent(g);
            }
        };

        applyCommonStyles(btn, UIFont.lexend(Font.BOLD, 16), Color.WHITE);

        // Specific hover logic for this button type
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(new Color(230, 230, 230)); }
            public void mouseExited(MouseEvent e) { btn.setForeground(Color.WHITE); }
        });

        return btn;
    }


    // text only button rani
    public static JButton createTextButton(String text) {
        JButton btn = new JButton(text);
        applyCommonStyles(btn, UIFont.lexend(Font.PLAIN, 14), UIColors.TEXT_DARK);
        btn.setBackground(new Color(0, 0, 0, 0)); // Transparent
        return btn;
    }


    // creating the settings button
    public static JButton createSettingsButton(String text) {
        JButton btn = new JButton(text);
        applyCommonStyles(btn, UIFont.lexend(Font.BOLD, 14), UIColors.SUB_TEXT_COLOR); // Default state color
        btn.setBackground(new Color(0, 0, 0, 0));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(UIColors.TEXT_COLOR); }
            public void mouseExited(MouseEvent e) { btn.setForeground(UIColors.SUB_TEXT_COLOR); }
        });

        return btn;
    }

    // helper method sa pag-set up sa properties sa button
    private static void applyCommonStyles(JButton btn, Font font, Color fgColor) {
        btn.setFont(font);
        btn.setForeground(fgColor);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBorder(null);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    private UIButtons() {}
}
