package com.plantfarmlogger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.plantfarmlogger.controller.dao.CropDao;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.MainWindow;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger starting...");

        CropDao cbd = new CropDao();
        LocalDate ld = LocalDate.now();
        cbd.create(new Crop("type1", "type2", "type3", ld.toString(), 12.12, 32.4, 33.3));

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            MainWindow mainWindow = new MainWindow();

            User testUser = new User("John", "john123", "Farm Address", 19, "123");

            // AppNavigator -> Creates HomeController -> Creates HomeView
            mainWindow.getNavigator().showCropLogs(testUser);

            mainWindow.setVisible(true);
        });
    }
}