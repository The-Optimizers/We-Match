package com.example.wematch.repositories;

import com.example.wematch.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
//    List<Role> findByRoleId(Long role_id);
}