package com.plantfarmlogger.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.plantfarmlogger.model.CropLog;
import com.plantfarmlogger.model.enums.GrowthStatus;
import com.plantfarmlogger.model.enums.HealthStatus;

//     private String notes;
//     private String date;
//     private HealthStatus healthStatus;
//     private GrowthStatus growthStatus;
//     private String action;
//     private String cropBed;
//     private String farmer;
//     private String cropId;
public class CropLogDao {
    ArrayList<CropLog> CropLogs = new ArrayList<CropLog>();
    private static CropLogDao instance = null;
    private final String CropLogFile = "src/main/resources/csv/croplogs.csv";

    public CropLogDao() {
        fetch();
    }
    
    public static CropLogDao getInstance() {
        return instance == null ? instance = new CropLogDao() : instance;
    }
    public ArrayList<CropLog> getCropLogs() {
        return CropLogs;
    }

    private void fetch() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(CropLogFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",");

                String notes = spl[0];
                String date = spl[1];
                HealthStatus healthStatus = HealthStatus.valueOf(spl[2]);
                 GrowthStatus growthStatus = GrowthStatus.valueOf(spl[3]);
                String action = spl[4];
               
                String farmer = spl[5];
                String cropId = spl[6];
                CropLog n = new CropLog(notes, date, healthStatus, growthStatus, action, farmer, cropId);
                CropLogs.add(n);
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR theres no file " + CropLogFile);
        }
        System.out.println("SUCCESS");
    }
    private void saveToCSV() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CropLogFile));

        ) {
            for (CropLog c : CropLogs) {
              bw.write(c.getNotes() + "," + c.getDate() + "," + c.getHealthStatus().name() + "," + c.getGrowthStatus().name() + "," +
                      c.getAction() + "," + c.getFarmer() + "," + c.getCropId());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR");
        }
        System.out.println("OPEnEd " + CropLogFile);
    }

    public void create(CropLog t) {
        if (t == null) {
            System.out.println("no user ");
        }
        CropLogs.add(t);
        saveToCSV();

    }

    public void delete(CropLog t) {
        int index = 0;
        for (CropLog u : CropLogs) {

            if (t == u) {
                CropLogs.remove(index);
            }

            index++;
        }
        saveToCSV();
    }

    public void printU() {
        for (CropLog c : CropLogs) {
            System.out.println(c);
        }
    }

}
