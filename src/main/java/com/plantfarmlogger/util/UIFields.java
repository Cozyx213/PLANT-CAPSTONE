package com.plantfarmlogger.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class UIFields {

    private static final Dimension FIELD_SIZE = new Dimension(280, 40);

    public static JTextField createRoundedField() {
        JTextField field = new JTextField() {

            @Override
            protected void paintComponent(Graphics g) {
                UIRenderer.paintRoundedBackground(g, this);
                super.paintComponent(g);
            }
        };

        setupRoundedDefaults(field);
        return field;
    }

    public static JPasswordField createRoundedPasswordField() {
        JPasswordField field = new JPasswordField() {

            @Override
            protected void paintComponent(Graphics g) {
                UIRenderer.paintRoundedBackground(g, this);
                super.paintComponent(g);
            }
        };

        setupRoundedDefaults(field);
        return field;
    }

    public static JTextField createUnderlineField(String placeholder) {
        JTextField tf = new JTextField(placeholder);
        tf.setBorder(new MatteBorder(0, 0, 1, 0, UIColors.TEXT_DARK));
        tf.setOpaque(false);
        tf.setForeground(Color.GRAY);
        tf.setFont(UIFont.lexend(Font.PLAIN, 14));

        tf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (tf.getText().equals(placeholder)) {
                    tf.setText("");
                    tf.setForeground(UIColors.TEXT_DARK);
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

    private static void setupRoundedDefaults(JTextField field) {
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(5, 15, 5, 15));
        field.setBackground(UIColors.TEXT_FIELD_BG);
        field.setForeground(UIColors.TEXT_DARK);
        field.setFont(UIFont.lexend(Font.PLAIN, 16));
        field.setPreferredSize(FIELD_SIZE);
        field.setMaximumSize(FIELD_SIZE);
    }

    public static JTextField createStyledField(String placeholder) {
        JTextField tf = new JTextField(placeholder);
        tf.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, UIColors.TEXT_DARK),
                new EmptyBorder(5, 0, 5, 0)));
        tf.setOpaque(false);
        tf.setBackground(new Color(0, 0, 0, 0));
        tf.setForeground(Color.GRAY);
        tf.setFont(UIFont.lexend(Font.PLAIN, 14));

        tf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (tf.getText().equals(placeholder)) {
                    tf.setText("");
                    tf.setForeground(UIColors.TEXT_DARK);
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

    private UIFields() {
    }
}
