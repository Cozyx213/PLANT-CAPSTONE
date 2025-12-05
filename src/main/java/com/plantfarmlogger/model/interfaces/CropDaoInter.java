package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;

public interface CropDaoInter {
    Crop findByCropId(String cropId);
    ArrayList<Crop> findAllByUserId(String userId);
    boolean createCrop(Crop t);
    void deleteByCropId(String cropId);
    void deleteAllByUserId(String userId);
}