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
                UIRenderer.paintRoundedButton(g, this, 15);
            }
        };

        btn.setFont(UIFont.lexend(Font.PLAIN, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(UIColors.BUTTON_COLOR);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return btn;
    }

    public static JButton createRoundedButton(String text) {
        return new RoundedButton(text);
    }

    private static class RoundedButton extends JButton {

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.WHITE);
            setFont(UIFont.lexend(Font.BOLD, 16));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    setForeground(new Color(230, 230, 230));
                }

                public void mouseExited(MouseEvent e) {
                    setForeground(Color.WHITE);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            UIRenderer.paintDynamicButton(g, this);
            super.paintComponent(g);
        }
    }

    private UIButtons() {}
}
