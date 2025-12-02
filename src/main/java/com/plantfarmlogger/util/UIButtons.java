package com.plantfarmlogger.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

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

    public static JButton createTextButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(UIFont.lexend(Font.PLAIN, 14));
        btn.setForeground(UIColors.TEXT_DARK);
        btn.setBackground(null);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(UIColors.BUTTON_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                btn.setForeground(UIColors.TEXT_DARK);
            }
        });
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

    private UIButtons() {
    }
}
