package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Medicinal;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.util.ArrayList;

public class HerbCrop extends Crop implements Prunable, Medicinal {

    public HerbCrop(String plantType, String soilType, String lastFertilized,
                    String datePlanted, double width, double height,
                    double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
    }

    @Override
    public String schedulePruningDate() {
        return null;
    }

    @Override
    public ArrayList<String> getActiveCompounds() {
        return null;
    }
}
