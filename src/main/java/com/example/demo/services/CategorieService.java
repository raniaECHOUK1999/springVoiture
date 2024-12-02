package com.example.demo.services;

import com.example.demo.Models.Categorie;

import java.util.List;

public interface CategorieService {

    List<Categorie> findAll();

    Categorie findById(Long id);

    Categorie save(Categorie categorie);

    void delete(Long id);
}
