package com.microservices.service;

import com.microservices.model.dto.AuthDto;
import com.microservices.model.dto.LoginDto;
import com.microservices.model.entity.User;
import com.microservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUserProfile(String token, User updateRequest) {
        String username = jwtService.extractUsername(token);
        User existingUser = userRepository.findByUsername(username);

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if (updateRequest.getName() != null) existingUser.setName(updateRequest.getName());
        if (updateRequest.getEmail() != null) existingUser.setEmail(updateRequest.getEmail());
        if (updateRequest.getAge() > 0) existingUser.setAge(updateRequest.getAge());

        return userRepository.save(existingUser);
    }

    public AuthDto login(LoginDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername());
        return new AuthDto(token, user.getUsername(), user.getName(), user.getEmail(), user.getAge());
    }

    public User getUserProfile(String token) {
        String username = jwtService.extractUsername(token);
        return userRepository.findByUsername(username);
    }
}
