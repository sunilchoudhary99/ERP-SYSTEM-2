package com.example.MyWebsite.service.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.User;
import com.example.MyWebsite.repository.EmployeeManagement.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) return "Error: Username exists!";
        if (userRepository.existsByEmail(user.getEmail())) return "Error: Email exists!";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return "Login Successful!";
        }
        return "Error: Invalid credentials!";
    }
}