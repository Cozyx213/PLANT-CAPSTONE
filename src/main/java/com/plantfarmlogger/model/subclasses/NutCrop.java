package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Prunable;


public class NutCrop extends Crop implements Prunable {
    public NutCrop(String plantType, String soilType, String lastFertilized, String datePlanted, double width, double height, double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
    }


    @Override
    public void setExplicitPruningDate(String date) {

    }

    @Override
    public void setCalculatedPruningDate() {

    }
}
