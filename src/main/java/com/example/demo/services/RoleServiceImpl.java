package com.example.demo.services;

import com.example.demo.Models.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository RoleRepository;

    @Override
    public List<Role> findAll() {
        return RoleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return RoleRepository.findById(id);
    }

    @Override
    public Role save(Role Role) {
        return RoleRepository.save(Role);
    }

    @Override
    public void delete(Long id) {
        RoleRepository.deleteById(id);
    }
}
