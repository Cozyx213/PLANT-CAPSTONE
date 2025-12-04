package com.plantfarmlogger.view.home;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.AppNavigator;
import com.plantfarmlogger.view.components.CropCardPanel;

public class HomeController {

    private final HomeModel model;
    private final HomeView view;
    private final AppNavigator navigator; // Reference needed for navigation
    private final User user; // keep reference to user for passing data

    private boolean isCreating = false; // The Lock

    public HomeController(User user, AppNavigator navigator) {
        this.user = user;
        this.navigator = navigator;
        this.model = new HomeModel();
        this.view = new HomeView(user);

        // Wire up the "Add" button
        this.view.setAddButtonListener(e -> handleAddNewCard());
    }

    private void handleAddNewCard() {
        if (isCreating) return; // Prevent multiple cards

        // lock the UI
        isCreating = true;
        view.toggleAddButton(false); // Helper method we need to add to View

        // callbacks for the vard

        // unlocking UI and saving the model on save
        java.util.function.Consumer<String> onSave = (cropName) -> {
            model.addCropBed(cropName);
            view.updateCountLabel(model.getCount());
            isCreating = false;
            view.toggleAddButton(true); // Unlock
        };

        // going to croplog
        java.util.function.Consumer<String> onNavigate = (cropName) -> {
            System.out.println("Navigating to logs for: " + cropName);
            navigator.showCropLogs(user, cropName);
        };

        // remove card, unlock UI on cancel
        Runnable onCancel = () -> {
            view.removeTopCard(); // Helper method to remove the temp card
            isCreating = false;
            view.toggleAddButton(true); // Unlock
        };

        // create,add card
        CropCardPanel card = new CropCardPanel(onSave, onNavigate, onCancel);
        view.addCardToTop(card);
    }

    public HomeView getView() {
        return view;
    }
}