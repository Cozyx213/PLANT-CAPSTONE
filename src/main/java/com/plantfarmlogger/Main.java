package com.plantfarmlogger;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.MainWindow;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set native look and feel for better aesthetics
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            // 1. Create a dummy user
            User testUser = new User("John", "john123", "Farm Address", 19, "123");

            // 2. Load the MainWindow
            MainWindow mainWindow = new MainWindow();

            // 3. Force navigate to CropLogs
            mainWindow.getNavigator().showCropLogs(testUser);

            // 4. Show Frame
            mainWindow.setVisible(true);
        });
    }
}