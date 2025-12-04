package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.interfaces.Medicinal;
import com.plantfarmlogger.model.interfaces.Prunable;

public class HerbCrop extends Crop implements Prunable, Medicinal {
    private String activeCompounds;
    private String pruningDate;
    private int userBaseGrowingDays;

    public HerbCrop(String identification, String plantType, String soilType, String lastFertilized,
            String datePlanted, double width, double height,
            double length, String pruningDate, int userBaseGrowingDays, String activeCompounds) {
        super(identification, plantType, soilType, lastFertilized, datePlanted, width, height, length);
        this.pruningDate = pruningDate;
        this.userBaseGrowingDays = userBaseGrowingDays;
        this.activeCompounds = activeCompounds;
    }
     public HerbCrop( String plantType, String soilType, String lastFertilized,
            String datePlanted, double width, double height,
            double length, String pruningDate, int userBaseGrowingDays, String activeCompounds) {
        super(plantType, soilType, lastFertilized, datePlanted, width, height, length);
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
        // TODO: Implement date calculation logic using datePlanted and
        // userBaseGrowingDays
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
                getIdentification(),
                "HerbCrop-" + getPlantType(),
                getSoilType(),
                getLastFertilized(),
                getDatePlanted(),
                String.valueOf(getWidth()),
                String.valueOf(getHeight()),
                String.valueOf(getLength()),
                getPruningDate(),
                String.valueOf(getUserBaseGrowingDays()),
                getActiveCompounds());
    }
}
