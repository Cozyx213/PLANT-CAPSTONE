package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Medicinal;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;
import java.util.Map;

public class HerbCrop extends Crop implements Prunable, Medicinal {
    public static final Map<String, Integer> HERBCROP_BASE_GROWING_DAYS = Map.ofEntries(
            Map.entry("basil", 20),
            Map.entry("mint", 18),
            Map.entry("oregano", 25),
            Map.entry("thyme", 28),
            Map.entry("rosemary", 35),
            Map.entry("lavender", 40),
            Map.entry("sage", 30),
            Map.entry("chamomile", 22),
            Map.entry("lemon balm", 18),
            Map.entry("cilantro", 15),
            Map.entry("dill", 14),
            Map.entry("parsley", 20),
            Map.entry("tarragon", 25)
    );
    public static final int DEFAULT_BASE_GROWING_DAYS = 21;
    public static final Map<String, Integer> HERBCROP_PRUNING_INTERVAL = Map.ofEntries(
            Map.entry("basil", 7),
            Map.entry("mint", 10),
            Map.entry("oregano", 14),
            Map.entry("thyme", 14),
            Map.entry("rosemary", 20),
            Map.entry("lavender", 30),
            Map.entry("sage", 14),
            Map.entry("chamomile", 10),
            Map.entry("lemon balm", 7),
            Map.entry("cilantro", 5),
            Map.entry("dill", 5),
            Map.entry("parsley", 7),
            Map.entry("tarragon", 14)
    );
    public static final int DEFAULT_PRUNING_INTERVAL = 7;
    private String activeCompounds;
    private String pruningDate;
    private int userBaseGrowingDays;

    // used for creation of new HerbCrop
    public HerbCrop(String plantType, String soilType, double width, double height,
            double length, String pruningDate, int userBaseGrowingDays, String activeCompounds, String userId) {
        super(plantType, soilType, width, height, length, userId);
        this.pruningDate = pruningDate;
        this.userBaseGrowingDays = userBaseGrowingDays;
        this.activeCompounds = activeCompounds;
    }

    // used for loading from file; full parameters
    public HerbCrop(String id, String plantType, String soilType,
                    String lastFertilized, String datePlanted,
                    double width, double height, double length,
                    String userId, String pruningDate, int userBaseGrowingDays,
                    String activeCompounds) {
        super(id, plantType, soilType, lastFertilized, datePlanted, width, height, length, userId);
        this.pruningDate = pruningDate;
        this.userBaseGrowingDays = userBaseGrowingDays;
        this.activeCompounds = activeCompounds;
    }


    @Override
    public String getActiveCompounds() {
        return activeCompounds;
    }

    public void setActiveCompounds(String activeCompounds) {
        this.activeCompounds = activeCompounds;
    }

    @Override
    public void setExplicitPruningDate(String date) {
        this.pruningDate = date;
    }

    @Override
    public void setCalculatedPruningDate() {
        LocalDate planted = LocalDate.parse(getDatePlanted());
        LocalDate today = LocalDate.now();

        // Determine base maturity days
        int baseDays = (userBaseGrowingDays != 0)
                ? userBaseGrowingDays
                : HERBCROP_BASE_GROWING_DAYS.getOrDefault(
                getPlantType().toLowerCase(),
                DEFAULT_BASE_GROWING_DAYS
        );

        // Determine pruning interval
        int intervalDays = HERBCROP_PRUNING_INTERVAL.getOrDefault(
                getPlantType().toLowerCase(),
                DEFAULT_PRUNING_INTERVAL
        );

        // Date when the herb becomes mature
        LocalDate maturityDate = planted.plusDays(baseDays);

        LocalDate nextPrune;

        if (pruningDate == null) {
            // Never pruned before
            if (maturityDate.isAfter(today)) {
                // Not mature yet → next prune is maturity day
                nextPrune = maturityDate;
            } else {
                // Mature → first prune happens intervalDays after maturity
                nextPrune = maturityDate.plusDays(intervalDays);

            }
        } else {
            // Already pruned before → count from last prune
            nextPrune = LocalDate.parse(pruningDate).plusDays(intervalDays);
        }
        while (!nextPrune.isAfter(today)) {
            nextPrune = nextPrune.plusDays(intervalDays);
        }
        this.pruningDate = nextPrune.toString();
    }


    public String getPruningDate() {
        return pruningDate;
    }

    public int getUserBaseGrowingDays() {
        return userBaseGrowingDays;
    }

    @Override
    public String toString() {
        return String.join(",",
                getID(),
                "HerbCrop-" + getPlantType(),
                getSoilType(),
                getLastFertilized(),
                getDatePlanted(),
                String.valueOf(getWidth()),
                String.valueOf(getHeight()),
                String.valueOf(getLength()),
                getUserId(),
                getPruningDate(),
                String.valueOf(getUserBaseGrowingDays()),
                getActiveCompounds()
                );
                
    }
}
