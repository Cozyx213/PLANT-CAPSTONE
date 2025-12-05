package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;

public interface CropDaoInter {
    boolean createCrop(Crop crop);
    Crop getCrop(String cropId);
    ArrayList<Crop> getAllByUserId(String userId);
    boolean updateCrop(Crop crop);
    void deleteCrop(String cropId);
    void deleteCropsByUserId(String userId);
}