package com.plantfarmlogger.model;

import com.plantfarmlogger.model.interfaces.*;

public class Plant {
    private String name;

    public Plant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
