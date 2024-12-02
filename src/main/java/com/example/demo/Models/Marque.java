package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Classe représentant la marque d'une voiture.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Marque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMrq; // Identifiant unique de la marque.

    private String name;

    private String anneeDeCreation; // Année de création de la marque.

    private String description; // Description de la marque.

    /**
     * Relation One-to-Many avec Voiture.
     * Une catégorie peut contenir plusieurs voitures.
     */
    @OneToMany(mappedBy = "marque")
    private List<Voiture> voitures; // Liste des voitures appartenant à cette catégorie.
}
