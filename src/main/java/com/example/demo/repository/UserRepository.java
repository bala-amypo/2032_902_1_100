package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.Repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
