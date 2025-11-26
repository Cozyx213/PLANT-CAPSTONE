package com.plantfarmlogger.model.enums;

public enum Action {
    FERTILIZE("fertilize"),
    WATER("water"),
    AERATE("aerate"),
    CUT("cut");

    private final String value;

    Action(String value){
        this.value = value;
    }

    public String getValue(){return this.value;}
}