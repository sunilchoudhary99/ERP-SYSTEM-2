package com.example.MyWebsite.controller.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.User;
import com.example.MyWebsite.service.EmployeeManagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        String res = userService.registerUser(user);
        return res.contains("Error") ? ResponseEntity.badRequest().body(res) : ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> data) {
        String res = userService.loginUser(data.get("username"), data.get("password"));
        return res.contains("Error") ? ResponseEntity.status(401).body(res) : ResponseEntity.ok(res);
    }
}