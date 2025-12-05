package com.plantfarmlogger.controller;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.subclasses.HerbCrop;
import com.plantfarmlogger.model.subclasses.LeafCrop;
import com.plantfarmlogger.model.subclasses.RootCrop;

public class CropFactory {

    /**
     * Creates a Crop object based on type and parameters.
     *
     * @param cropType         "Herb", "Leaf", or "Root"
     * @param plantType        species name
     * @param soilType
     * @param width
     * @param height
     * @param length
     * @param userId
     * @param pruningDate      optional for Herb/Leaf
     * @param userBaseDays     optional for Herb/Leaf
     * @param activeCompounds  optional for Herb
     * @param userRootDensity  optional for Root
     * @return Crop object of the correct subclass
     */
    public static Crop createCrop(
            String cropType,
            String plantType,
            String soilType,
            double width,
            double height,
            double length,
            String userId,
            String pruningDate,
            Integer userBaseDays,
            String activeCompounds,
            Double userRootDensity
    ) {
        switch (cropType) {
            case "HerbCrop":
                return new HerbCrop(
                        plantType,
                        soilType,
                        width, height, length,
                        pruningDate,
                        userBaseDays != null ? userBaseDays : HerbCrop.DEFAULT_BASE_GROWING_DAYS,
                        activeCompounds,
                        userId
                );

            case "LeafCrop":
                return new LeafCrop(
                        plantType,
                        soilType,
                        width, height, length,
                        userId,
                        pruningDate,
                        userBaseDays != null ? userBaseDays : LeafCrop.DEFAULT_BASE_GROWING_DAYS
                );

            case "RootCrop":
                return new RootCrop(
                        plantType,
                        soilType,
                        width, height, length,
                        userId,
                        userRootDensity != null ? userRootDensity : RootCrop.DEFAULT_ROOTCROP_DENSITY
                );

            default:
                throw new IllegalArgumentException("Unknown crop type: " + cropType);
        }
    }
}
