package com.farm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Farm {
    private String name;
    private String address;
    private List<Farmer> farmers;
    private List<Plant> plants;
    private List<Log> logs;

    public Farm(String name, String address) {
        this.name = name;
        this.address = address;
        this.farmers = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.logs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Farmer> getFarmers() {
        return farmers;
    }

    public void addFarmer(Farmer farmer) {
        this.farmers.add(farmer);
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addPlant(Plant plant) {
        this.plants.add(plant);
    }

    public void createLog(Log log) {
        logs.add(log);
        writeLogToFile(log);
    }

    public List<Log> readLogs() {
        return new ArrayList<>(logs);
    }

    private void writeLogToFile(Log log) {
        try (FileWriter fw = new FileWriter("data/crop_log.csv", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            String cropName = log.getCropBed() != null && log.getCropBed().getCrop() != null 
                ? log.getCropBed().getCrop().getName() : "unknown";
            String farmerName = log.getFarmer() != null ? log.getFarmer().getName() : "unknown";
            
            out.println(String.format("%s,%s,%s,%s,%s,%s",
                log.getDate(),
                farmerName,
                cropName,
                log.getHealthStatus(),
                log.getGrowthStatus(),
                log.getAction()));
        } catch (IOException e) {
            System.err.println("Error writing log to file: " + e.getMessage());
        }
    }

    public void loadLogsFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    // This is a simplified version - in a real app you'd need to match farmers and cropbeds
                    System.out.println("Loaded log: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading logs from file: " + e.getMessage());
        }
    }
}
