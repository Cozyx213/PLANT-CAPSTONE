package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Plant;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;

public class NutCrop extends Plant implements Prunable {
    public NutCrop(String name) {
        super(name);
    }

    @Override
    public LocalDate schedulePruningDate() {
        return null;
    }
}
