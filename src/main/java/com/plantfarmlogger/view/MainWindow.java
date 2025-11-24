package com.plantfarmlogger.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("PLAnt capstone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new BorderLayout());
        JLabel l = new JLabel("Hello — this is the UI", SwingConstants.CENTER);
        p.add(l, BorderLayout.CENTER);

        JButton b = new JButton("Click me");
        b.addActionListener(e -> JOptionPane.showMessageDialog(this, "Button clicked"));
        p.add(b, BorderLayout.SOUTH);

        setContentPane(p);
    }
}