package com.example.demo.services;

import com.example.demo.Models.Marque;
import java.util.List;

public interface MarqueService {

    List<Marque> findAll();

    Marque findById(Long id);

    Marque save(Marque marque);

    void delete(Long id);
}
