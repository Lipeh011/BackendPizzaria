package com.management.pizzaria.controller;

import com.management.pizzaria.model.Pizza;
import com.management.pizzaria.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PizzaRepository repo;

    private Pizza pizza;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        pizza = new Pizza();
        pizza.setNome("Margherita");
        pizza = repo.save(pizza);
    }

    @Test
    void deveRetornarListaDePizzas() throws Exception {
        mockMvc.perform(get("/pizzas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Margherita"));
    }
}
