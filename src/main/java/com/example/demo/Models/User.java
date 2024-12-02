package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant un utilisateur du système.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser; // Identifiant unique de l'utilisateur.

    private String name; // Nom de l'utilisateur.

    private String email; // Email de l'utilisateur.

    private String phone; // Numéro de téléphone de l'utilisateur.

    private Integer age; // Âge de l'utilisateur.

    private String password; // Mot de passe de l'utilisateur (à sécuriser).

    @ManyToOne
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    private Role role;


}
