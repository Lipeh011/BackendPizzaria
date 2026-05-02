package com.management.pizzaria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.pizzaria.model.Cliente;
import com.management.pizzaria.repository.ClienteRepository;
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
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository repo;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        cliente = new Cliente();
        cliente.setNome("Gabriel");
        cliente.setEmail("gabriel@email.com");
        cliente.setTelefone("123456789");
        cliente = repo.save(cliente);
    }

    @Test
    void deveCriarCliente() throws Exception {
        Cliente novo = new Cliente();
        novo.setNome("Novo Cliente");
        novo.setEmail("novo@email.com");
        novo.setTelefone("987654321");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Novo Cliente"));
    }

    @Test
    void deveRetornarClientePorId() throws Exception {
        mockMvc.perform(get("/clientes/{id}", cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gabriel"));
    }

    @Test
    void deveRetornarListaDeClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Gabriel"));
    }

    @Test
    void deveAtualizarCliente() throws Exception {
        cliente.setNome("Atualizado");

        mockMvc.perform(put("/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Atualizado"));
    }

    @Test
    void deveDeletarCliente() throws Exception {
        mockMvc.perform(delete("/clientes/{id}", cliente.getId()))
                .andExpect(status().isNoContent());
    }
}
