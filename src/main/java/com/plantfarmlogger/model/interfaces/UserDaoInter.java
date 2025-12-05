package com.plantfarmlogger.model.interfaces;

import java.util.ArrayList;
import com.plantfarmlogger.model.User;

public interface UserDaoInter {
    boolean createUser(User user);
    ArrayList<User> getUsers();
    User getUser(String userId);
    boolean updateUser(User user);
    void deleteUser(String userId);
}