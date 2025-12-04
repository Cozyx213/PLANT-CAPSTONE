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
    }

    public ArrayList<User> getUsers() {
        return Users;
    }

    private void fetch() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(userFile));) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",");

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
                bw.write(u.getName() + "," + u.getUsername() + "," + u.getAddress() + "," + u.getAge() + ","
                        + u.getPassword() + "\n");
            }

        } catch (IOException e) {
            System.out.println("IO_ERROR");
        }
        System.out.println("SUCCESS");
    }

    public void create(User t) {
        if (t == null) {
            System.out.println("no user ");
        }
        Users.add(t);
        saveToCSV();

    }

    public void update(User t, String[] params) {

    }

    public void delete(User t) {
        int index = 0;
        for (User u : Users) {

            if (u.getName().equals(t.getName())) {
                Users.remove(index);
            }

            index++;
        }
        saveToCSV();
    }

    public void printU() {
        for (User u : Users) {
            System.out.println(u);
        }
    }

    public User authenticate(String username, String password) {
        for (User u : Users) {
            if (u.getName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

}
