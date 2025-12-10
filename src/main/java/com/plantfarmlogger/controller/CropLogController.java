package com.plantfarmlogger.controller;

import java.util.ArrayList;
import java.util.Arrays;

import com.plantfarmlogger.controller.dao.CropLogDao;
import com.plantfarmlogger.enums.Action;
import com.plantfarmlogger.enums.GrowthStatus;
import com.plantfarmlogger.enums.HealthStatus;
import com.plantfarmlogger.model.CropLog;

public class CropLogController {
    private static CropLogController instance = null;
    private final CropLogDao cropLogDao;

    public CropLogController() {
        this.cropLogDao = CropLogDao.getInstance();
    }

    public static CropLogController getInstance() {
        return instance == null ? instance = new CropLogController() : instance;
    }

    private static final ArrayList<HealthStatus> healthStatusMap = new ArrayList<>(Arrays.asList(
            HealthStatus.DEAD,
            HealthStatus.HEALTHY,
            HealthStatus.INFECTED,
            HealthStatus.DEFICIENT,
            HealthStatus.PEST_INFESTED));
    private static final ArrayList<GrowthStatus> growthStatusMap = new ArrayList<>(Arrays.asList(
            GrowthStatus.MATURE,
            GrowthStatus.GERMINATION,
            GrowthStatus.SEEDLING,
            GrowthStatus.REPRODUCTIVE,
            GrowthStatus.VEGETATIVE));
    private static final ArrayList<Action> actionsMap = new ArrayList<>(Arrays.asList(
            Action.CUT,
            Action.AERATE,
            Action.WATER,
            Action.FERTILIZE));

    public boolean addCropLog(String notes, String healthStatus, String growthStatus,
            String[] actions, String userId, String cropId) {

        validateCropLog(notes, healthStatus, growthStatus, actions);
        HealthStatus hs = HealthStatus.valueOf(healthStatus.toUpperCase());
        GrowthStatus gs = GrowthStatus.valueOf(growthStatus.toUpperCase());
        ArrayList<Action> actionsList = new ArrayList<>();
        for (String action : actions) {
            actionsList.add(Action.valueOf(action.toUpperCase()));
        }
        CropLog newCropLog = new CropLog(notes, hs, gs, actionsList, userId, cropId);
        boolean success = cropLogDao.createCropLog(newCropLog);
        if (success) {
            System.out.println("[CropLogController] Crop log created successfully");
            return true;
        }
        System.out.println("[CropLogController] Failed to create crop log");
        return false;
    }

    public ArrayList<CropLog> getCropLogsByCropId(String cropId) {
        return cropLogDao.getAllByCropId(cropId);
    }

    public ArrayList<CropLog> getCropLogsByUserId(String userId) {
        return cropLogDao.getAllByUserId(userId);
    }

    public boolean deleteCropLog(String cropLogId) {
        cropLogDao.deleteCropLog(cropLogId);
        System.out.println("[CropLogController] Crop log " + cropLogId + " deleted");
        return true;
    }

    public boolean deleteCropLogsByCropId(String cropId) {
        cropLogDao.deleteCropsByCropId(cropId);
        System.out.println("[CropLogController] Crops logs of crop " + cropId + " deleted");
        return true;
    }

    public boolean deleteCropLogsByUserId(String userId) {
        cropLogDao.deleteCropsByUserId(userId);
        System.out.println("[CropLogController] Crops logs of user " + userId + " deleted");
        return true;
    }

    private void validateCropLog(String notes, String healthStatus, String growthStatus,
            String[] actions) {
        HealthStatus hs;
        GrowthStatus gs;
        try {
            hs = HealthStatus.valueOf(healthStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid health status: " + healthStatus);
        }
        try {
            gs = GrowthStatus.valueOf(growthStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid growth status: " + growthStatus);
        }
        for (String action : actions) {
            try {
                Action.valueOf(action.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid action: " + action);
            }
        }
    }

}
