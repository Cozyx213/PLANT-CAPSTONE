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
    private Integer userBaseGrowingDays;

    // used for creation of new HerbCrop
    public HerbCrop(String plantType, String soilType, double width, double height,
            double length,  String userId, Integer userBaseGrowingDays, String activeCompounds) {
        super(plantType, soilType, width, height, length, userId);
        this.pruningDate = null;
        this.userBaseGrowingDays = userBaseGrowingDays;
        this.activeCompounds = activeCompounds;
    }

    // used for loading from file; full parameters
    public HerbCrop(String id, String plantType, String soilType,
                    String lastFertilized, String datePlanted,
                    double width, double height, double length,
                    String userId, String pruningDate, Integer userBaseGrowingDays,
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

    /**
     * Calculates and sets the next pruning date for this herb crop based on herb-specific
     * growth and harvest behavior.
     * <p>
     * Herb crops follow a different pruning cycle compared to leaf crops:
     *
     * <ul>
     *     <li><b>First pruning:</b> Occurs when the herb reaches approximately
     *     half of its base maturity period. Culinary herbs (basil, mint, thyme, etc.)
     *     are typically pruned early to encourage branching.</li>
     *
     *     <li><b>Subsequent prunings:</b> After the first prune, herbs regrow at a
     *     consistent rate defined by a species-specific pruning interval.
     *     Their full maturity date is no longer relevant.</li>
     *
     *     <li><b>No backdated pruning:</b> If the first calculated prune date or a
     *     subsequent interval prune falls in the past, the method repeatedly adds
     *     {@code intervalDays} until a future date is reached.</li>
     * </ul>
     *
     * <p>
     * Behavior depends on whether the crop has been pruned before:
     * </p>
     *
     * <h3>Case 1: {@code pruningDate == null}</h3>
     * <ul>
     *     <li>The first possible pruning date is: {@code planted + baseDays / 2}.</li>
     *     <li>If this date is still in the future, it becomes the next pruning date.</li>
     *     <li>If this date is in the past, the method begins adding {@code intervalDays}
     *     until the resulting date is in the future.</li>
     * </ul>
     *
     * <h3>Case 2: {@code pruningDate != null}</h3>
     * <ul>
     *     <li>The next pruning date is calculated as:
     *         {@code LocalDate.parse(pruningDate) + intervalDays}.</li>
     *     <li>If this date is already in the past, the method continues adding
     *         {@code intervalDays} until a future date is found.</li>
     * </ul>
     *
     * <p>
     * Finally, the resulting date is always guaranteed to be strictly ahead of today.
     * The computed date is stored back into {@code this.pruningDate} as an ISO-8601 string.
     * </p>
     */
    @Override
    public void setCalculatedPruningDate() {
        LocalDate planted = LocalDate.parse(getDatePlanted());
        LocalDate today = LocalDate.now();

        // Base maturity for the herb species
        int baseDays = (userBaseGrowingDays != null)
                ? userBaseGrowingDays
                : HERBCROP_BASE_GROWING_DAYS.getOrDefault(
                getPlantType().toLowerCase(),
                DEFAULT_BASE_GROWING_DAYS
        );

        // Herb-specific pruning interval
        int intervalDays = HERBCROP_PRUNING_INTERVAL.getOrDefault(
                getPlantType().toLowerCase(),
                DEFAULT_PRUNING_INTERVAL
        );

        LocalDate nextPrune;

        if (pruningDate == null) {
            // First pruning uses HALF the base maturity days (herb-specific rule)
            LocalDate firstPossiblePrune = planted.plusDays(baseDays / 2);

            if (firstPossiblePrune.isAfter(today)) {
                // Plant has not reached first prune threshold
                nextPrune = firstPossiblePrune;
            } else {
                // Past first prune window → begin counting intervals
                nextPrune = firstPossiblePrune.plusDays(intervalDays);
            }
        } else {
            // Herb regrows at interval rate regardless of maturity
            nextPrune = LocalDate.parse(pruningDate).plusDays(intervalDays);
        }

        // Ensure next prune is always in the future
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
