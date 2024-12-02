package com.example.demo.services;

import com.example.demo.Models.Option;
import java.util.List;

public interface OptionService {

    List<Option> findAll();

    Option findById(Long id);

    List<Option> findByIdVoiture(Long id);
    Option save(Option option);

    void delete(Long id);
}
