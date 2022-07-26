package com.example.demo.security;


import com.example.demo.dto.LoginDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping
    public String login(@RequestBody LoginDto loginDto){
        Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
        if(bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            return JwtUtil.getJWTToken(user);
        }
        throw new RuntimeException("Username and Password not matched");

    }
}
