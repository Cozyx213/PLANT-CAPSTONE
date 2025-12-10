package com.plantfarmlogger.enums;

public enum GrowthStatus {
    GERMINATION("GERMINATION"),
    SEEDLING("SEEDLING"),
    VEGETATIVE("VEGETATIVE"),
    REPRODUCTIVE("REPRODUCTIVE"),
    MATURE("MATURE");

    private final String value;

    GrowthStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

