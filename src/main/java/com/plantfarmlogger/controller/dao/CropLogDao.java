package com.plantfarmlogger.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.plantfarmlogger.enums.Action;
import com.plantfarmlogger.model.CropLog;
import com.plantfarmlogger.enums.GrowthStatus;
import com.plantfarmlogger.enums.HealthStatus;

public class CropLogDao {
    private static CropLogDao instance = null;
    ArrayList<CropLog> cache = new ArrayList<CropLog>();
    private final String cropLogFile = "src/main/resources/csv/croplogs.csv";
    public CropLogDao() {
        loadAll();
    }
    public static CropLogDao getInstance() {
        return instance == null ? instance = new CropLogDao() : instance;
    }


    private void loadAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(cropLogFile));) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",", -1);
                String id = spl[0];
                String notes = spl[1];
                String date = spl[2];
                HealthStatus healthStatus = HealthStatus.valueOf(spl[3]);
                 GrowthStatus growthStatus = GrowthStatus.valueOf(spl[4]);
                String actionsJoinedWithSemicolon = spl[5];
               
                String farmer = spl[6];
                String cropId = spl[7];
                String[] actionsArray = actionsJoinedWithSemicolon.split(";");
                ArrayList<Action> actionsList = new ArrayList<>();
                for(String action : actionsArray){
                    if (action == null || action.isBlank()) continue; // skip empty entries
                    actionsList.add(Action.valueOf(action));
                }
                CropLog n = new CropLog(id, notes, date, healthStatus, growthStatus, actionsList, farmer, cropId);
                cache.add(n);
            }
            System.out.println("[CropLogDao] Cache loaded successfully!");
        } catch (IOException e) {
            System.out.println("[CropLogDao] IO_ERROR: file " + cropLogFile + "does not exist!");
        }
    }

    private void save() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cropLogFile))) {
            for (CropLog c : cache) {
                bw.write(c.toString());
                bw.newLine();
            }
            System.out.println("[CropLogDao] CropLogs saved to file " + cropLogFile + " successfully!");
        } catch (IOException e) {
            System.out.println("[CropLogDao] IO_ERROR: Error in opening file " + cropLogFile);
        }
    }

    // create
    public boolean createCropLog(CropLog cropLog) {
        if (cropLog == null) {
            System.out.println("[CropLogDao] Create new CropLog unsuccessful: Cannot create nonexistent crop log");
            return false;
        }
        cache.add(cropLog);
        save();
        System.out.println("[CropLogDao] CropLog " + cropLog.getDate() + "saved to file.");
        return true;
    }

    // read
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

    public ArrayList<CropLog> getAllByUserId(String userId) {
        ArrayList<CropLog> cropsOfUser = new ArrayList<>();
        for (CropLog cropLog : cache) {
            if (cropLog.getUserId().equals(userId)) {
                cropsOfUser.add(cropLog);
            }
        }
        if (cropsOfUser.isEmpty()) {
            System.out.println("[CropLogDao] No Crops found for userId " + userId);
        }
        return cropsOfUser;
    }



    public void deleteCropLog(String cropLogId) {
        // Do not FIXME: Iterating over a copy of the cache to avoid ConcurrentModificationException
        // new ArrayList<>(cache)
        for (CropLog c : new ArrayList<>(cache)) {
            if (c.getId().equals(cropLogId)) {
                cache.remove(c);
                save();
                System.out.println("[CropLogDao] Deleted CropLog " + cropLogId + "succesfully!");
                return;
            }
        }
        System.out.println("[CropLogDao] Failed to delete Crop Log " + cropLogId + ".Not found");
        save();
    }

    public void deleteCropsByCropId(String cropId) {
        int ctr = 0;
        // Do not FIXME: Iterating over a copy of the cache to avoid ConcurrentModificationException
        // new ArrayList<>(cache)
        for (CropLog c : new ArrayList<>(cache)) {
            if (c.getCropId().equals(cropId)) {
                cache.remove(c);
                ctr++;
            }
        }
        if (ctr == 0) {
            System.out.println("No crop logs with cropId: " + cropId);
            return;
        }
        System.out.println("Deleted " + ctr + " Crop Logs with cropId: " + cropId);
    }

    public void deleteCropsByUserId(String userId) {
        int ctr = 0;
        // Do not FIXME: Iterating over a copy of the cache to avoid ConcurrentModificationException
        // new ArrayList<>(cache)
        for (CropLog c : new ArrayList<>(cache)) {
            if (c.getUserId().equals(userId)) {
                cache.remove(c);
                ctr++;
            }
        }
        if (ctr == 0) {
            System.out.println("No crop logs with userId: " + userId);
            return;
        }
        System.out.println("Deleted " + ctr + " Crop Logs with userId: " + userId);
    }


    public void printU() {
        for (CropLog c : cache) {
            System.out.println(c);
        }
    }

}
