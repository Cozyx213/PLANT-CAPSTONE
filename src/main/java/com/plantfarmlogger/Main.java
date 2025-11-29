package com.plantfarmlogger;

import java.time.LocalDate;


import javax.swing.SwingUtilities;

import com.plantfarmlogger.controller.dao.CropBedDao;
import com.plantfarmlogger.model.CropBed;
import com.plantfarmlogger.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        System.out.println("Farm Logger start");
        // show Swing UI on EDT\
        CropBedDao cbd = new CropBedDao();
        LocalDate ld = LocalDate.now();
        cbd.create(new CropBed("type1" ,"type2" , "typ3" , ld.toString() , 12.12 ,32.4 , 33.3 ));
        SwingUtilities.invokeLater(() -> {
            MainWindow w = new MainWindow();
            w.setVisible(true);
        });

    }
}