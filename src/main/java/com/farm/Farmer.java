package com.farm;

public class Farmer {
    private String name;
    private int age;
    private String address;
    private String farmName;

    public Farmer(String name, int age, String address, String farmName) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.farmName = farmName;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
}
