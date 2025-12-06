package com.plantfarmlogger.util;

import javax.swing.*;
import java.awt.*;

public class UIRenderer {

    public static void paintRoundedBackground(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(c.getBackground());
        g2.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 30, 30);

        g2.dispose();
    }

    public static void paintRoundedButton(Graphics g, JButton btn, int radius) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(btn.getBackground());
        g2.fillRoundRect(0, 0, btn.getWidth(), btn.getHeight(), radius, radius);

        g2.dispose();
    }

    public static void paintDynamicButton(Graphics g, JButton btn) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (btn.getModel().isPressed()) {
            g2.setColor(UIColors.BUTTON_COLOR.darker());
        } else if (btn.getModel().isRollover()) {
            g2.setColor(UIColors.BUTTON_HOVER_COLOR);
        } else {
            g2.setColor(UIColors.BUTTON_COLOR);
        }

        g2.fillRoundRect(0, 0, btn.getWidth(), btn.getHeight(), 40, 40);
        g2.dispose();
    }

    public static void paintRoundedPanel(Graphics g, JComponent c, int radius, Color color) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(color);

        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);

        g2.dispose();
    }
    private UIRenderer() {}
}
