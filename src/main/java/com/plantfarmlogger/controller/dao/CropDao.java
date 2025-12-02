package com.plantfarmlogger.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.CropDaoInter;

public class CropDao implements CropDaoInter {
    ArrayList<Crop> CropBeds = new ArrayList<Crop>();

    private final String cropBedFile = "src/main/resources/csv/cropbeds.csv";

    public CropDao() {
        fetch();
    }

    public ArrayList<Crop> getCropBeds() {
        return CropBeds;
    }

    private void fetch() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(cropBedFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",");
                String plantType = spl[0];
                String soilType = spl[1];
                String lastFertilized = spl[2];
                String datePlanted = spl[3];

                double width = Double.parseDouble(spl[4]);
                double height = Double.parseDouble(spl[5]);
                double length = Double.parseDouble(spl[6]);

                Crop n = new Crop(plantType, soilType, lastFertilized, datePlanted, width, height, length);
                CropBeds.add(n);
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR theres no file " + cropBedFile);
        }
        System.out.println("SUCCESS");
    }

    private void saveToCSV() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cropBedFile));

        ) {
            for (Crop c : CropBeds) {
                bw.write(String.join(",", c.getPlantType(),
                        c.getSoilType(),
                        c.getLastFertilized(),
                        c.getDatePlanted(),
                        String.valueOf(c.getWidth()),
                        String.valueOf(c.getHeight()),
                        String.valueOf(c.getLength())));
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR");
        }
        System.out.println("OPEnEd " + cropBedFile);
    }

    public void create(Crop t) {
        if (t == null) {
            System.out.println("no user ");
        }
        CropBeds.add(t);
        saveToCSV();

    }

    public void delete(Crop t) {
        int index = 0;
        for (Crop u : CropBeds) {

            if (t == u) {
                CropBeds.remove(index);
            }

            index++;
        }
        saveToCSV();
    }

    public void printU() {
        for (Crop c : CropBeds) {
            System.out.println(c);
        }
    }

}
