package com.mUtilisateur.services;

import com.mUtilisateur.entity.User;

import java.util.List;

public interface IuserService {
    boolean addUser(User user);
    void updateUser(int id, User user);
    void deleteUser(int id);
    List<User> listUser();
    User getOneUser(int id);

}
