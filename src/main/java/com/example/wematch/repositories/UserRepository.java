package com.example.wematch.repositories;

import com.example.wematch.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
     Users findByUsername(String username);
     public Users findByEmail(String email);

     public Users findByResetPasswordToken(String token);
}