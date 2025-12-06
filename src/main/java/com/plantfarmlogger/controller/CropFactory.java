package com.plantfarmlogger.controller;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.subclasses.HerbCrop;
import com.plantfarmlogger.model.subclasses.LeafCrop;
import com.plantfarmlogger.model.subclasses.RootCrop;

public class CropFactory {

    /**
     * Creates a Crop object based on type and parameters.
     *
     * @param cropType        "Herb", "Leaf", or "Root"
     * @param plantType       species name
     * @param soilType
     * @param width
     * @param height
     * @param length
     * @param userId
     * @param pruningDate     optional for Herb/Leaf
     * @param userBaseDays    optional for Herb/Leaf
     * @param activeCompounds optional for Herb
     * @param userRootDensity optional for Root
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
                        userId,
                        pruningDate,
                        userBaseDays != null ? userBaseDays : HerbCrop.DEFAULT_BASE_GROWING_DAYS,
                        activeCompounds
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

    public static Crop updateCrop(
            Crop oldCrop,
            String cropType,
            String id, String plantType, String soilType,
            String lastFertilized, String datePlanted,
            Double width, Double height, Double length,
            String userId, String pruningDate,
            Integer userBaseDays,
            String activeCompounds,
            Double userRootDensity
    ) {
        if (oldCrop instanceof HerbCrop herb) {
            return new HerbCrop(
                    herb.getID(),
                    plantType != null ? plantType : herb.getPlantType(),
                    soilType != null ? soilType : herb.getSoilType(),
                    herb.getLastFertilized(),
                    herb.getDatePlanted(),
                    width != null ? width : herb.getWidth(),
                    height != null ? height : herb.getHeight(),
                    length != null ? length : herb.getLength(),
                    herb.getUserId(),
                    pruningDate != null ? pruningDate : herb.getPruningDate(),
                    userBaseDays != null ? userBaseDays : herb.getUserBaseGrowingDays(),
                    activeCompounds != null ? activeCompounds : herb.getActiveCompounds()
            );
        } else if (oldCrop instanceof LeafCrop leaf) {
            return new LeafCrop(
                    leaf.getID(),
                    plantType != null ? plantType : leaf.getPlantType(),
                    soilType != null ? soilType : leaf.getSoilType(),
                    leaf.getLastFertilized(),
                    leaf.getDatePlanted(),
                    width != null ? width : leaf.getWidth(),
                    height != null ? height : leaf.getHeight(),
                    length != null ? length : leaf.getLength(),
                    leaf.getUserId(),
                    pruningDate != null ? pruningDate : leaf.getPruningDate(),
                    userBaseDays != null ? userBaseDays : leaf.getUserBaseGrowingDays()
            );
        } else if (oldCrop instanceof RootCrop root) {
            return new RootCrop(
                    root.getID(),
                    plantType != null ? plantType : root.getPlantType(),
                    soilType != null ? soilType : root.getSoilType(),
                    root.getLastFertilized(),
                    root.getDatePlanted(),
                    width != null ? width : root.getWidth(),
                    height != null ? height : root.getHeight(),
                    length != null ? length : root.getLength(),
                    root.getUserId(),
                    userRootDensity != null ? userRootDensity : root.getUserRootCropDensity()
            );
        }

        throw new IllegalArgumentException("Unknown crop type: " + cropType);
    }
}
