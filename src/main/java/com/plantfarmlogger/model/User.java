package com.plantfarmlogger.model;

public class User {
    private String name;
    private String username;
    private String password;
    private String address;
    private int age;
    private String farm;
    public User(String name, String username, String address,String farm, int age, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.age = age; 
        this.farm = farm;
    //    User n = new User(name,username,address,farm,age,password);
                
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
    public String getFarm(){
        return farm;
    }
    public void setFarm(String farm){
        this.farm = farm;
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
    @Override
    public String toString(){
        return name;
    }
}
