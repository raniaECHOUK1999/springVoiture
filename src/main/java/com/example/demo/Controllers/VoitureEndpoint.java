package com.example.demo.Controllers;

import com.example.demo.Models.Voiture;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface VoitureEndpoint {

    /**
     * Crée une voiture.
     * @param voiture La voiture à créer.
     * @return La voiture créée.
     */
    @PostMapping(value = "/voiture", produces = "application/json")
    Voiture createVoiture(@RequestBody Voiture voiture);

    @GetMapping("/voitures/add")
    String showAddVoitureForm(Model model);

    @PostMapping("/voitures/add")
    String addVoiture(@ModelAttribute Voiture voiture, @RequestParam("image") MultipartFile image, Model model);

    /**
     * Récupère une voiture par son ID.
     * @param id L'ID de la voiture.
     * @return La voiture correspondant à l'ID.
     */
    @GetMapping(value = "/voiture/{id}", produces = "application/json")
    String getVoitureById(@PathVariable Long id, Model mode);
    /**
     * Récupère toutes les voitures.
     * @return La liste de toutes les voitures.
     */
    @GetMapping(value = "/listVoitures", produces = "application/json")
     String listeVoitures(Model model);
    /**
     * Met à jour une voiture existante.
     * @param id L'ID de la voiture à mettre à jour.
     * @param model La voiture mise à jour.
     * @return La voiture mise à jour.
     */
    @GetMapping("/voitures/edit/{id}")
    String showEditForm(@PathVariable("id") Long id, Model model);

    @PostMapping("/voitures/edit/{id}")
    String updateVoiture(@PathVariable Long id, @ModelAttribute Voiture voiture);
        /**
         * Supprime une voiture par son ID.
         * @param id L'ID de la voiture à supprimer.
         */
        @GetMapping("/voitures/delete/{id}")
        String deleteVoiture(@PathVariable("id") Long id, RedirectAttributes redirectAttributes);

    /**
     * Recherche des voitures par marque.
     * @param marque La marque de la voiture.
     * @return Liste des voitures correspondant à la marque.
     */
    @GetMapping(value = "/voiture/marque/{marque}", produces = "application/json")
    List<Voiture> getVoitureByMarque(@PathVariable("marque") String marque);

    /**
     * Recherche des voitures par couleur.
     * @param couleur La couleur de la voiture.
     * @return Liste des voitures correspondant à la couleur.
     */
    @GetMapping(value = "/voiture/couleur/{couleur}", produces = "application/json")
    List<Voiture> getVoitureByCouleur(@PathVariable("couleur") String couleur);

    /**
     * Trier les voitures par marque et prix.
     * @return Liste des voitures triées.
     */
    @GetMapping(value = "/voiture/trier", produces = "application/json")
    List<Voiture> trierVoituresParMarqueEtPrix();
}
