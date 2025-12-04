package com.plantfarmlogger;

import java.time.LocalDate;

import javax.swing.SwingUtilities;

import com.plantfarmlogger.controller.dao.CropDao;
import com.plantfarmlogger.controller.dao.CropLogDao;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.enums.*;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.model.subclasses.LeafCrop;
import com.plantfarmlogger.model.subclasses.RootCrop;
import com.plantfarmlogger.model.subclasses.HerbCrop;
import com.plantfarmlogger.model.CropLog;
import com.plantfarmlogger.view.MainWindow;

import javax.swing.UIManager;

//   public CropLog(String notes, String date, HealthStatus healthStatus,
//             GrowthStatus growthStatus, String action, String cropBed,
//             String farmer, String cropId) {
                
//         this.notes = notes;
//         this.date = date;
//         this.healthStatus = healthStatus;
//         this.growthStatus = growthStatus;
//         this.action = action;
//         this.cropBed = cropBed;
//         this.farmer = farmer;
//         this.cropId = cropId;
//     }
public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger start");
        CropLogDao cd =  CropLogDao.getInstance();
        //  cd.create( (Crop) new  LeafCrop( "Lettuce", "Loamy", "2024-10-01", "2024-09-15", 10.0, 5.0, 15.0));
        //  cd.create((Crop) new RootCrop( "Carrot", "Sandy", "2024-10-05", "2024-09-20", 8.0, 4.0, 12.0));
        //  cd.create((Crop) new HerbCrop("Basil", "Clay", "2024-10-03", "2024-09-18", 6.0, 3.0, 9.0, "2024-10-10", 20, "Eugenol"));
        //ef975e3-d852-47ed-bb39-0691079a0e76 = cropid
        cd.create(new CropLog("First log entry", "2024-09-25", HealthStatus.HEALTHY, GrowthStatus.GERMINATION, "Watered the crop", 
                "Farmer Joe", "ef975e3-d852-47ed-bb39-0691079a0e76"));
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