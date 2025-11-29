package com.plantfarmlogger.model;

import com.plantfarmlogger.model.enums.Action;

import java.time.LocalDate;

public class CropLog {
    private String notes;
    private LocalDate date;
    private String healthStatus;
    private String growthStatus;
    private Action action;
    private String cropBed;
    private String farmer;

    public CropLog(String notes, LocalDate date, String healthStatus,
                   String growthStatus, Action action, String cropBed,
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