package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Register a new admin
    public Admin register(Admin admin) {
        return adminRepository.save(admin);
    }

    // Authenticate admin login
    public boolean authenticate(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        return admin.isPresent() && admin.get().getPassword().equals(password);
    }

 // Get user by ID
    public Admin getUserById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id); // Correct method call
        return admin.orElse(null);
    }


    // Update user by ID
 // Update user details by username
    public Admin updateUserByUsername(String username, Admin updatedAdmin) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setUsername(updatedAdmin.getUsername());
            admin.setEmail(updatedAdmin.getEmail());
            return adminRepository.save(admin); // Save and return updated admin
        }

        return null; // Return null if user not found
    }


    // Delete user by ID
    public boolean deleteUserById(Long id) {
        try {
            if (adminRepository.existsById(id)) {
                adminRepository.deleteById(id); // Delete user by ID
                return true;
            }
            return false; // User not found
        } catch (Exception e) {
            // Handle exception
            return false;
        }
    }


    // Get all users
    public List<Admin> getAllUsers() {
        return adminRepository.findAll();
    }
}
