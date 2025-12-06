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
    ArrayList<User> cache = new ArrayList<User>();
    private static UserDao instance = null;
    private final String userFile = "src/main/resources/csv/users.csv";

    private UserDao() {
        loadAll();
    }
    public static UserDao getInstance() {
        return instance == null ? instance = new UserDao() : instance;
    }

    private void loadAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] spl = line.split(",");
                String id = spl[0];
                String name = spl[1];
                String username = spl[2];
                String password = spl[3];
                String address = spl[4];
                int age = Integer.parseInt(spl[5]);
                User n = new User(id, name, username, password, address, age);
                cache.add(n);
            }
            System.out.println("[UserDao] Cache loaded successfully!");
        } catch (IOException e) {
            System.out.println("[UserDao] IO_ERROR: file " + userFile + "does not exist!");
        }
    }

    private void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(userFile))) {
            for (User u : cache) {
                bw.write(u.toString());
                bw.newLine();
            }
            System.out.println("[UserDao] Crops saved to file " + userFile + " successfully!");
        } catch (IOException e) {
            System.out.println("[UserDao] IO_ERROR: Error in opening file " + userFile);
        }
    }

    @Override
    public boolean createUser(User user) {
        if (user == null) {
            System.out.println("[UserDao] Create new User unsuccessful: Cannot create nonexistent user");
            return false;
        }
        for (User u : cache) {
            if(!u.getId().equals(user.getId())
                    && u.getUsername().equals(user.getUsername())) {
                System.out.println("[UserDao] User already exists with username " + user.getUsername());
                return false;
            }
        }
        cache.add(user);
        save();
        System.out.println("[UserDao] User " + user.getId() + " saved to file.");
        return true;
    }

    @Override
    public ArrayList<User> getUsers() {
        return cache;
    }

    @Override
    public User getUser(String userId) {
        for (User u : cache) {
            if (u.getId().equals(userId)) {
                System.out.println("[UserDao] User " + userId + " fetched successfully!");
                return u;
            }
        }
        System.out.println("[UserDao] User " + userId + " not found.");
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null) {
            System.out.println("[UserDao] Update User unsuccessful: Cannot update nonexistent user");
            return false;
        }
        for (int i = 0; i < cache.size(); i++) {
            if(cache.get(i).getId().equals(user.getId())) {
                cache.set(i, user);
                save();
                System.out.println("[UserDao] User " + user.getId() + " updated successfully!");
                return true;
            }
        }
        System.out.println("[UserDao] Failed to update User " + user.getId() + " not found.");
        return false;
    }

    @Override
    public void deleteUser(String userId) {
        // Do not FIXME: Iterating over a copy of the cache to avoid ConcurrentModificationException
        // new ArrayList<>(cache)
        for (User u : new ArrayList<>(cache)) {
            if (u.getId().equals(userId)) {
                cache.remove(u);
                save();
                System.out.println("[UserDao] User " + userId + " deleted successfully!");
                return;
            }
        }
        System.out.println("[UserDao] Failed to update User " + userId + " not found.");
    }

    public void printU() {
        for (User u : cache) {
            System.out.println(u);
        }
    }

}
