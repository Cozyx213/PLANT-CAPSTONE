package com.plantfarmlogger.view.croplog;

import java.util.ArrayList;
import java.util.List;

public class CropLogModel {

    public record CropLogEntry(String date, String age, String health, String growth, String actions) {}

    private String cropName;
    private final List<CropLogEntry> logs;

    public CropLogModel() {
        this.logs = new ArrayList<>();
        this.cropName = "Loading...";
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropName() {
        return cropName;
    }

    public void addLog(CropLogEntry entry) {
        // sort by date ang pag log
        logs.add(0, entry);
    }

    public List<CropLogEntry> getLogs() {
        return logs;
    }
}