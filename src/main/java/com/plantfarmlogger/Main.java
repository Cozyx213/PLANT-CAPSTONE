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
            MainWindow mainWindow = new MainWindow();
            UserController userController = UserController.getInstance();
            User testUser = userController.getUser("64b49616-0515-41af-8080-9fe86d85336c");

            mainWindow.getNavigator().showHome(testUser);

            mainWindow.setVisible(true);
        });
    }
}