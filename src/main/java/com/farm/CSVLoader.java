package com.farm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static List<Farm> loadFarms(String filename) {
        List<Farm> farms = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return farms;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    farms.add(new Farm(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading farms: " + e.getMessage());
        }
        return farms;
    }

    public static List<Farmer> loadFarmers(String filename) {
        List<Farmer> farmers = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return farmers;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    String address = parts[2].trim();
                    String farmName = parts[3].trim();
                    farmers.add(new Farmer(name, age, address, farmName));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading farmers: " + e.getMessage());
        }
        return farmers;
    }

    public static List<CropBed> loadCropBeds(String filename) {
        List<CropBed> cropBeds = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return cropBeds;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String cropName = parts[0].trim();
                    String soilType = parts[1].trim();
                    String lastFertilized = parts[2].trim();
                    double width = Double.parseDouble(parts[3].trim());
                    double length = Double.parseDouble(parts[4].trim());
                    double height = Double.parseDouble(parts[5].trim());
                    String farmName = parts[6].trim();

                    Crop crop = new Crop(cropName, soilType, lastFertilized);
                    Bed bed = new Bed(width, length, height);
                    cropBeds.add(new CropBed(crop, bed, farmName));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading crop beds: " + e.getMessage());
        }
        return cropBeds;
    }

    public static void saveFarm(Farm farm, String filename) {
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(farm.getName() + "," + farm.getAddress());
        } catch (IOException e) {
            System.err.println("Error saving farm: " + e.getMessage());
        }
    }

    public static void saveFarmers(List<Farmer> farmers, String filename) {
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Farmer farmer : farmers) {
                out.println(String.format("%s,%d,%s,%s",
                    farmer.getName(),
                    farmer.getAge(),
                    farmer.getAddress(),
                    farmer.getFarmName()));
            }
        } catch (IOException e) {
            System.err.println("Error saving farmers: " + e.getMessage());
        }
    }

    public static void saveCropBeds(List<CropBed> cropBeds, String filename) {
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (CropBed cb : cropBeds) {
                Crop crop = cb.getCrop();
                Bed bed = cb.getBed();
                out.println(String.format("%s,%s,%s,%.1f,%.1f,%.1f,%s",
                    crop.getName(),
                    crop.getSoilType(),
                    crop.getLastFertilized(),
                    bed.getWidth(),
                    bed.getLength(),
                    bed.getHeight(),
                    cb.getFarmName()));
            }
        } catch (IOException e) {
            System.err.println("Error saving crop beds: " + e.getMessage());
        }
    }
}
