package com.plantfarmlogger;

import java.time.LocalDate;

import javax.swing.SwingUtilities;

import com.plantfarmlogger.controller.dao.CropDao;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.MainWindow;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger start");

        CropDao cbd = new CropDao();
        LocalDate ld = LocalDate.now();
        cbd.create(new Crop("type1" ,"type2" , "typ3" , ld.toString() , 12.12 ,32.4 , 33.3 ));
        SwingUtilities.invokeLater(() -> {
            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            User testUser = new User("John", "john123", "Farm Address", 19, "123");

            MainWindow mainWindow = new MainWindow();

            mainWindow.getNavigator().showHome(testUser);

            mainWindow.setVisible(true);
        });
    }
}