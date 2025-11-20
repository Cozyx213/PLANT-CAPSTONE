package com.farm;

public abstract class Plant {
    private String soilType;
    private String lastFertilized;

    public Plant(String soilType, String lastFertilized) {
        this.soilType = soilType;
        this.lastFertilized = lastFertilized;
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
}
