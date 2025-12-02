package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Subterranean;

import java.util.Map;

public class RootCrop extends Crop implements Subterranean {
    public static final Map<String, Double> ROOTCROP_DENSITIES = Map.ofEntries(
            Map.entry("potato", 750.0),
            Map.entry("carrot", 640.0),
            Map.entry("beetroot", 630.0),
            Map.entry("radish", 950.0),
            Map.entry("turnip", 730.0),
            Map.entry("sweet potato", 900.0),
            Map.entry("cassava", 1000.0),
            Map.entry("ginger", 870.0),
            Map.entry("onion", 600.0),
            Map.entry("garlic", 570.0)
            );
    public static final double DEFAULT_ROOTCROP_DENSITY = 750.0;
    public static final double PACKING_FACTOR = 1.0;
    public RootCrop(String plantType, String soilType, String lastFertilized, String datePlanted, double width, double height, double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
    }
    @Override
    public double estimateMass() {
        double bedVolume = getWidth() * getHeight() * getLength();
        double rootCropDensity = ROOTCROP_DENSITIES.getOrDefault(getPlantType(), DEFAULT_ROOTCROP_DENSITY);
        return bedVolume * rootCropDensity * PACKING_FACTOR;
    }
}
