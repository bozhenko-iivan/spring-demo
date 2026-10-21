package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query(value = "SELECT * FROM table_name WHERE table_name.email = :email", nativeQuery = true)
    @Query(value = "SELECT u FROM User u Where u.email = :email")
    Optional<User> findByEmail(String email);
}