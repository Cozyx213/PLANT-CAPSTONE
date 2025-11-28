package com.plantfarmlogger;

import javax.swing.SwingUtilities;
import com.plantfarmlogger.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        System.out.println("Farm Logger start");
        // show Swing UI on EDT
        SwingUtilities.invokeLater(() -> {
            MainWindow w = new MainWindow();
            w.setVisible(true);
        });

    }
}