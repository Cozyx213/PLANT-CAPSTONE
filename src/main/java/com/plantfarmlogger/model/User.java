package com.plantfarmlogger.model;

import java.util.UUID;

public class User {
    private String name;
    private String username;
    private String password;
    private String address;
    private int age;
    private String id;
    public User(String id, String name, String username, String address, int age, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.age = age;

    }public User( String name, String username, String address, int age, String password) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.age = age;

    }
    // User n = new User(name,username,address,farm,age,password);
    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
