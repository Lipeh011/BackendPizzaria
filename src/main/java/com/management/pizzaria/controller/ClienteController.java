package com.management.pizzaria.controller;

import com.management.pizzaria.model.Cliente;
import com.management.pizzaria.dto.ClienteDTO; // Importante: Importar seu DTO
import com.management.pizzaria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors; // Importante: Importar o Collectors

@RestController
@RequestMapping(value = "/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciar clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    // GET por ID (Traz tudo)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id) {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    // GET Todos (Traz resumido - DTO)
    @GetMapping
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista simplificada (DTO)")
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        // Converte Cliente -> ClienteDTO
        List<ClienteDTO> listDto = list.stream()
                .map(obj -> new ClienteDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    // POST
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Cliente obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Cliente obj, @PathVariable Long id) {
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}