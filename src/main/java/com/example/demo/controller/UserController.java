package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user") // Adjusted to match the frontend's /user endpoint
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to retrieve all users
    @GetMapping("/all") // Optional, to differentiate this from the /user registration route
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Endpoint for user registration
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
