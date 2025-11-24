package com.plantfarmlogger.model;

public class Farm {
    private String name;
    private int size;

    public Farm() {}
    public Farm(String name, int size) { this.name = name; this.size = size; }
    public String getName() { return name; }
    public int getSize() { return size; }
}