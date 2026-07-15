package com.example.MyWebsite.repository.EmployeeManagement;
import com.example.MyWebsite.entites.EmployeeManagement.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}