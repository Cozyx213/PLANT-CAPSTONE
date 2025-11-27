package com.plantfarmlogger.model.interfaces;

import com.plantfarmlogger.model.User;

public interface UserDaoInter {
   
    void save(User t);
    void update(User t, String[] params);
    void delete(User t);
    boolean authenticate(User t);

}