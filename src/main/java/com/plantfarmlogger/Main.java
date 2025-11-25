package com.plantfarmlogger;

import javax.swing.SwingUtilities;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.MainWindow;
import com.plantfarmlogger.controller.*;
public class Main{

    public static void main(String[] args) {

        User jake = new User("jake","gwapo","passwordni","moon", 19);
        System.out.println(jake);
        System.out.println("Hello owrld QWD ");
          // show Swing UI on EDT
        SwingUtilities.invokeLater(() -> {
            MainWindow w = new MainWindow();
            w.setVisible(true);
        });
        RegisterController rc = new RegisterController();
        String res = rc.register("jake bajenting2","cozy" , "password123", "cebu", "jake farm", 19);
        System.out.println(res);

    }
}