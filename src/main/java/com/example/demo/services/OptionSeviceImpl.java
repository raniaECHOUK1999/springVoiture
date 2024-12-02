package com.example.demo.services;

import com.example.demo.Models.Option;
import com.example.demo.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionSeviceImpl implements OptionSevice {


    @Autowired
    OptionRepository optionRepository;
    @Override
    public Option saveOption(Option v) {
        return optionRepository.save(v);
    }
    @Override
    public Option updateOption(Option v) {
        return optionRepository.save(v);
    }
    @Override
    public void deleteOption(Option v) {
        optionRepository.delete(v);
    }
    @Override
    public void deleteOptionById(Long id) {
        optionRepository.deleteById(id);
    }
    @Override
    public Option getOption(Long id) {
        return optionRepository.findById(id).get();
    }
    @Override
    public List<Option> getAllOption() {
        return optionRepository.findAll();
    }

}
