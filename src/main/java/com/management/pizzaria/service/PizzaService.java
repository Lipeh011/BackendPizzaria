package com.management.pizzaria.service;

import com.management.pizzaria.model.Pizza;
import com.management.pizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repo;

    public List<Pizza> findAll() {
        return repo.findAll();
    }
}