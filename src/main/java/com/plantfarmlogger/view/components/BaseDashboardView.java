package com.plantfarmlogger.view.components;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.util.UIColors;

import javax.swing.*;
import java.awt.*;

public abstract class BaseDashboardView extends JPanel {

    // Store reference so subclasses can access it if needed (e.g. for loading screens)
    protected JPanel contentPanel;

    public BaseDashboardView(User user) {
        setLayout(new BorderLayout());
        setBackground(UIColors.BG_COLOR);

        add(new SideBar(user), BorderLayout.WEST);

        this.contentPanel = createContentPanel();

        add(contentPanel, BorderLayout.CENTER);
    }

    protected abstract JPanel createContentPanel();
}