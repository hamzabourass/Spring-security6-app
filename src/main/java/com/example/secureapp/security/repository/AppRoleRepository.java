package com.example.secureapp.security.repository;

import com.example.secureapp.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByName(String name);
}
