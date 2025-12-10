package com.plantfarmlogger.enums;

public enum Action {
    FERTILIZE("FERTILIZE"),
    WATER("WATER"),
    AERATE("AERATE"),
    CUT("CUT");

    private final String value;

    Action(String value){
        this.value = value;
    }

    public String getValue(){return this.value;}
}