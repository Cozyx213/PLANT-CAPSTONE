package com.plantfarmlogger;

import java.time.LocalDate;


import javax.swing.SwingUtilities;

import com.plantfarmlogger.controller.dao.CropDao;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.view.MainWindow;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger start");
        // show Swing UI on EDT\
        CropDao cbd = new CropDao();
        LocalDate ld = LocalDate.now();
        cbd.create(new Crop("type1" ,"type2" , "typ3" , ld.toString() , 12.12 ,32.4 , 33.3 ));
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