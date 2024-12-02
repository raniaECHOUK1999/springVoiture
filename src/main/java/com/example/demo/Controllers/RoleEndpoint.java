package com.example.demo.Controllers;

import com.example.demo.Models.Role;
import com.example.demo.Models.Voiture;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Interface exposant les endpoints REST pour la gestion des rôles.
 */
public interface RoleEndpoint {

    /**
     * Crée un rôle.
     * @param role Le rôle à créer.
     * @return La vue de confirmation ou erreur.
     */


    /**
     * Récupère un rôle par son ID.
     * @param id L'ID du rôle.
     * @return La vue du rôle.
     */

    @GetMapping("/roles/{id}")
     String getRoleById(@PathVariable Long id, Model model);
    /**
     * Récupère tous les rôles.
     * @return La vue avec la liste des rôles.
     */

    @GetMapping("/listRoles")
     String listRoles(Model model);

    /**
     * Met à jour un rôle existant.
     * @param id L'ID du rôle à mettre à jour.
     * @param role Le rôle mis à jour.
     * @return Redirige vers la liste des rôles.
     */

    @PostMapping("/roles/{id}/update")
     String updateRole(@PathVariable Long id, @ModelAttribute Role role, Model model);

    /**
     * Supprime un rôle par son ID.
     * @param id L'ID du rôle à supprimer.
     * @param redirectAttributes Les attributs pour le message de confirmation.
     * @return Redirige vers la liste des rôles.
     */

    @GetMapping("/roles/{id}/delete")
     String deleteRole(@PathVariable Long id, RedirectAttributes redirectAttributes);

    /**
     * Affiche le formulaire d'ajout d'un rôle.
     * @return La vue du formulaire.
     */
    @PostMapping("/roles/add")
    String addRole(@ModelAttribute Role role, Model model) ;

    @GetMapping("/roles/add")
    String showAddRoleForm(Model model);
}
