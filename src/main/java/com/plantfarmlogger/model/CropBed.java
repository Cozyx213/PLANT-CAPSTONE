package com.plantfarmlogger.model;

public class CropBed{
    private Plant plantType;
    private String soilType;
    private String lastFertilized;
    private double width;
    private double height;
    private double length;
    private String datePlanted;
    
    public CropBed(Plant plantType, String soilType, String lastFertilized,
                   double width, double height, double length,
                   String datePlanted){
        this.plantType = plantType;
        this.soilType = soilType;
        this.lastFertilized = lastFertilized;
        this.width = width;
        this.height = height;
        this.length = length;
    }
    public void addLog(){}
    public void editLog(){}
    public CropLog getLog(){return null;}
    public void getInfo(){}

    public Plant getPlantType() {
        return plantType;
    }

    public void setPlantType(Plant plantType) {
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

    public String getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(String datePlanted) {
        this.datePlanted = datePlanted;
    }
}
