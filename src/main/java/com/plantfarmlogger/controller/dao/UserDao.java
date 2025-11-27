package com.plantfarmlogger.controller.dao;
import com.plantfarmlogger.model.User;
import com.plantfarmlogger.model.interfaces.UserDaoInter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class UserDao implements UserDaoInter{
	ArrayList <User> Users= new  ArrayList<User>();
    private final String userFile = "src/main/resources/csv/users.csv";





    public UserDao(){
        fetch();
    }
    public ArrayList<User> getUsers(){
        return Users;
    }
    void fetch(){
 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, true));
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            ){
                String line;

                while((line = br.readLine())!=null){
                    String[] spl = line.split(",");
                    
                    String name = spl[0];
                    String username = spl[1];
                    String address = spl[2];
                    String farm = spl[3];
                    int age = Integer.parseInt(spl[4]);
                    String password = spl[5];

                    User n = new User(name,username,address,farm,age,password);
                    Users.add(n);
                }
              
        }catch(IOException e){
           System.out.println( "IO_ERROR");
        }
       System.out.println( "SUCCESS");
    }
     void saveToCSV(){
 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(userFile));
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            ){
                for(User u : Users){
                    bw.write(u.getName()+","+u.getUsername()+","+u.getAddress()+","+u.getFarm()+","+u.getAge()+","+u.getPassword()+"\n");
                }
                
                
              
        }catch(IOException e){
           System.out.println( "IO_ERROR");
        }
       System.out.println( "SUCCESS");
    }
    public void save(User t){
        if(t==null){
            System.out.println("no user ");
        }
        Users.add(t);
        saveToCSV();

    }
    public void update(User t, String[] params){

    }
    public void delete(User t){
        int index =0;
        for(User u : Users ){

            if(u.getName().equals(t.getName())){
                Users.remove(index);
            }

            index++;
        }
        saveToCSV();
    }
    public void printU(){
        for(User u : Users){
            System.out.println(u);
        }
    }
    public boolean authenticate(User t){
        for(User u : Users){
            if(u.getName()==t.getName() && u.getPassword() == t.getPassword()) {
                return true;
            }
        }
        return false;
    }
    
}
