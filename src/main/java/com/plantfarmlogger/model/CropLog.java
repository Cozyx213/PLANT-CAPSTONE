package com.plantfarmlogger.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.plantfarmlogger.enums.Action;
import com.plantfarmlogger.enums.GrowthStatus;
import com.plantfarmlogger.enums.HealthStatus;

public class CropLog {
    private String id;
    private String notes;
    private String date;
    private HealthStatus healthStatus;
    private GrowthStatus growthStatus;
    private ArrayList<Action> actions;
    
    private String userId;
    private String cropId;

    // used for new creation
    public CropLog(String notes, HealthStatus healthStatus,
                   GrowthStatus growthStatus, ArrayList<Action> actions,
                   String userId, String cropId) {
        this.id = UUID.randomUUID().toString();
        this.notes = notes;
        this.date = LocalDate.now().toString();
        this.healthStatus = healthStatus;
        this.growthStatus = growthStatus;
        this.actions = actions;
        this.userId = userId;
        this.cropId = cropId;
    }

    // used for loading from file; full parameters
    public CropLog(String id, String notes, String date, HealthStatus healthStatus,
            GrowthStatus growthStatus, ArrayList<Action> actions,
            String userId, String cropId) {
        this.id = id;
        this.notes = notes;
        this.date = date;
        this.healthStatus = healthStatus;
        this.growthStatus = growthStatus;
        this.actions = actions;
        this.userId = userId;
        this.cropId = cropId;
    }
    public String getCropId() {
        return cropId;
    }
    public void setCropId(String cropId) {
        this.cropId = cropId;
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

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String[] actionsArray = new String[this.actions.size()];
        for (int i = 0; i < this.actions.size(); i++) {
            actionsArray[i] = this.actions.get(i).toString();
        }
        String actionsJoinedWithSemicolon = String.join(";", actionsArray);
        return String.join(",",
                getId(),
                getNotes(),
                getDate(),
                getHealthStatus().name(),
                getGrowthStatus().name(),
                actionsJoinedWithSemicolon,
                getUserId(),
                getCropId());
    }
}