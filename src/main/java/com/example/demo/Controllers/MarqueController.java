package com.example.demo.Controllers;

import com.example.demo.Models.Marque;
import com.example.demo.services.MarqueService;
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
public class MarqueController implements MarqueEndpoint{

    private static final Logger logger = LoggerFactory.getLogger(MarqueController.class);

    @Autowired
    private MarqueService marqueService;

    // Afficher la page de la liste des marques
    @Override
    public String getAllMarques(Model model) {
        logger.info("Fetching all marques.");
        try {
            List<Marque> marques = marqueService.findAll();
            model.addAttribute("marques", marques);
            return "listMarques";  // Le nom de votre fichier HTML pour la liste
        } catch (Exception e) {
            logger.error("Error while retrieving marques: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving marques", e);
        }
    }


    // Afficher le formulaire pour ajouter une nouvelle marque
    @Override
    public String showAddMarqueForm(Model model) {
        model.addAttribute("marque", new Marque());  // Ajoute un objet Marque vide
        return "addMarque";  // Le nom du fichier HTML pour le formulaire d'ajout
    }

    // Ajouter une nouvelle marque
    @Override
    public String createMarque(@ModelAttribute Marque marque) {
        logger.info("Creating a new marque: {}", marque);
        try {
            marqueService.save(marque);  // Sauvegarder la marque dans la base de données
            return "redirect:/listMarques";  // Rediriger vers la liste des marques après l'ajout
        } catch (Exception e) {
            logger.error("Error while creating marque: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating marque", e);
        }
    }

    @Override
    public Marque getMarqueById(Long id) {
        return marqueService.findById(id);
    }

    // Supprimer une marque par son ID
    @Override
    public String deleteMarque(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute("message", "Marque supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la Marque.");
        }
        return "redirect:/listMarques";
    }



    /**
     * Met à jour une voiture existante.
     * @param id L'ID de la voiture à mettre à jour.
     * @param marque La Marque mise à jour.
     * @return La voiture mise à jour.
     */
    @Override
    public String updateMarque(@PathVariable Long id, @ModelAttribute Marque marque) {
        logger.info("Received request to update Voiture with ID: {}", id);
        try {
            marque.setIdMrq(id);
            marque = marqueService.save(marque);
            return "redirect:/listMarques";
        } catch (Exception e) {
            logger.error("Error while updating Voiture: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "marque not found", e);
        }
    }

    @Override
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Marque marque = marqueService.findById(id); // Récupérez depuis la base
        model.addAttribute("marque", marque);
        return "editMarque"; // Nom du template Thymeleaf pour la modification
    }

}
