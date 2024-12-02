package com.example.demo.repositories;

import com.example.demo.Models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    // Trouver une option par son nom
    Option findByNom(String nom);


    @Query("SELECT v.options FROM Voiture v WHERE v.idVoiture = :idVoiture")
    List<Option> findOptionsByVoitureId(@Param("idVoiture") Long idVoiture);

    // Trouver toutes les options disponibles pour une voiture
    List<Option> findByPrixGreaterThan(Long price);
}
