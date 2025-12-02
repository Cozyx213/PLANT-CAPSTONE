package com.plantfarmlogger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.plantfarmlogger.view.MainWindow;

public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger start");
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            MainWindow mainWindow = new MainWindow();

            mainWindow.setVisible(true);
        });
    }
}