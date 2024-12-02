package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant une option associée à une voiture.
 * Exemple d'option : "Toit ouvrant", "Sièges chauffants".
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOpt; // Identifiant unique de l'option.

    private String nom; // Nom de l'option (ex : "Toit ouvrant").

    private Long prix; // Prix de l'option (par exemple, un supplément sur le prix de la voiture).

    @ManyToMany(mappedBy = "options")
    private Set<Voiture> voitures = new HashSet<>();
}
