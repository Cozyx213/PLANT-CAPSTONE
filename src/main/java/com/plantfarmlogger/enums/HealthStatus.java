package com.plantfarmlogger.enums;

public enum HealthStatus {
    HEALTHY("healthy"),
    DEFICIENT("deficient"),
    INFECTED("infected"),
    PEST_INFESTED("pestinfested"),
    DEAD("dead");

    private final String value;
    
    HealthStatus(String value){
        this.value = value;
    }

    public String getValue(){return this.value;}
}
