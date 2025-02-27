package com.example.demo.repositories;

import com.example.demo.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(String name);
}
