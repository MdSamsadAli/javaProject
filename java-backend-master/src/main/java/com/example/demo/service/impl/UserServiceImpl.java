package com.example.demo.service.impl;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateDto updateDto) {
        User user = findById(updateDto.getId());
        user.setFullName(updateDto.getFullName());
        user.setAddress(updateDto.getAddress());
        user.setAge(updateDto.getAge());
        user.setFiles(updateDto.getFiles());
       return userRepository.save(user);
    }

    @Override
    public String deleteUser(int id) {
        findById(id);
        userRepository.deleteById(id);
        return "User Deleted Successfully";
    }
}
