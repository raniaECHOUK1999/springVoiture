package com.example.demo.repositories;

import com.example.demo.Models.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MarqueRepository extends JpaRepository<Marque, Long> {

    // Trouver une marque par année de création
    Marque findByAnneeDeCreation(String anneeDeCreation);
}
