package com.management.pizzaria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.pizzaria.model.Pedido;
import com.management.pizzaria.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoRepository repo;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        pedido = new Pedido();
        pedido = repo.save(pedido);
    }

    @Test
    void deveRetornarPedidoPorId() throws Exception {
        mockMvc.perform(get("/pedidos/{id}", pedido.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarPedido() throws Exception {
        mockMvc.perform(delete("/pedidos/{id}", pedido.getId()))
                .andExpect(status().isNoContent());
    }
}
