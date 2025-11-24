package com.plantfarmlogger.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class RegisterController{

    public String register(String name, String username, String password, String address, String farm, int age ){

        if(username ==null|| name==null || password ==null|| address == null || age == 0|| farm==null){
            return "INVALID_INPUT";
        }
  // tloading users from the users.csv
        String userFile = "src/main/resources/csv/users.csv";
       
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, true));
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            ){
                String line;

                while((line = br.readLine())!=null){
                    String[] spl = line.split(",");

                    if(spl[1].equals(username)){
                        return "USER_EXIST";
                    }
               
                }
                String fin = name+","+username+","+age+","+address+","+farm+","+password;
                bw.write(fin);

        }catch(IOException e){
            return "IO_ERROR";
        }
        return "SUCCESS";
    }
    
}