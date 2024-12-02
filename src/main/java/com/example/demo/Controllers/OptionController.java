package com.example.demo.Controllers;

import com.example.demo.Models.Marque;
import com.example.demo.Models.Option;
import com.example.demo.Models.Voiture;
import com.example.demo.services.OptionService;
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
public class OptionController implements OptionEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(OptionController.class);

    @Autowired
    private OptionService optionService;

    // Afficher la page d'ajout de l'option (le formulaire)
    @Override
    public String showAddOptionForm(Model model) {
        model.addAttribute("option", new Option());  // Ajoute un objet Option vide au modèle
        return "addOption";  // Le nom du fichier HTML pour le formulaire
    }

    /**
     * Crée une nouvelle option.
     *
     * @param option L'option à créer.
     * @return L'option créée.
     */
    @Override
    public String createOption(@ModelAttribute Option option) {
        logger.info("Received request to create option: {}", option);
        try {
            optionService.save(option); // Sauvegarde l'option
            return "redirect:/listOptions"; // Redirige vers la liste des options après l'ajout
        } catch (Exception e) {
            logger.error("Error while creating option: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating option", e);
        }
    }

    /**
     * Récupère une option par son ID.
     *
     * @param id L'ID de l'option.
     * @return L'option avec l'ID spécifié.
     */
    @Override
    public Option getOptionById(@PathVariable Long id) {
        logger.info("Received request to get option with ID: {}", id);
        try {
            return optionService.findById(id);
        } catch (Exception e) {
            logger.error("Error while retrieving option: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Option not found", e);
        }
    }

    /**
     * Récupère toutes les options.
     *
     * @return La liste de toutes les options.
     */
    @Override
    public String getAllOptions(Model model) {
        logger.info("Received request to get all options.");
        try {
            // Récupérer toutes les voitures via le service
            List<Option> options = optionService.findAll();
            model.addAttribute("options", options);
            return "listOptions"; // Le nom du template HTML
        } catch (Exception e) {
            logger.error("Error while retrieving all options: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving all options", e);
        }
    }

    /**
     * Supprime une option par son ID.
     *
     * @param id L'ID de l'option à supprimer.
     */
    @Override
    public String deleteOption(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute("message", "Option supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la Option.");
        }
        return "redirect:/listOptions";
    }

    /**
     * Met à jour une voiture existante.
     * @param id L'ID de la voiture à mettre à jour.
     * @param option La Option mise à jour.
     * @return La voiture mise à jour.
     */
    @Override
    public String updateOption(@PathVariable Long id, @ModelAttribute Option option) {
        logger.info("Received request to update Voiture with ID: {}", id);
        try {
            option.setIdOpt(id);
            option = optionService.save(option);
            return "redirect:/listOptions";
        } catch (Exception e) {
            logger.error("Error while updating option: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Option not found", e);
        }
    }

    @Override
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Option option = optionService.findById(id); // Récupérez depuis la base
        model.addAttribute("option", option);
        return "editOption"; // Nom du template Thymeleaf pour la modification
    }

}
