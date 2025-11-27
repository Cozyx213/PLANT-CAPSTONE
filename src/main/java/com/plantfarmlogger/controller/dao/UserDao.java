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
	ArrayList <User> Users;
    private final String userFile = "src/main/resources/csv/users.csv";
       
    void fetch(){
 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, true));
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            ){
                String line;

                while((line = br.readLine())!=null){
                    String[] spl = line.split(",");
                    
                    String name = spl[0];
                    String username = spl[1];
                    int age = Integer.parseInt(spl[2]);
                    String address = spl[3];
                    String farm = spl[4];
                    String password = spl[5];

                    User n = new User(name,username,address,farm,age,password);
                    Users.add(n);
                }
              
        }catch(IOException e){
           System.out.println( "IO_ERROR");
        }
       System.out.println( "SUCCESS");
    }
    public void save(User t){
        Users.add(t);

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
    }
    public void authenticate(User t){
        
    }
}
