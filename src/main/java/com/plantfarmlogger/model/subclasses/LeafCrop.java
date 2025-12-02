package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Prunable;

public class LeafCrop extends Crop implements Prunable {

    public LeafCrop(String plantType, String soilType, String lastFertilized,
                    String datePlanted, double width, double height,
                    double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
    }

    @Override
    public String schedulePruningDate() {
        return null;
    }
}
