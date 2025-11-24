package com.plantfarmlogger.model;

public class User{
    private String name;
    private String username;
    private String password;
    private String address;
    private int age;

    public User(String name, String username,
                String password, String address,  
                int age){
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.age = age;
    }

    public void changeName(String newName){
        this.name = newName;
    }
    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    public void changeAddress(String newAddress){
        this.address = newAddress;
    }



    // Getters and Setters
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
