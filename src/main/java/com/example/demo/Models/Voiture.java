package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

/**
 * Classe représentant une voiture dans le système.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoiture; // Identifiant unique de la voiture.

    @ManyToOne
    @JoinColumn(name = "idMrq", referencedColumnName = "idMrq")
    private Marque marque;

    private String matricule; // Numéro de matricule de la voiture.

    private Double prix; // Prix de la voiture.

    private String moteur; // Type de moteur de la voiture (ex : "Essence", "Diesel").

    private String couleur; // Couleur de la voiture.

    private String typeIntérieur; // Type d'intérieur de la voiture (ex : "Cuir", "Tissu").

    /**
     * Date de vente de la voiture.
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateVente; // Date de vente de la voiture.

    /**
     * Relation Many-to-One avec Categorie.
     * Chaque voiture appartient à une catégorie.
     */
    @ManyToOne
    @JoinColumn(name = "idCtg", referencedColumnName = "idCtg")
    private Categorie categorie; // Catégorie à laquelle cette voiture appartient.

    private String image;

    private transient MultipartFile imageFile;

    @ManyToMany
    @JoinTable(
            name = "voiture_options",
            joinColumns = @JoinColumn(name = "idVoiture"),
            inverseJoinColumns = @JoinColumn(name = "idOpt")
    )
    private Set<Option> options = new HashSet<>();

    public String getNomImage() {
        if (image != null && image.contains("_")) {
            return image.substring(image.indexOf("_") + 1); // Récupère "images.jpeg" à partir de "1733077324919_images.jpeg"
        }
        return image; // Si pas de préfixe, retourne l'image telle quelle
    }
}
