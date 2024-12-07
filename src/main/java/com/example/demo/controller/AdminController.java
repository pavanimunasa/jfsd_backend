package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from the frontend
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Register a new admin
    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        return adminService.register(admin);
    }

    // Login (authenticate admin)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        try {
            boolean isAuthenticated = adminService.authenticate(admin.getUsername(), admin.getPassword());
            if (isAuthenticated) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add a new user
    @PostMapping("/add-user")
    public Admin addUser(@RequestBody Admin admin) {
        return adminService.register(admin);
    }

    // Get user by userId
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Admin> getUser(@PathVariable String userId) {
        try {
            Admin user = adminService.getUserById(userId);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update user by userId
 // Update user details by username
    @PutMapping("/update-user-by-username/{username}")
    public ResponseEntity<Admin> updateUserByUsername(@PathVariable String username, @RequestBody Admin updatedAdmin) {
        try {
            Admin updatedUser = adminService.updateUserByUsername(username, updatedAdmin);

            if (updatedUser == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // User not found
            }

            return new ResponseEntity<>(updatedUser, HttpStatus.OK); // User updated successfully
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle server error
        }
    }


    // Delete user by ID
 // Delete user by ID
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            boolean isDeleted = adminService.deleteUserById(id);
            if (isDeleted) {
                return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // View all users
    @GetMapping("/view-users")
    public List<Admin> getAllUsers() {
        return adminService.getAllUsers();
    }
}