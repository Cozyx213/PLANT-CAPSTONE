package com.plantfarmlogger.model;

import com.plantfarmlogger.model.interfaces.*;

public class Plant implements Fertilizable, Waterable,
        Aeratable, Cuttable{
    private String name;
    private int age;

    public Plant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void cut() {}

    @Override
    public

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
