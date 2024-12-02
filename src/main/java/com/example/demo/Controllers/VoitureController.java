package com.example.demo.Controllers;

import com.example.demo.Models.Categorie;
import com.example.demo.Models.Marque;
import com.example.demo.Models.Option;
import com.example.demo.Models.Voiture;
import com.example.demo.services.CategorieService;
import com.example.demo.services.MarqueService;
import com.example.demo.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.demo.services.VoitureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class VoitureController implements VoitureEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(VoitureController.class);

    @Autowired
    private VoitureService voitureService;

    @Autowired
    private CategorieService categorieService;
    @Autowired
    private OptionService optionService;

    @Autowired
    private MarqueService marqueService;

    /**
     * Crée une voiture.
     * @param voiture La voiture à créer.
     * @return La voiture créée.
     */
    @Override
    public Voiture createVoiture(@RequestBody Voiture voiture) {
        logger.info("Received request to create Voiture: {}", voiture);
        try {
            return voitureService.saveVoiture(voiture);
        } catch (Exception e) {
            logger.error("Error while creating Voiture: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating Voiture", e);
        }
    }

    /**
     * Récupère une voiture par son ID.
     * @param id L'ID de la voiture.
     * @return La voiture correspondant à l'ID.
     */
    @Override
    public String getVoitureById(@PathVariable Long id, Model model) {
        logger.info("Received request to get Voiture with ID: {}", id);
        try {
            Voiture voiture = voitureService.getVoiture(id);
            model.addAttribute("voiture", voiture);
            List<Option> options = optionService.findByIdVoiture(id);
            model.addAttribute("options", options);

            return "viewVoiture";
        } catch (Exception e) {
            logger.error("Error while retrieving Voiture: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voiture not found", e);
        }
    }

    /**
     * Récupère toutes les voitures.
     * @return La liste de toutes les voitures.
     */
    @Override
    public String listeVoitures(Model model) {
        logger.info("Received request to get all Voitures.");
        try {
            // Récupérer toutes les voitures via le service
            List<Voiture> voitures = voitureService.getAllVoiture();
            model.addAttribute("voitures", voitures); // Ajouter la liste des voitures au modèle
            return "listVoitures"; // Le nom du template HTML
        } catch (Exception e) {
            logger.error("Error while retrieving all Voitures: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving all Voitures", e);
        }
    }

    /**
     * Met à jour une voiture existante.
     * @param id L'ID de la voiture à mettre à jour.
     * @param voiture La voiture mise à jour.
     * @return La voiture mise à jour.
     */
    @Override
    public String updateVoiture(@PathVariable Long id, @ModelAttribute Voiture voiture) {
        logger.info("Received request to update Voiture with ID: {}", id);
        try {
            voiture.setIdVoiture(id);
            voiture = voitureService.updateVoiture(voiture);
            return "redirect:/listVoitures";
        } catch (Exception e) {
            logger.error("Error while updating Voiture: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voiture not found", e);
        }
    }

    @Override
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Voiture voiture = voitureService.getVoiture(id); // Récupérez la voiture depuis la base
        // Récupère toutes les catégories pour les afficher dans le menu déroulant
        List<Categorie> categories = categorieService.findAll();
        List<Marque> marques = marqueService.findAll();
        List<Option> options = optionService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("options", options);
        model.addAttribute("voiture", voiture);
        model.addAttribute("categories", categories);
        model.addAttribute("marques", marques);
        return "editVoiture"; // Nom du template Thymeleaf pour la modification
    }

    /**
     * Supprime une voiture par son ID.
     * @param id L'ID de la voiture à supprimer.
     */
    @Override
    public String deleteVoiture(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            voitureService.deleteVoitureById(id);
            redirectAttributes.addFlashAttribute("message", "Voiture supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la voiture.");
        }
        return "redirect:/listVoitures";
    }

    /**
     * Recherche des voitures par marque.
     * @param marque La marque de la voiture.
     * @return Liste des voitures correspondant à la marque.
     */
    @Override
    public List<Voiture> getVoitureByMarque(@PathVariable String marque) {
        logger.info("Received request to get Voitures by marque: {}", marque);
        try {
            return voitureService.findByMarque(marque);
        } catch (Exception e) {
            logger.error("Error while retrieving Voitures by marque: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Voitures by marque", e);
        }
    }

    /**
     * Recherche des voitures par couleur.
     * @param couleur La couleur de la voiture.
     * @return Liste des voitures correspondant à la couleur.
     */
    @Override
    public List<Voiture> getVoitureByCouleur(@PathVariable String couleur) {
        logger.info("Received request to get Voitures by couleur: {}", couleur);
        try {
            return voitureService.findByCouleur(couleur);
        } catch (Exception e) {
            logger.error("Error while retrieving Voitures by couleur: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Voitures by couleur", e);
        }
    }

    /**
     * Trier les voitures par marque et prix.
     * @return Liste des voitures triées.
     */
    @Override
    public List<Voiture> trierVoituresParMarqueEtPrix() {
        logger.info("Received request to sort Voitures by marque and price.");
        try {
            return voitureService.trierMarquePrix();
        } catch (Exception e) {
            logger.error("Error while sorting Voitures: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sorting Voitures", e);
        }
    }

    @Override
    public String showAddVoitureForm(Model model) {
        // Ajoute une voiture vide pour le formulaire
        model.addAttribute("voiture", new Voiture());

        // Récupère toutes les catégories pour les afficher dans le menu déroulant
        List<Categorie> categories = categorieService.findAll();
        List<Option> options = optionService.findAll();
        List<Marque> marques = marqueService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("options", options);
        model.addAttribute("marques", marques);

        return "addVoiture";
    }

    @Override
    public String addVoiture(@ModelAttribute Voiture voiture,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             Model model) {
        try {
            // Sauvegarder l'image et obtenir le nom de fichier
            String imageName = saveImage(imageFile);

            // Assignation du nom du fichier à l'attribut image de la voiture
            voiture.setImage(imageName);

            // Sauvegarder la voiture dans la base de données
            voitureService.saveVoiture(voiture);

            return "redirect:/listVoitures";  // Rediriger vers la liste des voitures
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'ajout de la voiture.");
            return "error";  // Afficher la page d'erreur si une exception se produit
        }
    }

    // Méthode pour sauvegarder l'image sur le serveur
    private String saveImage(MultipartFile imageFile) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path path = Paths.get("images/" + fileName);

        // Vérification si le répertoire existe, sinon créer le répertoire
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        Files.copy(imageFile.getInputStream(), path);
        return fileName;
    }

}
