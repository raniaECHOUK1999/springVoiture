package com.example.demo.services;

import com.example.demo.Models.Option;
import com.example.demo.Models.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll();

    Optional<Role> findById(Long id);

    Role save(Role typeUser);

    void delete(Long id);
}
