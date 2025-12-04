package com.plantfarmlogger;

import java.time.LocalDate;

import javax.swing.SwingUtilities;

import com.plantfarmlogger.controller.dao.CropDao;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.model.subclasses.LeafCrop;
import com.plantfarmlogger.model.subclasses.RootCrop;
import com.plantfarmlogger.model.subclasses.HerbCrop;
import com.plantfarmlogger.view.MainWindow;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger start");
        CropDao cd =  CropDao.getInstance();
         cd.create( (Crop) new  LeafCrop( "Lettuce", "Loamy", "2024-10-01", "2024-09-15", 10.0, 5.0, 15.0));
         cd.create((Crop) new RootCrop( "Carrot", "Sandy", "2024-10-05", "2024-09-20", 8.0, 4.0, 12.0));
         cd.create((Crop) new HerbCrop("Basil", "Clay", "2024-10-03", "2024-09-18", 6.0, 3.0, 9.0, "2024-10-10", 20, "Eugenol"));
        cd.printU();
        SwingUtilities.invokeLater(() -> {
            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

           // User testUser = new User("John", "john123", "Farm Address", 19, "123");

            MainWindow mainWindow = new MainWindow();

            mainWindow.getNavigator().showLogin();

            mainWindow.setVisible(true);
        });
    }
}