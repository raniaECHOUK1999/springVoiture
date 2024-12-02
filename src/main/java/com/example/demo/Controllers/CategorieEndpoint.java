package com.example.demo.Controllers;

import com.example.demo.Models.Categorie;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

public interface CategorieEndpoint {

    /**
     * Crée une nouvelle catégorie.
     * @param categorie La catégorie à créer.
     * @return La catégorie créée.
     */
    @PostMapping("/categories/add")
    String createCategorie(@ModelAttribute Categorie categorie);
    /**
     * Récupère une catégorie par son ID.
     * @param id L'ID de la catégorie.
     * @return La catégorie avec l'ID spécifié.
     */
    @GetMapping(value = "/categories/{id}", produces = "application/json")
    Categorie getCategorieById(@PathVariable("id") Long id);

    /**
     * Récupère toutes les catégories.
     * @return La liste de toutes les catégories.
     */
    @GetMapping(value = "/ListCategories", produces = "application/json")
    String getAllCategories(Model model);

    /**
     * Met à jour une catégorie existante.
     * @param id L'ID de la catégorie à mettre à jour.
     * @param categorie La catégorie avec les nouvelles informations.
     * @return La catégorie mise à jour.
     */
    @PostMapping("/categories/edit/{id}")
    String updateCategorie(@PathVariable Long id, @ModelAttribute Categorie categorie);

    @GetMapping("/categories/edit/{id}")
    String showEditForm(@PathVariable("id") Long id, Model model);

        /**
         * Supprime une catégorie par son ID.
         * @param id L'ID de la catégorie à supprimer.
         */
    @GetMapping("/categories/delete/{id}")
    String deleteCategorie(@PathVariable("id") Long id, RedirectAttributes redirectAttributes);

    @GetMapping("/categories/add")
    String showAddCategoryForm(Model model);
}
