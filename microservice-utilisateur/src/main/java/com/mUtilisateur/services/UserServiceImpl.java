package com.mUtilisateur.services;

import com.mUtilisateur.dao.UserDao;
import com.mUtilisateur.entity.User;
import com.mUtilisateur.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IuserService{
    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        User userExist = userDao.findByusername(user.getUsername());
        if (userExist != null) throw new UserNotFoundException("L'utilisateur "+user.getUsername()+" existe déjà");
        userDao.save(user);
        return true;
    }

    @Override
    public void updateUser(int id, User user) {
        Optional<User> getUser = userDao.findById(id);
        if (!getUser.isPresent()) throw new UserNotFoundException("L'utilisateur avec l'Id "+id+" n'existe pas");
        getUser.get().setUsername(user.getUsername());
        getUser.get().setPassword(user.getPassword());
        userDao.save(getUser.get());
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> getUser = userDao.findById(id);
        if (!getUser.isPresent()) throw new UserNotFoundException("L'utilisateur avec l'Id "+id+" n'existe pas");
        userDao.delete(getUser.get());
    }

    @Override
    public List<User> listUser() {
        List<User> userList = userDao.findAll();
        if (userList.isEmpty()) throw new UserNotFoundException("Il n'existe aucun utilisateur");
        return userList;
    }

    @Override
    public User getOneUser(int id) {
        Optional<User> getUser = userDao.findById(id);
        if (!getUser.isPresent()) throw new UserNotFoundException("L'utilisateur avec l'Id "+id+" n'existe pas");
        return getUser.get();
    }
}
