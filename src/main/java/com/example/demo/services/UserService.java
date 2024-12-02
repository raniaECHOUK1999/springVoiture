package com.example.demo.services;

import com.example.demo.Models.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void delete(Long id);
}
