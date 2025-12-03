package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;
import java.util.Map;


public class NutCrop extends Crop implements Prunable {
    public static final Map<String, Integer> NUTCROP_INITIAL_PRUNE_DAYS = Map.ofEntries(
            Map.entry("almond", 365),
            Map.entry("walnut", 365),
            Map.entry("pecan", 365),
            Map.entry("hazelnut", 365)
    );

    public static final Map<String, Integer> NUTCROP_PRUNE_INTERVAL_DAYS = Map.ofEntries(
            Map.entry("almond", 365),
            Map.entry("walnut", 365),
            Map.entry("pecan", 365),
            Map.entry("hazelnut", 365)
    );

    public static final int DEFAULT_INITIAL_PRUNE_DAYS = 365;
    public static final int DEFAULT_PRUNE_INTERVAL_DAYS = 365;

    private String pruningDate;
    private Integer userInitialPruneDays;
    private Integer userPruneIntervalDays;
    public NutCrop(String plantType, String soilType, String lastFertilized, String datePlanted, double width, double height, double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
    }


    @Override
    public void setExplicitPruningDate(String date) {
        pruningDate = date;
    }

    /**
     * Calculates and sets the next pruning date for this nut crop.
     * <p>
     * Algorithm:
     * <ol>
     *     <li>If the crop has never been pruned, the next pruning date is:
     *         <pre>nextPrune = datePlanted + initialPruneDays</pre></li>
     *     <li>If the crop has already been pruned, the next pruning date is calculated
     *         by repeatedly adding the prune interval to the last pruning date
     *         until the next prune is after the current date ({@link LocalDate#now()}).</li>
     *     <li>Initial and interval days are determined in this order:
     *         <ol type="a">
     *             <li>User-defined days ({@link #userInitialPruneDays}, {@link #userPruneIntervalDays})</li>
     *             <li>Species-specific defaults ({@link #NUTCROP_INITIAL_PRUNE_DAYS}, {@link #NUTCROP_PRUNE_INTERVAL_DAYS})</li>
     *             <li>Fallback defaults ({@link #DEFAULT_INITIAL_PRUNE_DAYS}, {@link #DEFAULT_PRUNE_INTERVAL_DAYS})</li>
     *         </ol>
     *     </li>
     * </ol>
     * The calculated date is stored in {@link #pruningDate} in ISO-8601 format (yyyy-MM-dd).
     */
    @Override
    public void setCalculatedPruningDate() {
        LocalDate planted = LocalDate.parse(getDatePlanted());
        LocalDate now = LocalDate.now();

        int initialDays = (userInitialPruneDays != null) ? userInitialPruneDays :
                NUTCROP_INITIAL_PRUNE_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_INITIAL_PRUNE_DAYS);

        int intervalDays = (userPruneIntervalDays != null) ? userPruneIntervalDays :
                NUTCROP_PRUNE_INTERVAL_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_PRUNE_INTERVAL_DAYS);

        LocalDate nextPrune;
        if (pruningDate == null) {
            nextPrune = planted.plusDays(initialDays);
        } else {
            nextPrune = LocalDate.parse(pruningDate);
            while (!nextPrune.isAfter(now)) {
                nextPrune = nextPrune.plusDays(intervalDays);
            }
        }

        pruningDate = nextPrune.toString();
    }
}
