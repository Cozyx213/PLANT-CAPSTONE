package com.plantfarmlogger.controller;

import com.plantfarmlogger.controller.dao.CropLogDao;
import com.plantfarmlogger.controller.dao.UserDao;
import com.plantfarmlogger.enums.Action;
import com.plantfarmlogger.enums.GrowthStatus;
import com.plantfarmlogger.enums.HealthStatus;
import com.plantfarmlogger.model.CropLog;
import com.plantfarmlogger.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class CropLogController {
    private static CropLogController instance = null;
    private final CropLogDao cropLogDao;
    private static final ArrayList<HealthStatus> healthStatusMap = new ArrayList<>(Arrays.asList(
            HealthStatus.DEAD,
            HealthStatus.HEALTHY,
            HealthStatus.INFECTED,
            HealthStatus.DEFICIENT,
            HealthStatus.PEST_INFESTED
    ));
    private static final ArrayList<GrowthStatus> growthStatusMap = new ArrayList<>(Arrays.asList(
            GrowthStatus.MATURE,
            GrowthStatus.GERMINATION,
            GrowthStatus.SEEDLING,
            GrowthStatus.REPRODUCTIVE,
            GrowthStatus.VEGETATIVE
    ));
    private static final ArrayList<Action> actionsMap = new ArrayList<>(Arrays.asList(
            Action.CUT,
            Action.AERATE,
            Action.WATER,
            Action.FERTILIZE
    ));
    public CropLogController() {
        this.cropLogDao = CropLogDao.getInstance();
    }
    public static CropLogController getInstance() { return instance == null ? instance = new CropLogController(): instance;}

//    public CropLog(String notes, HealthStatus healthStatus,
//                   GrowthStatus growthStatus, ArrayList<Action> actions, String cropBed,
//                   String userId, String cropId) {
//        this(notes, LocalDate.now().toString(), healthStatus, growthStatus, actions,  userId, cropId);
//    }
    public boolean addCropLog(String notes, String healthStatus, String growthStatus,
                              String[] actions, String userId, String cropId){

        validateCropLog(notes, healthStatus, growthStatus, actions);
        HealthStatus hs = HealthStatus.valueOf(healthStatus.toUpperCase());
        GrowthStatus gs = GrowthStatus.valueOf(growthStatus.toUpperCase());

        ArrayList<Action> actionsList = new ArrayList<>();
        for(String action : actions){
            actionsList.add(Action.valueOf(action.toUpperCase()));
        }
        CropLog newCropLog = new CropLog(notes, hs, gs, actionsList, userId, cropId);
        boolean success = cropLogDao.createCropLog(newCropLog);
        if(success) {
            return true;
        }
        return false;
    }

    public ArrayList<CropLog> getCropLogsByCropId(String cropId){
        return cropLogDao.getCropLogs();
    }

    private void validateCropLog(String notes, String healthStatus, String growthStatus,
                                 String[] actions){
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
