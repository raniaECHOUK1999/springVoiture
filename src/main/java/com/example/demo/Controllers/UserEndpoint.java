package com.example.demo.Controllers;

import com.example.demo.Models.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public interface UserEndpoint {


    @GetMapping(value = "/users/add" , produces = "application/json")
    String showAddUserForm(Model model);

    /**
     * Crée une nouvelle User.
     * @param User L'User à créer.
     * @return L'User créée.
     */
    @PostMapping(value = "/users/add", produces = "application/json")
    public String createUser(@ModelAttribute User User) ;
    /**
     * Récupère une User par son ID.
     * @param id L'ID de l'User.
     * @return L'User avec l'ID spécifié.
     */
    @GetMapping(value = "/Users/{id}", produces = "application/json")
    User getUserById(@PathVariable("id") Long id);

    /**
     * Récupère toutes les Users.
     * @return La liste de toutes les Users.
     */
    @GetMapping(value = "/listUsers", produces = "application/json")
    String getAllUsers(Model model);

    /**
     * Met à jour une User existante.
     * @param id L'ID de l'User à mettre à jour.
     * @param User L'User mise à jour.
     * @return L'User mise à jour.
     */
    @PostMapping("/users/edit/{id}")
    String updateUser(@PathVariable Long id, @ModelAttribute User User);

    @GetMapping("/users/edit/{id}")
    String showEditForm(@PathVariable("id") Long id, Model model);

    /**
     * Supprime une User par son ID.
     * @param id L'ID de l'User à supprimer.
     */
    @GetMapping("/users/delete/{id}")
    String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes);
}
