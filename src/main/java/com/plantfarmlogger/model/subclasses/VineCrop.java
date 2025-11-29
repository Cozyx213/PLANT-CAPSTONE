package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Plant;
import com.plantfarmlogger.model.interfaces.Prunable;
import com.plantfarmlogger.model.interfaces.TrellisSupportable;

import java.time.LocalDate;

public class VineCrop extends Plant implements Prunable, TrellisSupportable {
    public VineCrop(String name) {
        super(name);
    }

    @Override
    public LocalDate schedulePruningDate() {
        return null;
    }

    @Override
    public boolean validateWeight() {
        return false;
    }
}
