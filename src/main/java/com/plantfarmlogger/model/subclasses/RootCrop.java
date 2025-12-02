package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Plant;
import com.plantfarmlogger.model.interfaces.Subterranean;

import java.util.Map;

public class RootCrop extends Plant implements Subterranean {
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
    public RootCrop(String name) {
        super(name);
    }

    @Override
    public double estimateMass() {

        return 0;
    }
}
