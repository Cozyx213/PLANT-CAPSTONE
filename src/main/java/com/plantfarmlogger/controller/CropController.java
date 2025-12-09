package com.plantfarmlogger.controller;



import com.plantfarmlogger.controller.dao.CropDao;
import com.plantfarmlogger.model.Crop;
import com.plantfarmlogger.model.subclasses.RootCrop;

import java.util.ArrayList;

public class CropController {
    private static CropController instance;
    private final CropDao cropDao;
    public CropController() {
        this.cropDao = CropDao.getInstance();
    }
    public static CropController getInstance() {return instance == null ? instance = new CropController(): instance;}
    /**
     * Creates a Crop object based on type and parameters.
     *
     * @param cropType        "Herb", "Leaf", or "Root"
     * @param plantType       species name
     * @param soilType
     * @param width
     * @param height
     * @param length
     * @param userId
     * @param pruningDate     optional for Herb/Leaf
     * @param userBaseDays    optional for Herb/Leaf
     * @param activeCompounds optional for Herb
     * @param userRootDensity optional for Root
     * @return Crop object of the correct subclass
     */
    public Crop addCrop(
            String cropType, String plantType, String soilType,
            double width, double height, double length,
            String userId, String pruningDate, Integer userBaseDays,
            String activeCompounds, Double userRootDensity
    ){
        Crop newCrop = CropFactory.createCrop(
                cropType, plantType, soilType,
                width, height, length,
                userId, userBaseDays,
                activeCompounds, userRootDensity
        );
        validateCrop(newCrop);
        boolean res = cropDao.createCrop(newCrop);
        if(res){
            System.out.println("[CropController] Crop created successfully");
            return newCrop;
        }
        System.out.println("[CropController] Failed to create crop");
        return null;
    }

    public Crop get(String cropId) {
        return cropDao.getCrop(cropId);
    }

    public ArrayList<Crop> getAllByUserId(String userId) {
        return cropDao.getAllByUserId(userId);
    }

    public Crop updateCrop(
            String cropType,
            String cropId, String plantType, String soilType,
            String lastFertilized, String datePlanted,
            Double width, Double height, Double length,
            String userId, String pruningDate, Integer userBaseDays,
            String activeCompounds, Double userRootDensity
    ){
        Crop existing = cropDao.getCrop(cropId);
        if (existing == null) {
            System.out.println("[CropController] Crop " + cropId + " does not exist");
            return null;
        }

        Crop updatedCrop = CropFactory.updateCrop(existing,
                cropType,
                cropId, plantType, soilType,
                lastFertilized, datePlanted,
                width, height, length,
                userId, pruningDate, userBaseDays,
                activeCompounds, userRootDensity);
        validateCrop(updatedCrop);
        boolean success = cropDao.updateCrop(updatedCrop);
        if (success) {
            System.out.println("[CropController] Crop " + cropId + " updated successfully");
            return updatedCrop;
        }
        System.out.println("[CropController] Crop " + cropId + " could not be updated");
        return null;
    }

    public boolean delete(String cropId) {
        cropDao.deleteCrop(cropId);
        System.out.println("[CropController] Crop " + cropId + " deleted");
        return true;
    }

    public boolean deleteAllByUserId(String userId) {
        cropDao.deleteCropsByUserId(userId);
        System.out.println("[CropController] Crops of user " + userId + " deleted");
        return true;
    }

    public int getCountByUser(String userId){
        return cropDao.getCountByUser(userId);
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
