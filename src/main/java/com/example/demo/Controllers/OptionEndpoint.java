package com.example.demo.Controllers;

import com.example.demo.Models.Marque;
import com.example.demo.Models.Option;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

public interface OptionEndpoint {

    @GetMapping(value = "/options/add" , produces = "application/json")
    String showAddOptionForm(Model model);

    /**
     * Crée une nouvelle option.
     * @param option L'option à créer.
     * @return L'option créée.
     */
    @PostMapping(value = "/options/add", produces = "application/json")
    public String createOption(@ModelAttribute Option option) ;
    /**
     * Récupère une option par son ID.
     * @param id L'ID de l'option.
     * @return L'option avec l'ID spécifié.
     */
    @GetMapping(value = "/options/{id}", produces = "application/json")
    Option getOptionById(@PathVariable("id") Long id);

    /**
     * Récupère toutes les options.
     * @return La liste de toutes les options.
     */
    @GetMapping(value = "/listOptions", produces = "application/json")
    String getAllOptions(Model model);

    /**
     * Met à jour une option existante.
     * @param id L'ID de l'option à mettre à jour.
     * @param option L'option mise à jour.
     * @return L'option mise à jour.
     */
    @PostMapping("/options/edit/{id}")
    String updateOption(@PathVariable Long id, @ModelAttribute Option option);

    @GetMapping("/options/edit/{id}")
    String showEditForm(@PathVariable("id") Long id, Model model);

    /**
     * Supprime une option par son ID.
     * @param id L'ID de l'option à supprimer.
     */
    @GetMapping("/options/delete/{id}")
    String deleteOption(@PathVariable("id") Long id, RedirectAttributes redirectAttributes);
}
