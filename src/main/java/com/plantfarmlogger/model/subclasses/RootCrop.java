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
    /**
     * Estimates the total mass of root biomass contained within the crop bed.
     * <p>
     * The estimation is based on a simple volumetric model:
     *
     * <pre>
     *     estimatedMass = bedVolume × rootCropDensity × PACKING_FACTOR
     * </pre>
     *
     * Where:
     * <ul>
     *     <li><b>bedVolume</b> is the geometric soil volume of the crop bed,
     *         computed as width × height × length.</li>
     *
     *     <li><b>rootCropDensity</b> is the physical density of the harvested
     *         root tissue (e.g., potato, carrot, cassava), retrieved from
     *         {@code ROOTCROP_DENSITIES}. If the plant type is not listed,
     *         {@code DEFAULT_ROOTCROP_DENSITY} is used.</li>
     *
     *     <li><b>PACKING_FACTOR</b> represents the proportion of the soil volume
     *         that is assumed to be occupied by actual root biomass.
     *         A value of 1.0 means the entire soil volume is treated as
     *         filled with root material. Realistic agricultural values
     *         range from 0.3 to 0.7, depending on the crop and planting density.</li>
     * </ul>
     *
     * This method produces an estimate for the <b>entire bed</b>, not an average
     * per plant. No plant count model is incorporated in this calculation.
     *
     * @return estimated total mass of root biomass in the crop bed
     */
    @Override
    public double estimateMass() {
        double bedVolume = getWidth() * getHeight() * getLength();
        double rootCropDensity = ROOTCROP_DENSITIES.getOrDefault(getPlantType(), DEFAULT_ROOTCROP_DENSITY);
        return bedVolume * rootCropDensity * PACKING_FACTOR;
    }
}
