package com.plantfarmlogger.controller;



import com.plantfarmlogger.controller.dao.CropDao;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.subclasses.RootCrop;

import java.util.ArrayList;

public class CropController {
    private final CropDao cropDao;
    public CropController() {
        this.cropDao = CropDao.getInstance();
    }
    public boolean addCrop(
            String cropType, String plantType, String soilType,
            double width, double height, double length,
            String userId, String pruningDate, Integer userBaseDays,
            String activeCompounds, Double userRootDensity
    ){
        Crop newCrop = CropFactory.createCrop(
                cropType, plantType, soilType,
                width, height, length,
                userId, pruningDate, userBaseDays,
                activeCompounds, userRootDensity
        );
        validateCrop(newCrop);

        return cropDao.createCrop(newCrop);
    }

    public ArrayList<Crop> getAll() {
        return cropDao.findAll();
    }

    public Crop get(String cropId) {
        return cropDao.findByCropId(cropId);
    }

    public ArrayList<Crop> getAllByUserId(String userId) {
        return cropDao.findAllByUserId(userId);
    }

    public void deleteAll() {
        cropDao.deleteAll();
    }

    public boolean delete(String cropId) {
        cropDao.deleteByCropId(cropId);
        return true;
    }

    public boolean deleteAllByUserId(String userId) {
        cropDao.deleteAllByUserId(userId);
        return true;
    }

    private void validateCrop(Crop crop) {
        if (crop.getPlantType() == null || crop.getPlantType().isBlank()) {
            throw new IllegalArgumentException("Plant type required");
        }
        if (crop.getSoilType() == null || crop.getSoilType().isBlank()) {
            throw new IllegalArgumentException("Soil type required");
        }

        if (crop instanceof RootCrop) {
            if(crop.getWidth() <= 0 || crop.getHeight() <= 0 || crop.getLength() <= 0) {
                throw new IllegalArgumentException("All dimensions must be positive for RootCrop");
            }
        }
    }

}
