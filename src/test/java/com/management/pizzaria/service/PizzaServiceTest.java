package com.management.pizzaria.service;

import com.management.pizzaria.model.Pizza;
import com.management.pizzaria.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceTest {

    @InjectMocks
    private PizzaService service;

    @Mock
    private PizzaRepository repo;

    private Pizza pizza;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pizza = new Pizza();
        pizza.setId(1L);
        pizza.setNome("Margherita");
    }

    @Test
    void findAllShouldReturnList() {
        when(repo.findAll()).thenReturn(Arrays.asList(pizza));

        List<Pizza> list = service.findAll();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }
}
