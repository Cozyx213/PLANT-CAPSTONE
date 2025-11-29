package com.plantfarmlogger.util;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIFactory {

    // --- Colors ---
    public static final Color BG_COLOR = new Color(242, 238, 227); // Dashboard BG
    public static final Color BG_COLOR_GENERAL = new Color(113, 165, 84); // Login/Register BG

    public static final Color CARD_COLOR = new Color(226, 237, 214);
    public static final Color BUTTON_COLOR = new Color(56, 89, 57);
    public static final Color BUTTON_HOVER_COLOR = new Color(64, 95, 69);

    public static final Color TEXT_DARK = new Color(50, 50, 50);
    public static final Color TEXT_COLOR = Color.WHITE;
    public static final Color TEXT_FIELD_BG = new Color(220, 220, 220);
    public static final Color ERROR_COLOR = new Color(180, 60, 60);

    // --- Fonts ---
    public static Font getLexend(int style, float size) {
        return new Font("SansSerif", style, (int)size);
    }

    // --- Buttons ---

    /**
     * Creates the Dashboard-style button (15px radius, smaller).
     */
    public static JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                paintRoundedButton(g, this, 15); // Reuse helper logic
            }
        };
        btn.setFont(getLexend(Font.PLAIN, 16));
        btn.setBackground(BUTTON_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 40));
        return btn;
    }

    /**
     * Creates the Login/Register-style button (40px radius, pill shape).
     */
    public static RoundedButton createRoundedButton(String text) {
        RoundedButton btn = new RoundedButton(text);
        // Default dimensions, can be overridden by caller
        btn.setPreferredSize(new Dimension(200, 45));
        return btn;
    }

    public static JButton createTextButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(getLexend(Font.PLAIN, 14));
        btn.setForeground(TEXT_DARK);
        btn.setBackground(new Color(0,0,0,0));
        btn.setOpaque(false);
        btn.setBorder(null);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- Text Fields ---

    public static JTextField createStyledField(String placeholder) {
        JTextField tf = new JTextField(placeholder);
        tf.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, TEXT_DARK),
                new EmptyBorder(5, 0, 5, 0)
        ));
        tf.setOpaque(false);
        tf.setBackground(new Color(0,0,0,0));
        tf.setForeground(Color.GRAY);
        tf.setFont(getLexend(Font.PLAIN, 14));

        // Placeholder Logic
        tf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (tf.getText().equals(placeholder)) {
                    tf.setText("");
                    tf.setForeground(TEXT_DARK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (tf.getText().isEmpty()) {
                    tf.setText(placeholder);
                    tf.setForeground(Color.GRAY);
                }
            }
        });
        return tf;
    }

    public static JTextField createDateField(String placeholder) {
        JTextField tf = createStyledField(placeholder + " (YYYY-MM-DD)");
        tf.setToolTipText("Format: YYYY-MM-DD (e.g., 2025-11-29)");
        return tf;
    }

    public static JTextField createRoundedField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                paintRoundedBackground(g, this); // Use helper
                super.paintComponent(g);
            }
        };
        setupRoundedField(field);
        return field;
    }

    public static JPasswordField createRoundedPasswordField() {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                paintRoundedBackground(g, this); // Use helper
                super.paintComponent(g);
            }
        };
        setupRoundedField(field);
        return field;
    }

    // --- Helpers ---

    private static void setupRoundedField(JTextField field) {
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(5, 15, 5, 15));
        field.setBackground(TEXT_FIELD_BG); // Use the variable
        field.setForeground(TEXT_DARK);
        field.setFont(getLexend(Font.PLAIN, 16));
        field.setPreferredSize(new Dimension(300, 40));
    }

    private static void paintRoundedBackground(Graphics g, JComponent c) {
        if (!c.isOpaque() && c.getBorder() instanceof javax.swing.border.Border) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 30, 30);
            g2.dispose();
        }
    }

    private static void paintRoundedButton(Graphics g, JButton btn, int radius) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(btn.getBackground());
        g2.fillRoundRect(0, 0, btn.getWidth(), btn.getHeight(), radius, radius);
        g2.dispose();
    }

    // --- Inner Classes ---

    public static class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.WHITE);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setFont(getLexend(Font.BOLD, 16)); // Default Font

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    setForeground(new Color(230, 230, 230));
                }
                public void mouseExited(MouseEvent evt) {
                    setForeground(Color.WHITE);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(BUTTON_COLOR.darker());
            } else if (getModel().isRollover()) {
                g2.setColor(BUTTON_HOVER_COLOR);
            } else {
                g2.setColor(BUTTON_COLOR);
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}