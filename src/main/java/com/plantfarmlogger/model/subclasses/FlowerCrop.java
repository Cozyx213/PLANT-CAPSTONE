package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Plant;
import com.plantfarmlogger.model.interfaces.Ornamental;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;

public class FlowerCrop extends Plant implements Prunable, Ornamental {
    public FlowerCrop(String name) {
        super(name);
    }

    @Override
    public LocalDate schedulePruningDate() {
        return null;
    }

    @Override
    public String getAestheticFeatures() {
        return "";
    }
}
