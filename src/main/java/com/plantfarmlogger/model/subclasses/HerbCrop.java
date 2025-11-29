package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Plant;
import com.plantfarmlogger.model.interfaces.Medicinal;
import com.plantfarmlogger.model.interfaces.Prunable;

import java.time.LocalDate;
import java.util.ArrayList;

public class HerbCrop extends Plant implements Prunable, Medicinal {
    public HerbCrop(String name) {
        super(name);
    }

    @Override
    public LocalDate schedulePruningDate() {
        return null;
    }

    @Override
    public ArrayList<String> getActiveCompounds() {
        return null;
    }
}
