package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;

import com.plantfarmlogger.model.CropBed;

public interface CropBedDaoInter {
    ArrayList<CropBed> getCropBeds();
    void create(CropBed t);
    void delete(CropBed t);

}