package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController implements UserEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // Afficher la page d'ajout de l'option (le formulaire)
    @Override
    public String showAddUserForm(Model model) {
            model.addAttribute("roles", roleService.findAll());  // Récupère tous les rôles pour l'affichage
            model.addAttribute("user", new User());  // Ajoute un objet User vide au modèle
            return "addUser";  // Le nom du fichier HTML pour le formulaire
        }

    /**
     * Crée une nouvelle User.
     *
     * @param User L'User à créer.
     * @return L'User créée.
     */
    @Override
    public String createUser(@ModelAttribute User User) {
        logger.info("Received request to create User: {}", User);
        try {
            userService.save(User); // Sauvegarde l'User
            return "redirect:/listUsers"; // Redirige vers la liste des Users après l'ajout
        } catch (Exception e) {
            logger.error("Error while creating User: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating User", e);
        }
    }

    /**
     * Récupère une User par son ID.
     *
     * @param id L'ID de l'User.
     * @return L'User avec l'ID spécifié.
     */
    @Override
    public User getUserById(@PathVariable Long id) {
        logger.info("Received request to get User with ID: {}", id);
        try {
            return userService.findById(id);
        } catch (Exception e) {
            logger.error("Error while retrieving User: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    /**
     * Récupère toutes les Users.
     *
     * @return La liste de toutes les Users.
     */
    @Override
    public String getAllUsers(Model model) {
        logger.info("Received request to get all Users.");
        try {
            // Récupérer toutes les voitures via le service
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
            return "listUsers"; // Le nom du template HTML
        } catch (Exception e) {
            logger.error("Error while retrieving all Users: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving all Users", e);
        }
    }

    /**
     * Supprime une User par son ID.
     *
     * @param id L'ID de l'User à supprimer.
     */
    @Override
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "User supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la User.");
        }
        return "redirect:/listUsers";
    }

    /**
     * Met à jour une voiture existante.
     * @param id L'ID de la voiture à mettre à jour.
     * @param user La User mise à jour.
     * @return La voiture mise à jour.
     */
    @Override
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        logger.info("Received request to update user with ID: {}", id);
        try {
            user.setIdUser(id);
            user = userService.save(user);
            return "redirect:/listUsers";
        } catch (Exception e) {
            logger.error("Error while updating User: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @Override
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);  // Récupère l'utilisateur à partir de l'ID
        model.addAttribute("user", user);  // Passe l'utilisateur au modèle
        model.addAttribute("roles", roleService.findAll());  // Passe la liste des rôles au modèle
        return "editUser";  // Nom de la vue Thymeleaf
    }

}
