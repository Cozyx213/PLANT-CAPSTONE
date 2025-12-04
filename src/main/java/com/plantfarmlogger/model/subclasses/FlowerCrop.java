package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Ornamental;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;
import java.util.Map;

public class FlowerCrop extends Crop implements Prunable, Ornamental {
    public static final Map<String, Integer> FLOWERCROP_INITIAL_PRUNE_DAYS = Map.ofEntries(
            Map.entry("rose", 30),
            Map.entry("marigold", 25),
            Map.entry("hibiscus", 40),
            Map.entry("lavender", 35)
    );
    public static final Map<String, Integer> FLOWERCROP_PRUNE_INTERVAL_DAYS = Map.ofEntries(
            Map.entry("rose", 60),
            Map.entry("marigold", 50),
            Map.entry("hibiscus", 70),
            Map.entry("lavender", 65)
    );
    public static final int DEFAULT_INITIAL_PRUNE_DAYS = 30;
    public static final int DEFAULT_PRUNE_INTERVAL_DAYS = 60;
    private String pruningDate;
    private Integer userInitialPruneDays;
    private Integer userPruneIntervalDays;
    private String aestheticFeatures;

    public FlowerCrop(String plantType, String soilType, String lastFertilized,
                      String datePlanted, double width, double height,
                      double length, String aestheticFeatures) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
        this.aestheticFeatures = aestheticFeatures;
    }

    @Override
    public void setExplicitPruningDate(String date) {
        pruningDate = date;
    }

    /**
     * Calculates and sets the next pruning date for this flower crop.
     * <p>
     * The calculation works as follows:
     * <ol>
     *     <li>If the crop has never been pruned ({@code pruningDate == null}),
     *         the next pruning date is calculated as planting date + initial prune days:
     *         <pre>nextPrune = datePlanted + initialPruneDays</pre></li>
     *     <li>If the crop has already been pruned, the next pruning date is
     *         calculated relative to the last pruning date. The interval between prunes
     *         is repeated until the next pruning date is after the current date
     *         ({@link java.time.LocalDate#now()}) to ensure future scheduling.</li>
     *     <li>The initial and interval days are determined in the following order:
     *         <ol type="a">
     *             <li>User-specified values ({@link #userInitialPruneDays} and {@link #userPruneIntervalDays})</li>
     *             <li>Species-specific values from {@link #FLOWERCROP_INITIAL_PRUNE_DAYS} and {@link #FLOWERCROP_PRUNE_INTERVAL_DAYS}</li>
     *             <li>Default values {@link #DEFAULT_INITIAL_PRUNE_DAYS} and {@link #DEFAULT_PRUNE_INTERVAL_DAYS}</li>
     *         </ol>
     *     </li>
     * </ol>
     * <p>
     * The calculated next pruning date is stored in {@link #pruningDate} in ISO-8601 format (yyyy-MM-dd).
     */
    @Override
    public void setCalculatedPruningDate() {
        LocalDate planted = LocalDate.parse(getDatePlanted());
        LocalDate now = LocalDate.now();

        int initialDays = (userInitialPruneDays != null) ? userInitialPruneDays :
                FLOWERCROP_INITIAL_PRUNE_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_INITIAL_PRUNE_DAYS);

        int intervalDays = (userPruneIntervalDays != null) ? userPruneIntervalDays :
                FLOWERCROP_PRUNE_INTERVAL_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_PRUNE_INTERVAL_DAYS);

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

    @Override
    public String getAestheticFeatures() {
        return this.aestheticFeatures;
    }

    public void setAestheticFeatures(String aestheticFeatures) {this.aestheticFeatures = aestheticFeatures;}
}
