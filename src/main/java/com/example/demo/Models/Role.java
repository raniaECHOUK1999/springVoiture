package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Classe représentant le type d'utilisateur dans le système (par exemple : "Admin", "Client").
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idRole; // Identifiant unique du type d'utilisateur.

    @Column(length = 20)
    private String name;; // Nom du type d'utilisateur (ex : "Admin", "Client").

  @OneToMany(mappedBy = "role")
  private List<User> users; // Liste des voitures appartenant à cette catégorie.
}
