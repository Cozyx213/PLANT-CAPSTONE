package com.farm;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Farm Management System ===\n");

        // Load farms from CSV
        List<Farm> farms = CSVLoader.loadFarms("data/farm.csv");
        if (farms.isEmpty()) {
            System.out.println("No farms found. Creating default farm...");
            farms.add(new Farm("JuanHydroponics", "Illigan City"));
            CSVLoader.saveFarm(farms.get(0), "data/farm.csv");
        }
        Farm farm = farms.get(0);
        System.out.println("Loaded Farm: " + farm.getName() + " at " + farm.getAddress());

        // Load farmers from CSV
        List<Farmer> farmers = CSVLoader.loadFarmers("data/farmers.csv");
        System.out.println("\nLoaded Farmers:");
        for (Farmer farmer : farmers) {
            farm.addFarmer(farmer);
            System.out.println("  - " + farmer.getName() + ", Age: " + farmer.getAge() + ", Address: " + farmer.getAddress());
        }

        // Load crop beds from CSV
        List<CropBed> cropBeds = CSVLoader.loadCropBeds("data/crop_beds.csv");
        System.out.println("\nLoaded Crop Beds:");
        for (CropBed cb : cropBeds) {
            Crop crop = cb.getCrop();
            Bed bed = cb.getBed();
            farm.addPlant(crop);
            System.out.println("  - " + crop.getName() + 
                             " (Soil: " + crop.getSoilType() + 
                             ", Last Fertilized: " + crop.getLastFertilized() + 
                             ", Dimensions: " + bed.getWidth() + "x" + bed.getLength() + "x" + bed.getHeight() + ")");
        }

        // Create a new log entry
        System.out.println("\n=== Creating New Log Entry ===");
        if (!farmers.isEmpty() && !cropBeds.isEmpty()) {
            Log newLog = new Log(
                "11-20-24",
                farmers.get(0),
                cropBeds.get(0),
                "Healthy",
                "Mature",
                Action.FERTILIZED
            );
            farm.createLog(newLog);
            System.out.println("Created log: " + newLog);
        }

        // Read all logs
        System.out.println("\n=== Reading All Logs ===");
        List<Log> logs = farm.readLogs();
        if (logs.isEmpty()) {
            System.out.println("No logs available in memory.");
        } else {
            for (Log log : logs) {
                System.out.println("  " + log);
            }
        }

        // Load existing logs from file
        System.out.println("\n=== Loading Logs from File ===");
        farm.loadLogsFromFile("data/crop_log.csv");

        System.out.println("\n=== System Ready ===");
    }
}
