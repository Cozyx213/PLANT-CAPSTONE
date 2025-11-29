package com.plantfarmlogger.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.plantfarmlogger.model.CropBed;
import com.plantfarmlogger.model.interfaces.CropBedDaoInter;

//    ArrayList<CropBed> getCropBeds();
//     void create(CropBed t);
//     void delete(CropBed t);
public class CropBedDao implements CropBedDaoInter {
    ArrayList<CropBed> CropBeds = new ArrayList<CropBed>();

    private final String cropBedFile = "src/main/resources/csv/cropbeds.csv";

    public CropBedDao() {
        fetch();
    }

    public ArrayList<CropBed> getCropBeds() {
        return CropBeds;
    }

    // private String plantType;
    // private String soilType;
    // private String lastFertilized;
    // private String datePlanted;
    // private double width;
    // private double height;
    // private double length;
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

                CropBed n = new CropBed(plantType,soilType, lastFertilized, datePlanted, width, height, length);
                CropBeds.add(n);
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR theres no file "+cropBedFile );
        }
        System.out.println("SUCCESS");
    }
    // private Plant plantType;
    // private String soilType;
    // private String lastFertilized;
    // private String datePlanted;
    // private double width;
    // private double height;
    // private double length;

    private void saveToCSV() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cropBedFile));

        ) {
            for (CropBed c : CropBeds) {
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

    public void create(CropBed t) {
        if (t == null) {
            System.out.println("no user ");
        }
        CropBeds.add(t);
        saveToCSV();

    }

  

    public void delete(CropBed t) {
        int index = 0;
        for (CropBed u : CropBeds) {

            if (t == u) {
                CropBeds.remove(index);
            }

            index++;
        }
        saveToCSV();
    }

    public void printU() {
        for (CropBed c : CropBeds) {
            System.out.println(c);
        }
    }

}
