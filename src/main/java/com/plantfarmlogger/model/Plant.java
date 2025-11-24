package com.plantfarmlogger.model;

public class Plant {
    private String name;
    private int age;
    public Plant() {}
    public Plant(String name, int age) { this.name = name; this.age = age; }
    public String getName() { return name; }
    public int getAge() { return age; }

}
