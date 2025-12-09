package com.plantfarmlogger.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.plantfarmlogger.enums.Action;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.CropLog;
import com.plantfarmlogger.enums.GrowthStatus;
import com.plantfarmlogger.enums.HealthStatus;

public class CropLogDao {
    ArrayList<CropLog> cache = new ArrayList<CropLog>();
    private static CropLogDao instance = null;
    private final String CropLogFile = "src/main/resources/csv/croplogs.csv";

    public CropLogDao() {
        fetch();
    }
    
    public static CropLogDao getInstance() {
        return instance == null ? instance = new CropLogDao() : instance;
    }
    public ArrayList<CropLog> getCropLogs() {
        return cache;
    }

    private void fetch() {
        try (BufferedReader br = new BufferedReader(new FileReader(CropLogFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",", -1);

                String notes = spl[0];
                String date = spl[1];
                HealthStatus healthStatus = HealthStatus.valueOf(spl[2]);
                 GrowthStatus growthStatus = GrowthStatus.valueOf(spl[3]);
                String actionsJoinedWithPipe = spl[4];
               
                String farmer = spl[5];
                String cropId = spl[6];
                String[] actionsList = actionsJoinedWithPipe.split(";");
                ArrayList<Action> actions = new ArrayList<>();
                for(String action : actionsList){
                    actions.add(Action.valueOf(action));
                }
                CropLog n = new CropLog(notes, date, healthStatus, growthStatus, actions, farmer, cropId);
                cache.add(n);
            }
            System.out.println("[CropLogDao] Cache loaded successfully!");

        } catch (IOException e) {
            System.out.println("IO_ERROR theres no file " + CropLogFile);
        }
    }
    private void saveToCSV() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CropLogFile))) {
            for (CropLog c : cache) {
                ArrayList<Action> actions = c.getActions();
                String[] actionsList = new String[actions.size()];
                for (int i = 0; i < actions.size(); i++) {
                    actionsList[i] = actions.get(i).toString();
                }
                String actionsJoinedWithPipe = String.join(";", actionsList);
              bw.write(c.getNotes() + "," + c.getDate() + "," + c.getHealthStatus().name() + "," + c.getGrowthStatus().name() + "," +
                       actionsJoinedWithPipe + "," + c.getUserId() + "," + c.getCropId());
                bw.newLine();
            }
            System.out.println("[CropLogDao] CropLogs saved to file " + CropLogFile);
        } catch (IOException e) {
            System.out.println("[CropLogDao] IO_ERROR: file " + CropLogFile + " does not exist");
        }
    }

    public boolean createCropLog(CropLog cropLog) {
        if (cropLog == null) {
            System.out.println("no user ");
            return false;
        }
        cache.add(cropLog);
        saveToCSV();
        System.out.println("[CropLogDao] CropLog" + cropLog.getCropId() + "saved to file");
        return true;
    }

    public ArrayList<CropLog> getAllByCropId(String cropId) {
        ArrayList<CropLog> cropsOfUser = new ArrayList<>();
        for (CropLog cropLog : cache) {
            if (cropLog.getCropId().equals(cropId)) {
                cropsOfUser.add(cropLog);
            }
        }
        if (cropsOfUser.isEmpty()) {
            System.out.println("[CropLogDao] No Crops found for cropId " + cropId);
        }
        return cropsOfUser;
    }

    public void delete(CropLog t) {
        int index = 0;
        for (CropLog u : cache) {

            if (t == u) {
                cache.remove(index);
            }

            index++;
        }
        saveToCSV();
    }

    public void printU() {
        for (CropLog c : cache) {
            System.out.println(c);
        }
    }

}
