package com.example.demo.services;

import com.example.demo.Models.Marque;
import com.example.demo.repositories.MarqueRepository;
import com.example.demo.services.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarqueServiceImpl implements MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    @Override
    public List<Marque> findAll() {
        return marqueRepository.findAll();
    }

    @Override
    public Marque findById(Long id) {
        Optional<Marque> marque = marqueRepository.findById(id);
        return marque.orElse(null);
    }

    @Override
    public Marque save(Marque marque) {
        return marqueRepository.save(marque);
    }

    @Override
    public void delete(Long id) {
        marqueRepository.deleteById(id);
    }
}
