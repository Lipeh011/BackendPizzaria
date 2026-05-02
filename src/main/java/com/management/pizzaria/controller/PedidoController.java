package com.management.pizzaria.controller;

import com.management.pizzaria.model.Pedido;
import com.management.pizzaria.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;
    @Operation(summary = "Ver os pedidos", description = "Mostrar os pedidos dos clientes")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> find(@PathVariable Long id) {
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Exclui um pedido", description = "Remove o pedido e seus itens/pagamentos em cascata")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}