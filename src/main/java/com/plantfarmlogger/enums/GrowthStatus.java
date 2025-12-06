package com.plantfarmlogger.enums;

public enum GrowthStatus {
    GERMINATION("Germination"),
    SEEDLING("Seedling"),
    VEGETATIVE("Vegetative"),
    REPRODUCTIVE("Reproductive"),
    MATURE("Mature");

    private final String value;

    GrowthStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

