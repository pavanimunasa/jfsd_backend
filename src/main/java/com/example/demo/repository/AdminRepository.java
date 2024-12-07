package com.example.demo.repository;

import com.example.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Find admin by username (which is unique in this case)
    Optional<Admin> findByUsername(String username);

    // You can add more custom queries if needed
    // Example: Optional<Admin> findByEmail(String email);
}
