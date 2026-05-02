package com.management.pizzaria.service;

import com.management.pizzaria.model.Cliente;
import com.management.pizzaria.repository.ClienteRepository;
import com.management.pizzaria.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Long id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    // CREATE
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    // UPDATE
    public Cliente update(Cliente obj) {
        // Verifica se existe antes de tentar atualizar
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj); // Atualiza apenas os dados permitidos
        return repo.save(newObj);
    }

    // DELETE
    public void delete(Long id) {
        find(id); // Verifica se existe
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Se tentar apagar um cliente que tem pedidos, o banco bloqueia. Tratamos aqui.
            throw new RuntimeException("Não é possível excluir porque há pedidos relacionados");
        }
    }

    // Método auxiliar para atualizar apenas nome e email (não deixamos mudar ID ou CPF se tivesse)
    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setTelefone(obj.getTelefone());
    }
}