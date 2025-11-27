package com.plantfarmlogger;

import javax.swing.SwingUtilities;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.view.MainWindow;
import com.plantfarmlogger.controller.*;
import com.plantfarmlogger.controller.dao.UserDao;
public class Main{

    public static void main(String[] args) {
        UserDao ud = new UserDao();
        ud.save(new User("jake", "cozy", "argao", "argaofarm", 19,"password123"));
        User j = new User("jake", "cozy", "argao", "argaofarm", 19,"password123");
        ud.save(j);
        ud.printU();
        if(ud.authenticate(j)){
            System.out.println("logged in as "+ j.getName());
        };
        ud.printU();
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