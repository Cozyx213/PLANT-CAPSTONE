package com.farm;

public class Crop extends Plant {
    private String name;

    public Crop(String name, String soilType, String lastFertilized) {
        super(soilType, lastFertilized);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
