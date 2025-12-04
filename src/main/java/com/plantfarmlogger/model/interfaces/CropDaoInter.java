package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;

public interface CropDaoInter {
    ArrayList<Crop> findAll();
    Crop findByCropId(String cropId);
    ArrayList<Crop> findAllByUserId(String userId);
    boolean create(Crop t);
    void deleteAll();
    void deleteByCropId(String cropId);
    void deleteAllByUserId(String userId);
}