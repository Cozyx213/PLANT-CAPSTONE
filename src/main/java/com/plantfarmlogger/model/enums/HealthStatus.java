package com.plantfarmlogger.model.enums;

public enum HealthStatus {
    HEALTHY("Healthy"),
    DEFICIENT("Deficient"),
    INFECTED("Infected"),
    PEST_INFESTED("Pest Infested"),
    DEAD("Dead");

    private final String value;
    
    HealthStatus(String value){
        this.value = value;
    }

    public String getValue(){return this.value;}
}
