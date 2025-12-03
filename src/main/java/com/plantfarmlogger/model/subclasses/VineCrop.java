package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Prunable;
import com.plantfarmlogger.model.interfaces.TrellisSupportable;


public class VineCrop extends Crop implements Prunable, TrellisSupportable {

    public VineCrop(String plantType, String soilType, String lastFertilized, String datePlanted, double width, double height, double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
    }



    @Override
    public boolean validateWeight() {
        return false;
    }

    @Override
    public void setExplicitPruningDate(String date) {

    }

    @Override
    public void setCalculatedPruningDate() {

    }
}
