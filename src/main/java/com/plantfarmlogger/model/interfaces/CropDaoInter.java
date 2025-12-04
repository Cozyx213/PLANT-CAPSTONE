package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;

import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.User;

public interface CropDaoInter {
    ArrayList<Crop> getCropBeds(User user);
    void create(Crop t);
    void delete(Crop t);

}