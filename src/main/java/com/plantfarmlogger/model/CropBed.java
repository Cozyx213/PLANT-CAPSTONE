package com.plantfarmlogger.model;
public class CropBed{
    private Plant plantType;
    private String soilType;

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
}
