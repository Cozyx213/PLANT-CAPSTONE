package com.plantfarmlogger.model;

import com.plantfarmlogger.model.enums.Action;
import com.plantfarmlogger.model.enums.GrowthStatus;
import com.plantfarmlogger.model.enums.HealthStatus;

import java.time.LocalDate;

public class CropLog {
    private String notes;
    private LocalDate date;
    private HealthStatus healthStatus;
    private GrowthStatus growthStatus;
    private Action action;
    private String cropBed;
    private String farmer;

    public CropLog(String notes, LocalDate date, HealthStatus healthStatus,
                   GrowthStatus growthStatus, Action action, String cropBed,
            String farmer) {
        this.notes = notes;
        this.date = date;
        this.healthStatus = healthStatus;
        this.growthStatus = growthStatus;
        this.action = action;
        this.cropBed = cropBed;
        this.farmer = farmer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
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