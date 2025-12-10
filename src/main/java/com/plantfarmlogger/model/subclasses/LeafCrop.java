package com.plantfarmlogger.model.subclasses;

import java.time.LocalDate;
import java.util.Map;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Prunable;

public class LeafCrop extends Crop implements Prunable {
    public static final Map<String, Integer> LEAFCROP_BASE_GROWING_DAYS = Map.ofEntries(
            Map.entry("lettuce", 14),
            Map.entry("spinach", 10),
            Map.entry("kale", 18),
            Map.entry("arugula", 12),
            Map.entry("bok choy", 14),
            Map.entry("swiss chard", 15),
            Map.entry("mustard greens", 12),
            Map.entry("collard greens", 18),
            Map.entry("mizuna", 10),
            Map.entry("endive", 14),
            Map.entry("romaine lettuce", 16),
            Map.entry("watercress", 10),
            Map.entry("tatsoi", 12),
            Map.entry("red leaf lettuce", 14));
    public static final int DEFAULT_BASE_GROWING_DAYS = 14;
    private String pruningDate;
    private Integer userBaseGrowingDays;


    public LeafCrop(String plantType, String soilType, double width,
                    double height, double length, String userId,
                    Integer userBaseGrowingDays) {
        super(plantType, soilType, width, height, length, userId);
        this.pruningDate = null;
        this.userBaseGrowingDays = userBaseGrowingDays;
    }

    // used for loading from file; full parameters
    public LeafCrop(String id, String plantType, String soilType, String lastFertilized,
            String datePlanted, double width, double height,
            double length, String userId, String pruningDate, Integer  userBaseGrowingDays) {
        super(id, plantType, soilType, lastFertilized, datePlanted, width, height, length, userId);
        this.pruningDate = pruningDate;
        this.userBaseGrowingDays = userBaseGrowingDays;
    }

  
    @Override
    public void setExplicitPruningDate(String date) {
        this.pruningDate = date;
    }

   
    @Override
    public void setCalculatedPruningDate() {

        int baseDays;
        if (userBaseGrowingDays != null) {
            baseDays = userBaseGrowingDays;
        } else {
            baseDays = LEAFCROP_BASE_GROWING_DAYS.getOrDefault(
                    getPlantType().toLowerCase(),
                    DEFAULT_BASE_GROWING_DAYS);
        }
        LocalDate calculatedPruneDate;
        if (pruningDate == null) {
            calculatedPruneDate = LocalDate.parse(getDatePlanted()).plusDays(baseDays);
        } else {
            LocalDate nextPrune = LocalDate.parse(pruningDate);
            while (!nextPrune.isAfter(LocalDate.now())) {
                nextPrune = nextPrune.plusDays(baseDays);
            }
            calculatedPruneDate = nextPrune;
        }
        this.pruningDate = calculatedPruneDate.toString();
    }

    public Integer getUserBaseGrowingDays() {
        return userBaseGrowingDays;
    }

    public void setUserBaseGrowingDays(Integer userBaseGrowingDays) {
        this.userBaseGrowingDays = userBaseGrowingDays;
    }

    public String getPruningDate() {
        return pruningDate;
    }

    @Override
    public String toString() {
        return String.join(",",
                getID(),
                "LeafCrop-" + getPlantType(),
                getSoilType(),
                getLastFertilized(),
                getDatePlanted(),
                String.valueOf(getWidth()),
                String.valueOf(getHeight()),
                String.valueOf(getLength()),
                getUserId(),
                getPruningDate(),
                String.valueOf(getUserBaseGrowingDays())
        );

    }
}
