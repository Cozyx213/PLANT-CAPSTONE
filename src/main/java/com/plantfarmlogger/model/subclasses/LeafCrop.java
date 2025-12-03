package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;
import java.util.Map;

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
            Map.entry("red leaf lettuce", 14)
    );
    public static final int DEFAULT_BASE_GROWING_DAYS = 14;
    private String pruningDate;
    private Integer userBaseGrowingDays;

    public LeafCrop(String plantType, String soilType, String lastFertilized,
                    String datePlanted, double width, double height,
                    double length, String pruningDate) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
        this.pruningDate = pruningDate;
    }
    public LeafCrop(String plantType, String soilType, String lastFertilized,
                    String datePlanted, double width, double height,
                    double length) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
        this.pruningDate = null;
        this.userBaseGrowingDays = null;
    }

    /**
     * Sets an explicit pruning date for this leaf crop.
     * <p>
     * Overrides any automatically calculated pruning date. The date should
     * be provided in ISO-8601 format (yyyy-MM-dd).
     *
     * @param date the explicit pruning date to assign to this crop
     */
    @Override
    public void setExplicitPruningDate(String date) {
        this.pruningDate = date;
    }
    /**
     * Calculates and sets the next pruning date for this leaf crop.
     * <p>
     * This method determines the upcoming pruning date based on the following rules:
     * <ol>
     *     <li>If the crop has never been pruned (i.e., {@link #pruningDate} is {@code null}),
     *         the next pruning date is calculated as the planting date plus the base growing days:
     *         <pre>
     *             nextPrune = datePlanted + baseGrowingDays
     *         </pre>
     *     </li>
     *     <li>If the crop has already been pruned (i.e., {@link #pruningDate} is not {@code null}),
     *         the next pruning date is calculated relative to the last pruning date.
     *         The method repeatedly adds the base growing days until the resulting date is
     *         after the current date ({@link java.time.LocalDate#now()}), ensuring that
     *         the next prune is always in the future.</li>
     *     <li>The {@code baseDays} used for calculation is determined in the following order of precedence:
     *         <ol type="a">
     *             <li>{@link #userBaseGrowingDays} — if the user has set a custom number of base growing days.</li>
     *             <li>{@link #LEAFCROP_BASE_GROWING_DAYS} — the species-specific default for the crop type.</li>
     *             <li>{@link #DEFAULT_BASE_GROWING_DAYS} — a fallback value if the crop type is not in the species-specific map.</li>
     *         </ol>
     *     </li>
     * </ol>
     * <p>
     * The calculated next pruning date is stored in {@link #pruningDate} in ISO-8601 format (yyyy-MM-dd).
     * <p>
     * This implementation ensures that the pruning schedule always points to the upcoming
     * pruning date relative to today, even if previous prunes were missed.
     *
     * @see #pruningDate
     * @see #userBaseGrowingDays
     * @see #LEAFCROP_BASE_GROWING_DAYS
     * @see #DEFAULT_BASE_GROWING_DAYS
     * @see java.time.LocalDate
     */
    @Override
    public void setCalculatedPruningDate() {
        LocalDate planted =  LocalDate.parse(getDatePlanted());
        LocalDate now = LocalDate.now();
        int baseDays;
        if (userBaseGrowingDays != null) {
            baseDays = userBaseGrowingDays;
        } else {
            baseDays = LEAFCROP_BASE_GROWING_DAYS.getOrDefault(
                    getPlantType().toLowerCase(),
                    DEFAULT_BASE_GROWING_DAYS
            );
        }
        LocalDate calculatedPruneDate;
        if(pruningDate == null) {
            calculatedPruneDate = LocalDate.parse(getDatePlanted()).plusDays(baseDays);
        }else{
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
}
