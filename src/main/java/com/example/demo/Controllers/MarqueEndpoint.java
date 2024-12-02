package com.example.demo.Controllers;

import com.example.demo.Models.Categorie;
import com.example.demo.Models.Marque;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


public interface MarqueEndpoint {

    /**
     * Crée une nouvelle marque.
     *
     * @param marque La marque à créer.
     * @return La marque créée.
     */
    @PostMapping("/marques/add")
    String createMarque(@ModelAttribute Marque marque);

    /**
     * Récupère une marque par son ID.
     *
     * @param id L'ID de la marque.
     * @return La marque avec l'ID spécifié.
     */
    @GetMapping(value = "/marques/{id}", produces = "application/json")
    Marque getMarqueById(@PathVariable("id") Long id);

    /**
     * Récupère toutes les marques.
     *
     * @return La liste de toutes les marques.
     */
    @GetMapping("/listMarques")
    String getAllMarques(Model model);

    /**
     * Met à jour une marque existante.
     *
     * @param id     L'ID de la marque à mettre à jour.
     * @param marque La Marque mise à jour.
     * @return La marque mise à jour.
     */
    @PostMapping("/marques/edit/{id}")
    String updateMarque(@PathVariable Long id, @ModelAttribute Marque marque);

    @GetMapping("/marques/edit/{id}")
    String showEditForm(@PathVariable("id") Long id, Model model);


    /**
     * Supprime une marque par son ID.
     *
     * @param id L'ID de la marque à supprimer.
     */
    @GetMapping("/marques/delete/{id}")
    String deleteMarque(@PathVariable("id") Long id, RedirectAttributes redirectAttributes);

    @GetMapping("/marques/add")
    String showAddMarqueForm(Model model);
}
