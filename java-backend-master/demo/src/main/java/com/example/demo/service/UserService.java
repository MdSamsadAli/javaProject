package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User findById(int id);

    User findByUsername(String username);

    User addUser(User user);

    User updateUser(User user);

    String deleteUser(int id);
}
