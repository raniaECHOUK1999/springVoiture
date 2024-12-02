package com.example.demo.Controllers;

import com.example.demo.Models.Role;
import com.example.demo.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RoleController implements RoleEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * Crée un rôle.
     * @param role Le rôle à créer.
     * @return La vue de confirmation ou erreur.
     */
    @Override
    public String addRole(@ModelAttribute Role role, Model model) {
        try {
            roleService.save(role);
            return "redirect:/listRoles";  // Redirige vers la liste des rôles
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'ajout du rôle.");
            return "error";  // Retourne une page d'erreur si une exception se produit
        }
    }

    /**
     * Récupère un rôle par son ID.
     * @param id L'ID du rôle.
     * @return La vue du rôle.
     */
    @Override
    public String getRoleById(@PathVariable Long id, Model model) {
        logger.info("Received request to get Role with ID: {}", id);
        try {
            Role role = roleService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
            model.addAttribute("role", role);
            return "viewRole"; // Vue pour afficher le rôle
        } catch (Exception e) {
            logger.error("Error while retrieving Role: ", e);
            model.addAttribute("error", "Role not found");
            return "error"; // Affiche la page d'erreur
        }
    }

    /**
     * Récupère tous les rôles.
     * @return La vue avec la liste des rôles.
     */
    @Override
    public String listRoles(Model model) {
        logger.info("Received request to get all Roles.");
        try {
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            return "listRoles"; // Vue avec la liste des rôles
        } catch (Exception e) {
            logger.error("Error while retrieving all Roles: ", e);
            model.addAttribute("error", "Error retrieving all Roles");
            return "error"; // Affiche la page d'erreur
        }
    }

    /**
     * Met à jour un rôle existant.
     * @param id L'ID du rôle à mettre à jour.
     * @param role Le rôle mis à jour.
     * @return Redirige vers la liste des rôles.
     */
    @Override
    public String updateRole(@PathVariable Long id, @ModelAttribute Role role, Model model) {
        logger.info("Received request to update Role with ID: {}", id);
        try {
            role.setIdRole(id);
            roleService.save(role);
            return "redirect:/listRoles"; // Redirige vers la liste des rôles
        } catch (Exception e) {
            logger.error("Error while updating Role: ", e);
            model.addAttribute("error", "Error updating Role");
            return "error"; // Affiche la page d'erreur
        }
    }

    /**
     * Supprime un rôle par son ID.
     * @param id L'ID du rôle à supprimer.
     * @param redirectAttributes Les attributs pour le message de confirmation.
     * @return Redirige vers la liste des rôles.
     */
    @Override
    public String deleteRole(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logger.info("Received request to delete Role with ID: {}", id);
        try {
            roleService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Role deleted successfully!");
            return "redirect:/listRoles"; // Redirige vers la liste des rôles
        } catch (Exception e) {
            logger.error("Error while deleting Role: ", e);
            redirectAttributes.addFlashAttribute("error", "Error deleting Role");
            return "redirect:/listRoles"; // Redirige vers la liste des rôles avec erreur
        }
    }

    /**
     * Affiche le formulaire d'ajout d'un rôle.
     * @return La vue du formulaire.
     */
    @Override
    public String showAddRoleForm(Model model) {
        // Crée un objet Role vide pour le formulaire
        model.addAttribute("role", new Role());
        return "addRole"; // Retourne la vue du formulaire
    }
}
