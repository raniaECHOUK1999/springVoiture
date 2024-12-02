package com.example.demo.services;

import com.example.demo.Models.Voiture;
import com.example.demo.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoitureServiceImpl implements VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Override
    public Voiture saveVoiture(Voiture v) {
        return voitureRepository.save(v);
    }

    @Override
    public Voiture updateVoiture(Voiture v) {
        return voitureRepository.save(v);  // Le save() de JPA va aussi gérer la mise à jour si l'objet existe déjà.
    }

    @Override
    public void deleteVoiture(Voiture v) {
        voitureRepository.delete(v);
    }

    @Override
    public void deleteVoitureById(Long id) {
        voitureRepository.deleteById(id);
    }

    @Override
    public Voiture getVoiture(Long id) {
        return voitureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Voiture> getAllVoiture() {
        return voitureRepository.findAll();
    }

    @Override
    public List<Voiture> findByMarque(String marquev) {
        return null;
    }

    @Override
    public List<Voiture> findByMarqueContains(String marquev) {
        return voitureRepository.findByMarqueContains(marquev);
    }

    @Override
    public List<Voiture> findByMarqueAndPrixGreaterThan(String marque, Double prix) {
        return voitureRepository.findByMarquePrix(marque, prix);
    }

    @Override
    public List<Voiture> findByOrderByMarqueAsc() {
        return voitureRepository.findByOrderByMarqueAsc();
    }

    @Override
    public List<Voiture> trierMarquePrix() {
        return voitureRepository.trierMarquePrix();
    }

    @Override
    public List<Voiture> findByCouleur(String couleur) {
        return voitureRepository.findByCouleur(couleur);
    }

    @Override
    public List<Voiture> findByCategorieId(Long categorieId) {
        return voitureRepository.findByCategorieIdCtg(categorieId);
    }

    @Override
    public List<Voiture> findByPrixGreaterThan(Double prix) {
        return voitureRepository.findVoitureByPriceGreaterThan(prix);
    }
}
