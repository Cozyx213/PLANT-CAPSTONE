package com.plantfarmlogger.view.home;

import java.util.ArrayList;
import java.util.List;

public class HomeModel {
    // temporary rani nga data structure
    // backenders will look for this and change accordingly
    // list of generic objects or empty placeholders
    private List<Object> cropBeds;

    public HomeModel() {
        this.cropBeds = new ArrayList<>();
    }

    public void addCropBed(Object cropData) {
        cropBeds.add(cropData);
    }

    public int getCount() {
        return cropBeds.size();
    }

    public List<Object> getCropBeds() {
        return cropBeds;
    }
}