package com.plantfarmlogger.view.components;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;

import javax.swing.*;
import java.awt.*;

public abstract class BaseDashboardView extends JPanel {

    public BaseDashboardView(User user) {
        setLayout(new BorderLayout());
        setBackground(UIColors.BG_COLOR);

        add(new SideBar(user), BorderLayout.WEST);

        add(createContentPanel(), BorderLayout.CENTER);
    }

    protected abstract JPanel createContentPanel();
}