package com.plantfarmlogger;

import javax.swing.SwingUtilities;

import com.plantfarmlogger.controller.CropController;
import com.plantfarmlogger.controller.CropLogController;
import com.plantfarmlogger.controller.UserController;
import com.plantfarmlogger.controller.dao.CropLogDao;
import com.plantfarmlogger.enums.*;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.model.CropLog;
import com.plantfarmlogger.model.subclasses.HerbCrop;
import com.plantfarmlogger.view.MainWindow;
import com.plantfarmlogger.view.components.BaseDashboardView;

import javax.swing.UIManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger start");

        SwingUtilities.invokeLater(() -> {
            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            UserController userController = UserController.getInstance();
            CropController cropController = CropController.getInstance();
            User testuser = userController.getUser("9ed94660-db75-49f9-87b3-17330e6848ea");
            Crop testCrop = cropController.get("e054d929-2063-4b9a-a03f-80a20a807824");
            MainWindow mainWindow = new MainWindow();


            mainWindow.getNavigator().showHome(testuser);

            mainWindow.setVisible(true);
        });
    }
}