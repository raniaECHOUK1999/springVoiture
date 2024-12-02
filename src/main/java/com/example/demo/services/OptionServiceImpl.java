package com.example.demo.services;

import com.example.demo.Models.Option;
import com.example.demo.repositories.OptionRepository;
import com.example.demo.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public Option findById(Long id) {
        Optional<Option> option = optionRepository.findById(id);
        return option.orElse(null);
    }

    @Override
    public List<Option> findByIdVoiture(Long id){
        return optionRepository.findOptionsByVoitureId(id);
    }

    @Override
    public Option save(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public void delete(Long id) {
        optionRepository.deleteById(id);
    }
}
