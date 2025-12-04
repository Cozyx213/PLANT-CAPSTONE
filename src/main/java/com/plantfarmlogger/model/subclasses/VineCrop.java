package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Prunable;
import com.plantfarmlogger.model.interfaces.TrellisSupportable;

import java.time.LocalDate;
import java.util.Map;


public class VineCrop extends Crop implements Prunable, TrellisSupportable {
    public static final Map<String, Double> VINECROP_MAX_TRELLIS_CAPACITY = Map.ofEntries(
            Map.entry("tomato", 10.0),     // kg
            Map.entry("cucumber", 8.0),
            Map.entry("grape", 15.0),
            Map.entry("pumpkin", 20.0)
    );
    public static final Map<String, Integer> VINECROP_INITIAL_PRUNE_DAYS = Map.ofEntries(
            Map.entry("tomato", 14),
            Map.entry("cucumber", 10),
            Map.entry("grape", 21),
            Map.entry("pumpkin", 12)
    );

    public static final Map<String, Integer> VINECROP_PRUNE_INTERVAL_DAYS = Map.ofEntries(
            Map.entry("tomato", 7),
            Map.entry("cucumber", 5),
            Map.entry("grape", 14),
            Map.entry("pumpkin", 7)
    );

    public static final int DEFAULT_INITIAL_PRUNE_DAYS = 14;
    public static final int DEFAULT_PRUNE_INTERVAL_DAYS = 7;
    public static final double DEFAULT_MAX_TRELLIS_CAPACITY = 10; // kg

    private String pruningDate;
    private Integer userInitialPruneDays;
    private Integer userPruneIntervalDays;
    private double userMaxTrellisCapacity;
    public VineCrop(String plantType, String soilType, String lastFertilized, String datePlanted, double width, double height, double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
        pruningDate = "";
        userInitialPruneDays = 0;
        userPruneIntervalDays = 0;
        userMaxTrellisCapacity = 0;
    }


    /**
     * Determines whether the given estimated weight of this vine crop is within
     * the maximum supported capacity of the trellis.
     * <p>
     * The maximum supported weight is determined in the following order:
     * <ol>
     *     <li>If a user-defined maximum capacity has been set via
     *         {@link #setUserMaxTrellisCapacity(double)}, that value is used.</li>
     *     <li>Otherwise, a species-specific default is retrieved from
     *         {@link #VINECROP_MAX_TRELLIS_CAPACITY}.</li>
     *     <li>If the plant type is not listed, a general default
     *         ({@link #DEFAULT_MAX_TRELLIS_CAPACITY}) is used.</li>
     * </ol>
     *
     * @param estimatedWeight the estimated weight of the vine crop in kilograms
     * @return {@code true} if the weight is within the supported capacity,
     *         {@code false} if the vine is too heavy
     */
    @Override
    public boolean validateWeight(double estimatedWeight) {
        double maxCapacity;
        if (userMaxTrellisCapacity > 0) {
            maxCapacity = userMaxTrellisCapacity;
        } else {
            maxCapacity = VINECROP_MAX_TRELLIS_CAPACITY.getOrDefault(
                    getPlantType().toLowerCase(),
                    DEFAULT_MAX_TRELLIS_CAPACITY
            );
        }

        return estimatedWeight <= maxCapacity;
    }
    @Override
    public void setExplicitPruningDate(String date) {
        pruningDate = date;
    }

    /**
     * Calculates and sets the next pruning date for this vine crop.
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
     *             <li>Species-specific defaults ({@link #VINECROP_INITIAL_PRUNE_DAYS}, {@link #VINECROP_PRUNE_INTERVAL_DAYS})</li>
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
                VINECROP_INITIAL_PRUNE_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_INITIAL_PRUNE_DAYS);

        int intervalDays = (userPruneIntervalDays != null) ? userPruneIntervalDays :
                VINECROP_PRUNE_INTERVAL_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_PRUNE_INTERVAL_DAYS);

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

    public String getPruningDate() {
        return pruningDate;
    }

    public void setPruningDate(String pruningDate) {
        this.pruningDate = pruningDate;
    }

    public Integer getUserInitialPruneDays() {
        return userInitialPruneDays;
    }

    public void setUserInitialPruneDays(Integer userInitialPruneDays) {
        this.userInitialPruneDays = userInitialPruneDays;
    }

    public Integer getUserPruneIntervalDays() {
        return userPruneIntervalDays;
    }

    public void setUserPruneIntervalDays(Integer userPruneIntervalDays) {
        this.userPruneIntervalDays = userPruneIntervalDays;
    }

    public double getUserMaxTrellisCapacity() {
        return userMaxTrellisCapacity;
    }

    public void setUserMaxTrellisCapacity(double userMaxTrellisCapacity) {
        this.userMaxTrellisCapacity = userMaxTrellisCapacity;
    }
}
