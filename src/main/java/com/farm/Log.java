package com.farm;

public class Log {
    private String date;
    private Farmer farmer;
    private CropBed cropBed;
    private String healthStatus;
    private String growthStatus;
    private Action action;

    public Log(String date, Farmer farmer, CropBed cropBed, String healthStatus, String growthStatus, Action action) {
        this.date = date;
        this.farmer = farmer;
        this.cropBed = cropBed;
        this.healthStatus = healthStatus;
        this.growthStatus = growthStatus;
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public CropBed getCropBed() {
        return cropBed;
    }

    public void setCropBed(CropBed cropBed) {
        this.cropBed = cropBed;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getGrowthStatus() {
        return growthStatus;
    }

    public void setGrowthStatus(String growthStatus) {
        this.growthStatus = growthStatus;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Log{" +
                "date='" + date + '\'' +
                ", farmer=" + (farmer != null ? farmer.getName() : "null") +
                ", cropBed=" + (cropBed != null && cropBed.getCrop() != null ? cropBed.getCrop().getName() : "null") +
                ", healthStatus='" + healthStatus + '\'' +
                ", growthStatus='" + growthStatus + '\'' +
                ", action=" + action +
                '}';
    }
}
