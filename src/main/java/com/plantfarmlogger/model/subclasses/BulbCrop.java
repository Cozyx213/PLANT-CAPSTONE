package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Medicinal;
import com.plantfarmlogger.model.interfaces.Subterranean;

import java.util.ArrayList;

public class BulbCrop extends Crop implements Medicinal, Subterranean {

    public BulbCrop(String plantType, String soilType, String lastFertilized,
                    String datePlanted, double width, double height,
                    double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
    }

    @Override
    public ArrayList<String> getActiveCompounds() {
        return null;
    }

    @Override
    public double estimateMass() {
        return 0;
    }
}
