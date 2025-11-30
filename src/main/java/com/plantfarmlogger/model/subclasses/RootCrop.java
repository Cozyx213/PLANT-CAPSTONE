package com.plantfarmlogger.model.subclasses;

import com.plantfarmlogger.model.Plant;
import com.plantfarmlogger.model.interfaces.Subterranean;

public class RootCrop extends Plant implements Subterranean {
    public RootCrop(String name) {
        super(name);
    }

    @Override
    public double estimateMass() {
        return 0;
    }
}
