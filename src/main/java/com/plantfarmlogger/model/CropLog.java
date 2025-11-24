package com.plantfarmlogger.model;

public class CropLog{
    private String name;
    private String date;
    private CropBed cropBed;

    // suggestion: use enum HealthStatus and GrowthStatus instead
    private String healthStatus;
    private String growthStatus;
    private Action action;
    private String address;
    private int age;

    public CropLog(String name, String date, CropBed cropBed,
                   String healthStatus, String growthStatus, Action action,
                   String address, int age) {
        this.name = name;
        this.date = date;
        this.cropBed = cropBed;
        this.healthStatus = healthStatus;
        this.growthStatus = growthStatus;
        this.action = action;
        this.address = address;
        this.age = age;
    }

    // suggestion: delete redundant method waterPlant(), Waterable.water() already exists
    public void waterPlant(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CropBed getCropBed() {
        return cropBed;
    }

    public void setCropBed(CropBed cropBed) {
        this.cropBed = cropBed;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getGrowthStatus() {
        return growthStatus;
    }

    public void setGrowthStatus(String growthStatus) {
        this.growthStatus = growthStatus;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}