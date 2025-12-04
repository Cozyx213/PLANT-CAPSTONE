package com.plantfarmlogger.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.CropDaoInter;
import com.plantfarmlogger.model.subclasses.HerbCrop;
import com.plantfarmlogger.model.subclasses.LeafCrop;
import com.plantfarmlogger.model.subclasses.RootCrop;

public class CropDao implements CropDaoInter {
    private static CropDao instance;
    ArrayList<Crop> cache = new ArrayList<Crop>();
    private final String cropFile = "src/main/resources/csv/cropbeds.csv";

    private CropDao() {
        loadAll();
    }
    public static CropDao getInstance() {
        return instance == null ? instance = new CropDao() : instance;
    }


    private void loadAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(cropFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",", -1);
                String id = spl[0];
                String userId = spl[1];
                String plantType = spl[2];
                String soilType = spl[3];
                String lastFertilized = spl[4];
                String datePlanted = spl[5];

                double width = Double.parseDouble(spl[6]);
                double height = Double.parseDouble(spl[7]);
                double length = Double.parseDouble(spl[8]);

                String[] t = plantType.split("-");
                String type = t[0];
                String cropName = t[1];
                Crop n = null;
                if (type.equals("HerbCrop")) {
                    String pruningDate = spl.length > 9 && !spl[9].isEmpty() ? spl[9] : null;

                    int userBaseGrowingDays = spl.length > 10 && !spl[10].isEmpty()
                                    ? parseIntOrDefault(spl[10], 0) : 0;
                    String activeCompounds = spl.length > 11 && !spl[11].isEmpty() ? spl[11] : null;

                    n = new HerbCrop(id, cropName, soilType,
                            lastFertilized, datePlanted, width,
                            height, length, userId,
                            pruningDate, userBaseGrowingDays, activeCompounds);
                } else if (type.equals("RootCrop")) {
                    double userRootCropDensity = spl.length > 9 && !spl[9].isEmpty()? Double.parseDouble(spl[9]) : 0;
                    n = new RootCrop(id, cropName, soilType,
                            lastFertilized, datePlanted, width,
                            height, length, userId,
                            userRootCropDensity);
                } else if (type.equals("LeafCrop")) {
                    String pruningDate = spl.length > 9 && !spl[9].isEmpty()? spl[9] : null;
                    int userBaseGrowingDays = spl.length > 10 ? parseIntOrDefault(spl[10], 0) : 0;
                    n = new LeafCrop(id, cropName, soilType,
                            lastFertilized, datePlanted, width,
                            height, length, pruningDate,
                            userId, userBaseGrowingDays);
                }
                cache.add(n);
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR: file " + cropFile + "does not exist");
        }
        System.out.println("SUCCESS");
    }


    private void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cropFile))) {
            for (Crop c : cache) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("IO_ERROR");
        }
        System.out.println("Opened " + cropFile);
    }

    // create
    public boolean create(Crop crop) {
        if (crop == null) {
            System.out.println("No crop found");
            return false;
        }
        cache.add(crop);
        save();
        return true;
    }

    // read
    @Override
    public ArrayList<Crop> findAll() {
        return cache;
    }

    @Override
    public Crop findByCropId(String cropId) {
        for (Crop c : cache) {
            if(c.getID().equals(cropId)) {return c;}
        }
        throw new IllegalArgumentException("Crop with id " + cropId + " not found");
    }

    @Override
    public ArrayList<Crop> findAllByUserId(String userId) {
        ArrayList<Crop> cropsOfUser = new ArrayList<>();
        for (Crop c : cache) {
            if (c.getUserId().equals(userId)) {
                cropsOfUser.add(c);
            }
        }
        return cropsOfUser;
    }


    // update
        // TODO: implement updateMethods

    // delete
    @Override
    public void deleteAll() {
        int ctr = 0;
        for(Crop c : cache) {
            deleteByCropId(c.getID());
        }
        System.out.println("Deleted all crops");
    }
    @Override
    public void deleteByCropId(String cropId) {
        for (Crop c : cache) {
            if (c.getID().equals(cropId)) {
                cache.remove(c);
                save();
                System.out.println("Deleted Crop with id " + cropId);
                return;
            }
        }
        System.out.println("Failed to delete Crop with " + cropId + ". Not found");
    }

    @Override
    public void deleteAllByUserId(String userId) {
        int ctr = 0;
        for (Crop c : cache) {
            if (c.getID().equals(userId)) {
                deleteByCropId(c.getID());
                ctr++;
            }
        }
        if(ctr == 0) {
            System.out.println("No crops with userId: " + userId );
            return;
        }
        System.out.println("Deleted " + ctr + " Crops with userId: " + userId);
    }

    public void printU() {
        for (Crop c : cache) {
            if (c == null) {
                System.out.println("null crop");
            } else {
                System.out.println(c.toString());

            }
        }
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        if (value == null || value.trim().equalsIgnoreCase("null") || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
