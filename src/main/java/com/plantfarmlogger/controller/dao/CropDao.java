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
    ArrayList<Crop> Crops = new ArrayList<Crop>();
    private static CropDao instance = null;
    private final String cropFile = "src/main/resources/csv/cropbeds.csv";

    private CropDao() {
        fetch();
    }
    public static CropDao getInstance() {
        return instance == null ? instance = new CropDao() : instance;
    }

    public ArrayList<Crop> getCropBeds() {
        return Crops;
    }

    private void fetch() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(cropFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",");
                String identification = spl[0];
                String plantType = spl[1];
                String soilType = spl[2];
                String lastFertilized = spl[3];
                String datePlanted = spl[4];

                double width = Double.parseDouble(spl[5]);
                double height = Double.parseDouble(spl[6]);
                double length = Double.parseDouble(spl[7]);

                String[] t = plantType.split("-");
                String type = t[0];
                String crp = t[1];
                Crop n = null;
                if (type.equals("HerbCrop")) {
                    String pruningDate = spl.length > 8 ? spl[8] : "null";
                    int userBaseGrowingDays = spl.length > 9 ? parseIntOrDefault(spl[9], 0) : 0;
                    String activeCompounds = spl.length > 10 ? spl[10] : "null";
                    n = new HerbCrop(identification, crp, soilType, lastFertilized, datePlanted, width, height, length,
                            pruningDate, userBaseGrowingDays, activeCompounds);
                } else if (type.equals("RootCrop")) {
                    n = (Crop) new RootCrop(identification, crp, soilType, lastFertilized, datePlanted, width, height,
                            length);
                } else if (type.equals("LeafCrop")) {
                    String pruningDate = spl.length > 8 ? spl[8] : "null";
                    int userBaseGrowingDays = spl.length > 9 ? parseIntOrDefault(spl[9], 0) : 0;
                    LeafCrop e = new LeafCrop(identification, crp, soilType, lastFertilized, datePlanted, width,
                            height, length, pruningDate);

                    e.setUserBaseGrowingDays(userBaseGrowingDays);
                    n = (Crop) e;
                }
                Crops.add(n);
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR theres no file " + cropFile);
        }
        System.out.println("SUCCESS");
    }

    private void saveToCSV() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cropFile));

        ) {
            for (Crop c : Crops) {

                bw.write(c.toString());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR");
        }
        System.out.println("OPEnEd " + cropFile);
    }

    public void create(Crop t) {
        if (t == null) {
            System.out.println("no user ");
        }
        Crops.add(t);
        saveToCSV();

    }

    public void delete(Crop t) {
        int index = 0;
        for (Crop u : Crops) {

            if (t == u) {
                Crops.remove(index);
            }

            index++;
        }
        saveToCSV();
    }

    public void printU() {
        for (Crop c : Crops) {
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
