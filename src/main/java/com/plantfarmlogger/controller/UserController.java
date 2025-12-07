package com.plantfarmlogger.controller;

import com.plantfarmlogger.controller.dao.UserDao;
import com.plantfarmlogger.model.User;

import java.util.ArrayList;

public class UserController {
    private static UserController instance = null;
    private final UserDao userDao;
    public UserController() {
        this.userDao = UserDao.getInstance();
    }
    public static UserController getInstance() { return instance == null ? instance = new UserController(): instance;}
    public boolean addUser(String name, String username, String password,
                   String address, int age){
        User newUser = new User(name, username, password, address, age);
        validateUser(newUser);

        return userDao.createUser(newUser);
    }

    public ArrayList<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(String userId){
        System.out.println("getUser");
        return userDao.getUser(userId);
    }

    public User updateUser(String userId,
                           String name, String username, String password,
                           String address, Integer age){
        User existingUser = userDao.getUser(userId);
        if(existingUser == null){
            System.out.println("[CropController] User " + userId + " could not be found");
        }
        return new User(
                userId,
                name != null ? name : existingUser.getName(),
                username != null ? username : existingUser.getUsername(),
                password != null ? password : existingUser.getPassword(),
                address != null ? address : existingUser.getAddress(),
                age != null ? age : existingUser.getAge());

    }

    public void deleteUser(String userId){
        userDao.deleteUser(userId);
        System.out.println("[CropController] User " + userId + " deleted");
    }

    public User authenticateLogin(String name, String password) {
        ArrayList<User> users = userDao.getUsers();
        for (User u : users) {
            System.out.println("Name: " + u.getName() + " Password: " + u.getPassword());
            System.out.println("Provided name: " + name + " Password: " + password);
            if (u.getName().equals(name) && u.getPassword().equals(password)) {
                System.out.println("[CropController] User " + name + " authenticated");
                return u;
            }
        }
        System.out.println("Invalid credentials");
        return null;
    }
    private void validateUser(User newUser){
        if(newUser == null){throw new IllegalArgumentException("User cannot be null");}
        if(newUser.getName() == null || newUser.getName().isEmpty()){throw new IllegalArgumentException("Name cannot be null or empty");}
        if(newUser.getUsername() == null){throw new IllegalArgumentException("Username cannot be null");}
        if(newUser.getPassword() == null || newUser.getPassword().isEmpty()){throw new IllegalArgumentException("Password cannot be null or empty");}
        if(newUser.getAddress() == null){throw new IllegalArgumentException("Address cannot be null");}
        if(newUser.getAge() <= 0){throw new IllegalArgumentException("Age cannot be negative or zero");}
    }
}
