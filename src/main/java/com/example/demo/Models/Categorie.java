package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Classe représentant une catégorie de voitures.
 * Les voitures peuvent être organisées par catégorie (par exemple : SUV, berline, etc.).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCtg; // Identifiant unique de la catégorie.

    private String nom; // Nom de la catégorie (ex : SUV, berline).

    private String description; // Description de la catégorie.

    /**
     * Relation One-to-Many avec Voiture.
     * Une catégorie peut contenir plusieurs voitures.
     */
    @OneToMany(mappedBy = "categorie")
    private List<Voiture> voitures; // Liste des voitures appartenant à cette catégorie.
}
