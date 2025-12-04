package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Ornamental;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;
import java.util.Map;


public class FruitCrop extends Crop implements Prunable, Ornamental {
    public static final Map<String, Integer> FRUITCROP_INITIAL_PRUNE_DAYS = Map.ofEntries(
            Map.entry("apple", 30),
            Map.entry("pear", 28),
            Map.entry("cherry", 35),
            Map.entry("plum", 32)
    );
    public static final Map<String, Integer> FRUITCROP_PRUNE_INTERVAL_DAYS = Map.ofEntries(
            Map.entry("apple", 90),
            Map.entry("pear", 80),
            Map.entry("cherry", 70),
            Map.entry("plum", 75)
    );
    public static final int DEFAULT_INITIAL_PRUNE_DAYS = 30;
    public static final int DEFAULT_PRUNE_INTERVAL_DAYS = 90;
    private String pruningDate;
    private Integer userInitialPruneDays;
    private Integer userPruneIntervalDays;
    private String aestheticFeatures;
    public FruitCrop(String plantType, String soilType, String lastFertilized,
                     String datePlanted, double width, double height,
                     double length, String aestheticFeatures) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
        this.aestheticFeatures = aestheticFeatures;
    }




    @Override
    public void setExplicitPruningDate(String date) {
        this.pruningDate = date;
    }
    /**
     * Calculates and sets the next pruning date for this fruit crop.
     * <p>
     * The calculation follows a two-phase pruning schedule:
     * <ol>
     *     <li><b>Initial pruning:</b> If the crop has never been pruned (i.e., {@link #pruningDate} is {@code null}),
     *         the next pruning date is calculated as the planting date plus the initial prune days:
     *         <pre>
     *             nextPrune = datePlanted + initialPruneDays
     *         </pre>
     *         The number of initial prune days is determined in the following order:
     *         <ol type="a">
     *             <li>{@link #userInitialPruneDays} — if the user has specified a custom number of days.</li>
     *             <li>{@link #FRUITCROP_INITIAL_PRUNE_DAYS} — species-specific default initial pruning days.</li>
     *             <li>{@link #DEFAULT_INITIAL_PRUNE_DAYS} — fallback default value if the species is not listed.</li>
     *         </ol>
     *     </li>
     *     <li><b>Subsequent pruning:</b> If the crop has already been pruned (i.e., {@link #pruningDate} is not {@code null}),
     *         the next pruning date is calculated relative to the last pruning date. The method repeatedly adds the
     *         prune interval until the resulting date is after the current date ({@link java.time.LocalDate#now()}), ensuring
     *         that the next pruning date is always in the future.
     *         <p>
     *         The prune interval is determined in the following order:
     *         <ol type="a">
     *             <li>{@link #userPruneIntervalDays} — if the user has specified a custom interval.</li>
     *             <li>{@link #FRUITCROP_PRUNE_INTERVAL_DAYS} — species-specific default interval in days.</li>
     *             <li>{@link #DEFAULT_PRUNE_INTERVAL_DAYS} — fallback default interval.</li>
     *         </ol>
     *     </li>
     * </ol>
     * <p>
     * This method ensures that the pruning schedule accounts for missed pruning events by always
     * advancing the date to the next valid pruning date relative to today.
     * <p>
     * The calculated next pruning date is stored in {@link #pruningDate} in ISO-8601 format (yyyy-MM-dd).
     *
     * @see #pruningDate
     * @see #userInitialPruneDays
     * @see #userPruneIntervalDays
     * @see #FRUITCROP_INITIAL_PRUNE_DAYS
     * @see #FRUITCROP_PRUNE_INTERVAL_DAYS
     * @see #DEFAULT_INITIAL_PRUNE_DAYS
     * @see #DEFAULT_PRUNE_INTERVAL_DAYS
     * @see java.time.LocalDate
     */
    @Override
    public void setCalculatedPruningDate() {
        LocalDate planted = LocalDate.parse(getDatePlanted());
        LocalDate now = LocalDate.now();

        int initialDays = (userInitialPruneDays != null) ? userInitialPruneDays :
                FRUITCROP_INITIAL_PRUNE_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_INITIAL_PRUNE_DAYS);
        int intervalDays = (userPruneIntervalDays != null) ? userPruneIntervalDays :
                FRUITCROP_PRUNE_INTERVAL_DAYS.getOrDefault(getPlantType().toLowerCase(), DEFAULT_PRUNE_INTERVAL_DAYS);

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
    /**
     * Sets the aesthetic features for this fruit crop.
     * <p>
     * Aesthetic features describe the visual or ornamental characteristics
     * of the plant, such as blossom appearance, foliage color, growth habit,
     * fruit coloration, or any other decorative qualities. This value is
     * user-defined and is returned by {@link #getAestheticFeatures()}.
     *
     * @param aestheticFeatures a textual description of the plant's aesthetic or ornamental qualities
     */
    public void setAestheticFeatures(String aestheticFeatures) {this.aestheticFeatures = aestheticFeatures;}
}
