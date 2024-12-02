package com.example.demo.repositories;

import com.example.demo.Models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    // Recherche par marque contenant un mot-clé (sensible à la casse)
    @Query(value = "SELECT * FROM voiture v WHERE v.marque_id LIKE %:marque%", nativeQuery = true)
    List<Voiture> findByMarqueContains(@Param("marque") String marque);

    // Recherche par marque et prix supérieur à un certain montant (avec LIKE et >)
    @Query(value = "SELECT * FROM voiture v JOIN marque m ON v.marque_id = m.idMrq WHERE m.name LIKE %:marquev% AND v.prix > :prixv", nativeQuery = true)
    List<Voiture> findByMarquePrix(@Param("marquev") String marque, @Param("prixv") Double prix);

    // Trier les voitures par marque par ordre croissant
    @Query(value = "SELECT * FROM voiture v ORDER BY v.marque_id ASC", nativeQuery = true)
    List<Voiture> findByOrderByMarqueAsc();

    // Trier les voitures par marque (ASC) puis prix (DESC)
    @Query(value = "SELECT * FROM voiture v JOIN marque m ON v.marque_id = m.idMrq ORDER BY m.name ASC, v.prix DESC", nativeQuery = true)
    List<Voiture> trierMarquePrix();

    // Recherche par couleur
    @Query(value = "SELECT * FROM voiture v WHERE v.couleur = :couleur", nativeQuery = true)
    List<Voiture> findByCouleur(@Param("couleur") String couleur);

    // Recherche par catégorie (ID de catégorie)
    @Query(value = "SELECT * FROM voiture v WHERE v.idCtg = :categorieId", nativeQuery = true)
    List<Voiture> findByCategorieIdCtg(@Param("categorieId") Long categorieId);

    // Recherche de voitures dont le prix est supérieur à un montant donné
    @Query(value = "SELECT * FROM voiture v WHERE v.prix > :price", nativeQuery = true)
    List<Voiture> findVoitureByPriceGreaterThan(@Param("price") Double price);

    // Recherche de voitures dans une fourchette de prix (prix minimum et maximum)
    @Query(value = "SELECT * FROM voiture v WHERE v.prix BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    List<Voiture> findByPrixBetween(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    // Recherche de voitures dont la marque commence par une lettre spécifique
    @Query(value = "SELECT * FROM voiture v JOIN marque m ON v.marque_id = m.idMrq WHERE m.name LIKE :letter%", nativeQuery = true)
    List<Voiture> findByMarqueStartsWith(@Param("letter") String letter);

    // Recherche de voitures dont la marque se termine par une lettre spécifique
    @Query(value = "SELECT * FROM voiture v JOIN marque m ON v.marque_id = m.idMrq WHERE m.name LIKE %:letter", nativeQuery = true)
    List<Voiture> findByMarqueEndsWith(@Param("letter") String letter);
}
