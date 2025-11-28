package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;
import com.plantfarmlogger.model.User;

public interface UserDaoInter {

    ArrayList<User> getUsers();

    void create(User t);

    void update(User t, String[] params);

    void delete(User t);

    User authenticate(String username, String password);

}