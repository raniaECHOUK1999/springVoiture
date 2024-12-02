package com.example.demo.services;

import com.example.demo.Models.Option;

import java.util.List;

public interface OptionSevice {


    Option saveOption(Option v);
    Option updateOption(Option v);
    void deleteOption(Option v);
    void deleteOptionById(Long id);
    Option getOption(Long id);
    List<Option> getAllOption();

}
