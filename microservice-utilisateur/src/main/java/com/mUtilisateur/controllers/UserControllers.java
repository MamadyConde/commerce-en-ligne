package com.mUtilisateur.controllers;

import com.mUtilisateur.entity.User;
import com.mUtilisateur.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllers {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public List<User> userList(){
        return userService.listUser();
    }

    @GetMapping("/users/{idUser}")
    public User getOneUser(@PathVariable int idUser){
        return userService.getOneUser(idUser);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        boolean flag = userService.addUser(user);
        if (!flag) return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable int idUser, @RequestBody User user){
        userService.updateUser(idUser, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable int idUser){
        userService.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    }
