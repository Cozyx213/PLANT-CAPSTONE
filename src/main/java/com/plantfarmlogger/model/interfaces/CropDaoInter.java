package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;

import com.plantfarmlogger.model.Crop;

public interface CropDaoInter {
    ArrayList<Crop> getCropBeds();
    void create(Crop t);
    void delete(Crop t);

}