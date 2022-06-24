package com.example.t4final.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.t4final.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

Optional<User> findByUsername(String username);
}