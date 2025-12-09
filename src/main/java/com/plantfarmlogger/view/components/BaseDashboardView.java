package com.plantfarmlogger.view.components;

import com.plantfarmlogger.controller.CropController;
import com.plantfarmlogger.controller.CropLogController;
import com.plantfarmlogger.controller.UserController;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;
import com.plantfarmlogger.view.AppNavigator;

import javax.swing.*;
import java.awt.*;

public abstract class BaseDashboardView extends JPanel {
    protected User user;
    protected UserController userController = UserController.getInstance();
    protected CropController cropController = CropController.getInstance();
    protected CropLogController cropLogController = CropLogController.getInstance();
    public final AppNavigator navigator;
    // getter for navigator and put a last accessed here?
    public BaseDashboardView(User user, AppNavigator navigator) {
        this.user = user;
        this.navigator = navigator;
        setLayout(new BorderLayout());
        setBackground(UIColors.BG_COLOR);

        add(new SideBar(user, navigator), BorderLayout.WEST);

        add(createContentPanel(), BorderLayout.CENTER);

    }

    protected abstract JPanel createContentPanel();
}