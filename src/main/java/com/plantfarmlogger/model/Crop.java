package com.plantfarmlogger.model;

import java.time.LocalDate;
import java.util.UUID;

public class Crop {
    private String identification;
    private String plantType;
    private String soilType;
    private String lastFertilized;
    private String datePlanted;
    private double width;
    private double height;
    private double length;
    private String userId;
    public Crop(String identification, String plantType, String soilType, String lastFertilized,
            String datePlanted, double width, double height,
            double length, String userId) {
        this.identification = identification;
        this.plantType = plantType;
        this.soilType = soilType;
        this.lastFertilized = lastFertilized;
        this.datePlanted = datePlanted;
        this.width = width;
        this.height = height;
        this.length = length;
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Crop(String plantType, String soilType, String lastFertilized,
            String datePlanted, double width, double height,
            double length, String userId) {
        this.identification = UUID.randomUUID().toString();
        this.plantType = plantType;
        this.soilType = soilType;
        this.lastFertilized = lastFertilized;
        this.datePlanted = datePlanted;
        this.width = width;
        this.height = height;
        this.length = length;
        this.userId = userId;
    }

    public Crop(String identification, String plantType, String soilType,
            double width, double height,
            double length, String userId) {
        this(identification, plantType, soilType, LocalDate.now().toString(), LocalDate.now().toString(), width, height,
                length, userId);

    }

    public CropLog getLogs() {
        return null;
    }

    public String getInfo() {
        return null;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getLastFertilized() {
        return lastFertilized;
    }

    public void setLastFertilized(String lastFertilized) {
        this.lastFertilized = lastFertilized;
    }

    public String getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(String datePlanted) {
        this.datePlanted = datePlanted;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
}
