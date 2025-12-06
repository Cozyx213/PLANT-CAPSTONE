package com.plantfarmlogger.view.components;

import com.plantfarmlogger.controller.CropController;
import com.plantfarmlogger.controller.CropLogController;
import com.plantfarmlogger.controller.UserController;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;

import javax.swing.*;
import java.awt.*;

public abstract class BaseDashboardView extends JPanel {
    protected User user;
    protected UserController userController = UserController.getInstance();
    protected CropController cropController = CropController.getInstance();
    protected CropLogController cropLogController = CropLogController.getInstance();
    public BaseDashboardView(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(UIColors.BG_COLOR);

        add(new SideBar(user), BorderLayout.WEST);

        add(createContentPanel(), BorderLayout.CENTER);

    }

    protected abstract JPanel createContentPanel();
}