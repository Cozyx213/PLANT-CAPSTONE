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
    private static CropDao instance = null;
    ArrayList<Crop> cache = new ArrayList<Crop>();
    private final String cropFile = "src/main/resources/csv/crops.csv";
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
                String combinedType = spl[1];
                String soilType = spl[2];
                String lastFertilized = spl[3];
                String datePlanted = spl[4];

                double width = Double.parseDouble(spl[5]);
                double height = Double.parseDouble(spl[6]);
                double length = Double.parseDouble(spl[7]);
                String userId = spl[8];

                String[] t = combinedType.split("-");
                String subclass = t[0];
                String plantType = t[1];
                Crop n = null;
                if (subclass.equals("HerbCrop")) {
                    String pruningDate = spl.length > 9 && !spl[9].isEmpty() ? spl[9] : null;
                    Integer userBaseGrowingDays = spl.length > 10 && !spl[10].isEmpty()
                                    ? Integer.parseInt(spl[10]) : null;
                    String activeCompounds = spl.length > 11 && !spl[11].isEmpty() ? spl[11] : null;
                    n = new HerbCrop(id, plantType, soilType,
                            lastFertilized, datePlanted, width,
                            height, length, userId,
                            pruningDate, userBaseGrowingDays, activeCompounds);
                } else if (subclass.equals("RootCrop")) {
                    Double userRootCropDensity = spl.length > 9 && !spl[9].isEmpty()? Double.parseDouble(spl[9]) : null;
                    n = new RootCrop(id, plantType, soilType,
                            lastFertilized, datePlanted, width,
                            height, length, userId,
                            userRootCropDensity);
                } else if (subclass.equals("LeafCrop")) {
                    String pruningDate = spl.length > 9 && !spl[9].isEmpty()? spl[9] : null;
                    Integer userBaseGrowingDays = spl.length > 10 ? Integer.parseInt(spl[10]) : null;
                    n = new LeafCrop(id, plantType, soilType,
                            lastFertilized, datePlanted, width,
                            height, length, userId,
                            pruningDate, userBaseGrowingDays);
                }
                cache.add(n);
            }
            System.out.println("[CropDao] Cache loaded successfully!");
        } catch (IOException e) {
            System.out.println("[CropDao] IO_ERROR: file " + cropFile + "does not exist!");
        }
    }


    private void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cropFile))) {
            for (Crop c : cache) {
                bw.write(c.toString());
                bw.newLine();
            }
            System.out.println("[CropDao] Crops saved to file " + cropFile + " successfully!");
        } catch (IOException e) {
            System.out.println("[CropDao] IO_ERROR: Error in opening file " + cropFile);
        }
    }

    // create
    public boolean createCrop(Crop crop) {
        if (crop == null) {
            System.out.println("[CropDao] Create new Crop unsuccessful: Cannot create nonexistent crop");
            return false;
        }
        cache.add(crop);
        save();
        System.out.println("[CropDao] Crop " + crop.getID() + "saved to file.");
        return true;
    }

    // read
    @Override
    public Crop getCrop(String cropId) {
        for (Crop c : cache) {
            if(c.getID().equals(cropId)) {
                System.out.println("[CropDao] Crop " + cropId + " fetched successfully!");
                return c;
            }
        }
        System.out.println("[CropDao] Crop " + cropId + " not found.");
        return null;
    }

    @Override
    public ArrayList<Crop> getAllByUserId(String userId) {
        ArrayList<Crop> cropsOfUser = new ArrayList<>();
        for (Crop c : cache) {
            if (c.getUserId().equals(userId)) {
                cropsOfUser.add(c);
            }
        }
        if (cropsOfUser.isEmpty()) {
            System.out.println("[CropDao] No Crops found for userId " + userId);
        }
        return cropsOfUser;
    }


    // update
    @Override
    public boolean updateCrop(Crop updatedCrop) {
        if (updatedCrop == null) {
            System.out.println("[CropDao] Update Crop unsuccessful: Cannot update nonexistent Crop");
            return false;
        }
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getID().equals(updatedCrop.getID())) {
                cache.set(i, updatedCrop);
                save();
                System.out.println("[CropDao] Crop " + updatedCrop.getID() + " updated successfully!");
                return true;
            }
        }
        System.out.println("[CropDao] Failed to update Crop " + updatedCrop.getID() + " not found.");
        return false;
    }


    // delete
    @Override
    public void deleteCrop(String cropId) {
        // Do not FIXME: Iterating over a copy of the cache to avoid ConcurrentModificationException
        // new ArrayList<>(cache)
        for (Crop c : new ArrayList<>(cache)) {
            if (c.getID().equals(cropId)) {
                cache.remove(c);
                save();
                System.out.println("[CropDao] Deleted Crop " + cropId + "succesfully!");
                return;
            }
        }
        System.out.println("[Cropdao] Failed to delete Crop " + cropId + ".Not found");
    }

    @Override
    public void deleteCropsByUserId(String userId) {
        int ctr = 0;
        // Do not FIXME: Iterating over a copy of the cache to avoid ConcurrentModificationException
        // new ArrayList<>(cache)
        for (Crop c : new ArrayList<>(cache)) {
            if (c.getUserId().equals(userId)) {
                cache.remove(c);
                ctr++;
            }
        }
        if (ctr == 0) {
            System.out.println("No crops with userId: " + userId);
            return;
        }
        System.out.println("Deleted " + ctr + " Crops with userId: " + userId);
    }

    public int getCountByUser(String userId){
        int ctr = 0;
        for(Crop c : cache) {
            if(c.getUserId().equals(userId)) {
                ctr++;
            }
        }
        return ctr;
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
