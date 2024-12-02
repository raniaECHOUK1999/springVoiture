package com.example.demo.repositories;

import com.example.demo.Models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    // Trouver une catégorie par son nom
    Categorie findByNom(String nom);

    // Trouver toutes les catégories par leur description
    List<Categorie> findByDescriptionContaining(String keyword);
}
