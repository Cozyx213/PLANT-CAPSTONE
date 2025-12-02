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
    ArrayList<Crop> Crops = new ArrayList<Crop>();

    private final String cropFile = "src/main/resources/csv/cropbeds.csv";

    public CropDao() {
        fetch();
    }

    public ArrayList<Crop> getCropBeds() {
        return Crops;
    }

    private void fetch() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(cropFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                String[] spl = line.split(",");
                if (spl.length < 8)
                    continue;

                String identification = spl[0];
                String plantType = spl[1];
                String soilType = spl[2];
                String lastFertilized = spl[3];
                String datePlanted = spl[4];

                double width = Double.parseDouble(spl[5]);
                double height = Double.parseDouble(spl[6]);
                double length = Double.parseDouble(spl[7]);

                Crop n = new Crop(identification, plantType, soilType, lastFertilized, datePlanted, width, height,
                        length);
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
                bw.write(String.join(",",
                        c.getIdentification(),
                        c.getPlantType(),
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
        System.out.println("OPEnEd " + cropFile);
    }

    public void create(Crop t) {
        if (t == null) {
            System.out.println("no user ");
            return;
        }
        Crops.add(t);
        saveToCSV();

    }

    public void delete(Crop t) {
        if (t != null) {
            Crops.remove(t);
            saveToCSV();
        }
    }

    public void printU() {
        for (Crop c : Crops) {
            System.out.println(c);
        }
    }

}
