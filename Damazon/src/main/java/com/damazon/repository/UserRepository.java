package com.damazon.repository;

import com.damazon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by their username
    Optional<User> findByUsername(String username);

    // Check if a username exists in the database
    boolean existsByUsername(String username);
}

