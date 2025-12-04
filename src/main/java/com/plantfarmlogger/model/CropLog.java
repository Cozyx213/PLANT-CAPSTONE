package com.plantfarmlogger.model;

import java.time.LocalDate;

import com.plantfarmlogger.model.enums.GrowthStatus;
import com.plantfarmlogger.model.enums.HealthStatus;

public class CropLog {
    private String notes;
    private String date;
    private HealthStatus healthStatus;
    private GrowthStatus growthStatus;
    private String action;
    private String cropBed;
    private String farmer;

    public CropLog(String notes, String date, HealthStatus healthStatus,
            GrowthStatus growthStatus, String action, String cropBed,
            String farmer) {
        this.notes = notes;
        this.date = date;
        this.healthStatus = healthStatus;
        this.growthStatus = growthStatus;
        this.action = action;
        this.cropBed = cropBed;
        this.farmer = farmer;
    }

    public CropLog(String notes, HealthStatus healthStatus,
            GrowthStatus growthStatus, String action, String cropBed,
            String farmer) {
        this(notes, LocalDate.now().toString(), healthStatus, growthStatus, action, cropBed, farmer);

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public GrowthStatus getGrowthStatus() {
        return growthStatus;
    }

    public void setGrowthStatus(GrowthStatus growthStatus) {
        this.growthStatus = growthStatus;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCropBed() {
        return cropBed;
    }

    public void setCropBed(String cropBed) {
        this.cropBed = cropBed;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }
}