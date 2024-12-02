package com.example.demo.Controllers;

import com.example.demo.Models.Categorie;
import com.example.demo.Models.Marque;
import com.example.demo.Models.Option;
import com.example.demo.Models.Voiture;
import com.example.demo.services.CategorieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategorieController implements CategorieEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(CategorieController.class);

    @Autowired
    private CategorieService categorieService;

    /**
     * Crée une nouvelle catégorie.
     * @param categorie La catégorie à créer.
     * @return La catégorie créée.
     */
    @Override
    public String createCategorie(@ModelAttribute Categorie categorie) {
        try {
            categorieService.save(categorie);
            return "redirect:/ListCategories";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating categorie", e);
        }
    }

    @Override
    public String showAddCategoryForm(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "addCategorie";
    }


    /**
     * Récupère une catégorie par son ID.
     * @param id L'ID de la catégorie.
     * @return La catégorie avec l'ID spécifié.
     */
    @Override
    public Categorie getCategorieById(@PathVariable Long id) {
        logger.info("Received request to get categorie with ID: {}", id);
        try {
            return categorieService.findById(id);
        } catch (Exception e) {
            logger.error("Error while retrieving categorie: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie not found", e);
        }
    }

    /**
     * Récupère toutes les catégories.
     * @return La liste de toutes les catégories.
     */
    @Override
    public String getAllCategories(Model model) {
        logger.info("Received request to get all categories.");
        try {
            // Récupérer toutes les voitures via le service
            List<Categorie> categories = categorieService.findAll();
            model.addAttribute("categories", categories);
            return "ListCategories"; // Le nom du template HTML
        } catch (Exception e) {
            logger.error("Error while retrieving all categories: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving all categories", e);
        }
    }

    /**
     * Supprime une catégorie par son ID.
     * @param id L'ID de la catégorie à supprimer.
     */

    @Override
    public String deleteCategorie(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            categorieService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Categorie supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la Categorie.");
        }
        return "redirect:/ListCategories";
    }


    /**
     * Met à jour une voiture existante.
     * @param id L'ID de la voiture à mettre à jour.
     * @param categorie La categorie mise à jour.
     * @return La voiture mise à jour.
     */
    @Override
    public String updateCategorie(@PathVariable Long id, @ModelAttribute Categorie categorie) {
        logger.info("Received request to update Voiture with ID: {}", id);
        try {
            categorie.setIdCtg(id);
            categorie = categorieService.save(categorie);
            return "redirect:/ListCategories";
        } catch (Exception e) {
            logger.error("Error while updating Voiture: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie not found", e);
        }
    }

    @Override
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Categorie categorie = categorieService.findById(id); // Récupérez depuis la base
        model.addAttribute("categorie", categorie);
        return "editCategorie"; // Nom du template Thymeleaf pour la modification
    }

}
