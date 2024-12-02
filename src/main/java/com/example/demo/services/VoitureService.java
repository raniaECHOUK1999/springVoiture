package com.example.demo.services;

import com.example.demo.Models.Voiture;
import java.util.List;

public interface VoitureService {

    // Méthode pour sauvegarder une voiture
    Voiture saveVoiture(Voiture v);

    // Méthode pour mettre à jour une voiture existante
    Voiture updateVoiture(Voiture v);

    // Méthode pour supprimer une voiture
    void deleteVoiture(Voiture v);

    // Méthode pour supprimer une voiture par son ID
    void deleteVoitureById(Long id);

    // Méthode pour récupérer une voiture par son ID
    Voiture getVoiture(Long id);

    // Méthode pour récupérer toutes les voitures
    List<Voiture> getAllVoiture();

    // Méthode pour rechercher les voitures par marque
    List<Voiture> findByMarque(String marquev);

    // Méthode pour rechercher les voitures dont la marque contient une chaîne spécifique
    List<Voiture> findByMarqueContains(String marquev);

    // Méthode pour rechercher des voitures par marque et prix
    List<Voiture> findByMarqueAndPrixGreaterThan(String marque, Double prix);

    // Méthode pour trier les voitures par marque par ordre croissant
    List<Voiture> findByOrderByMarqueAsc();

    // Méthode pour trier les voitures par marque (ascendante) et prix (descendante)
    List<Voiture> trierMarquePrix();

    // Méthode pour rechercher les voitures par couleur
    List<Voiture> findByCouleur(String couleur);

    // Méthode pour rechercher les voitures d'une catégorie spécifique
    List<Voiture> findByCategorieId(Long categorieId);

    // Méthode pour rechercher des voitures dont le prix est supérieur à un certain montant
    List<Voiture> findByPrixGreaterThan(Double prix);
}
