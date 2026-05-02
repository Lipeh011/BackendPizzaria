package com.management.pizzaria.controller;

import com.management.pizzaria.dto.PizzaDTO;
import com.management.pizzaria.model.Pizza;
import com.management.pizzaria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService service;

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> findAll() {
        List<Pizza> list = service.findAll();
        List<PizzaDTO> listDto = list.stream()
                .map(obj -> new PizzaDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}