package com.plantfarmlogger.enums;

public enum HealthStatus {
    HEALTHY("HEALTHY"),
    DEFICIENT("DEFICIENT"),
    INFECTED("INFECTED"),
    PEST_INFESTED("PEST_INFESTED"),
    DEAD("DEAD");

    private final String value;
    
    HealthStatus(String value){
        this.value = value;
    }

    public String getValue(){return this.value;}
}
