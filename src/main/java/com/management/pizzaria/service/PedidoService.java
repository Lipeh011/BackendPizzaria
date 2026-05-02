package com.management.pizzaria.service;

import com.management.pizzaria.model.Pedido;
import com.management.pizzaria.repository.PedidoRepository;
import com.management.pizzaria.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido find(Long id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    // Método DELETE
    public void delete(Long id) {
        find(id); // Verifica se existe antes de tentar apagar. Se não existir, lança exceção 404.
        repo.deleteById(id);
    }
}