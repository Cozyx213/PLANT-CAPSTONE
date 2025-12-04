package com.plantfarmlogger.view.croplog;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.croplog.CropLogModel.CropLogEntry;

import java.util.List;

public class CropLogController {

    private final CropLogModel model;
    private final CropLogView view;

    public CropLogController(User user) {
        this.model = new CropLogModel();
        this.view = new CropLogView(user);

        // mock data rani
        initializeMockData();

        // refresh view with initial data
        refreshView();

        view.setAddLogListener(e -> handleAddLog());
    }

    private void initializeMockData() {
        model.setCropName("Tomato Bed #1");
        model.addLog(new CropLogEntry("2025-10-28", "Day 12", "Healthy", "Vegetative", "Watered, Added Compost"));
        model.addLog(new CropLogEntry("2025-10-25", "Day 9", "Excellent", "Seedling", "Watered"));
    }

    private void handleAddLog() {
        // get user input
        CropLogEntry newEntry = view.showAddLogDialog();

        // update model and view if user input is valuid
        if (newEntry != null) {
            model.addLog(newEntry);
            refreshView();
        }
    }

    // Inside CropLogController
    public void setCropName(String name) {
        model.setCropName(name);
        // Immediately refresh the view title
        view.setCropTitle(name);
    }

    private void refreshView() {
        view.updateView(model.getCropName(), model.getLogs());
    }


    public CropLogView getView() {
        return view;
    }
}