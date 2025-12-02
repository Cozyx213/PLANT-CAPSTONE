package com.plantfarmlogger.view.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;

public abstract class BaseDashboardView extends JPanel {

    public BaseDashboardView(User user, Runnable onLogout) {
        setLayout(new BorderLayout());
        setBackground(UIColors.BG_COLOR);

        add(new SideBar(user, onLogout), BorderLayout.WEST);

        add(createContentPanel(), BorderLayout.CENTER);
    }

    protected abstract JPanel createContentPanel();
}