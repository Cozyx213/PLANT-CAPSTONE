package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Plant;
import com.plantfarmlogger.model.interfaces.Medicinal;
import com.plantfarmlogger.model.interfaces.Subterranean;

import java.util.ArrayList;

public class BulbCrop extends Plant implements Medicinal, Subterranean {
    public BulbCrop(String name) {
        super(name);
    }

    @Override
    public ArrayList<String> getActiveCompounds() {
        return null;
    }

    @Override
    public double estimateMass() {
        return 0;
    }
}
