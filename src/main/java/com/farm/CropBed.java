package com.farm;

public class CropBed {
    private Crop crop;
    private Bed bed;
    private String farmName;

    public CropBed(Crop crop, Bed bed, String farmName) {
        this.crop = crop;
        this.bed = bed;
        this.farmName = farmName;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
}
