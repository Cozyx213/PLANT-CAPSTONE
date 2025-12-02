package com.plantfarmlogger.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.plantfarmlogger.model.User;
import com.plantfarmlogger.model.interfaces.UserDaoInter;

public class UserDao implements UserDaoInter {
    ArrayList<User> Users = new ArrayList<User>();

    private final String userFile = "src/main/resources/csv/users.csv";

    public UserDao() {
        fetch();
        printU();
    }

    public ArrayList<User> getUsers() {
        return Users;
    }

    private void fetch() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(userFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                String[] spl = line.split(",");
                if (spl.length < 5)
                    continue;

                String name = spl[0];
                String username = spl[1];
                String address = spl[2];
                int age = Integer.parseInt(spl[3]);
                String password = spl[4];

                User n = new User(name, username, address, age, password);
                Users.add(n);
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR theres no file " + userFile);
        }
        System.out.println("OPEnEd " + userFile);
    }

    private void saveToCSV() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(userFile));

        ) {
            for (User u : Users) {
                bw.write(String.join(",",
                        u.getName(),
                        u.getUsername(),
                        u.getAddress(),
                        String.valueOf(u.getAge()),
                        u.getPassword()));
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR");
        }
        System.out.println("SUCCESS");
    }

    public void create(User t) {
        if (t == null) {
            System.out.println("no user ");
            return;
        }
        Users.add(t);
        saveToCSV();

    }

    public void update(User t, String[] params) {

    }

    public void delete(User t) {
        if (t != null) {
            Users.removeIf(u -> u.getUsername().equals(t.getUsername()));
            saveToCSV();
        }
    }

    public void printU() {
        for (User u : Users) {
            System.out.println(u.getName() + u.getPassword());
        }
    }

    public User authenticate(String username, String password) {
        for (User u : Users) {
            if (u.getName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        System.out.println(username+password);
        return null;
    }

}
