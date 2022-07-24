package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> optionalUser =
                userRepository.findById(id);
        return optionalUser.orElseThrow(
                () -> new NotFoundException(
                        "User not found for id: "+ id
                ));
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
       findById(user.getId());
       return userRepository.save(user);
    }

    @Override
    public String deleteUser(int id) {
        findById(id);
        userRepository.deleteById(id);
        return "User Deleted Successfully";
    }
}
