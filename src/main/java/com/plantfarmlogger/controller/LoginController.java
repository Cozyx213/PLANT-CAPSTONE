package com.plantfarmlogger.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LoginController {

    /**
     * Authenticate a user. Returns true if credentials match.
     * - Accepts admin/admin 
     * -`src/main/resources/csv/users.csv` contains lines of the form `username,password`, will check them too.
     */

    public boolean authenticate(String username, char[] password) {
        if (username == null || password == null) return false;
        System.out.println("input is\n" + username+"\n"+ new String(password));
        // test only okay
        if ("admin".equals(username) && Arrays.equals(password, "admin".toCharArray())) return true;

        // tloading users from the users.csv
        String userFile = "src/main/resources/csv/users.csv";
            try (BufferedReader r = new BufferedReader(new FileReader(userFile))) {

                String line;

                while ((line = r.readLine()) != null) {

                    line = line.trim();
                    System.out.println(line);

                    String[] parts = line.split(",");
               
           
                        String u = parts[1].trim();
                        String p = parts[5].trim();
                        System.out.println(u+ p);
                        if (u.equals(username) && Arrays.equals(password, p.toCharArray())) {
                            System.out.println("log in succesfull");
                            return true;
                        }else{
                            System.out.println("wrong creds lol r u hacker");
                        }
                    
                }
            }catch(IOException e){
                System.out.println("no file lol skill issue");
            }
        
        return false;
    }
}
