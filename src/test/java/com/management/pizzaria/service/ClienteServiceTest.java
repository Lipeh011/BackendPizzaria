package com.management.pizzaria.service;

import com.management.pizzaria.model.Cliente;
import com.management.pizzaria.repository.ClienteRepository;
import com.management.pizzaria.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteRepository repo;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Gabriel");
        cliente.setEmail("gabriel@email.com");
        cliente.setTelefone("123456789");
    }

    @Test
    void findShouldReturnClienteWhenFound() {
        when(repo.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente result = service.find(1L);

        assertNotNull(result);
        assertEquals("Gabriel", result.getNome());
    }

    @Test
    void findShouldThrowExceptionWhenNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.find(1L));
    }

    @Test
    void findAllShouldReturnList() {
        when(repo.findAll()).thenReturn(Arrays.asList(cliente));

        List<Cliente> list = service.findAll();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void insertShouldReturnSavedCliente() {
        when(repo.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = service.insert(new Cliente());

        assertNotNull(result);
        assertEquals("Gabriel", result.getNome());
    }

    @Test
    void updateShouldUpdateCliente() {
        Cliente updated = new Cliente();
        updated.setId(1L);
        updated.setNome("Novo Nome");
        updated.setEmail("novo@email.com");
        updated.setTelefone("987654321");

        when(repo.findById(1L)).thenReturn(Optional.of(cliente));
        when(repo.save(any(Cliente.class))).thenReturn(updated);

        Cliente result = service.update(updated);

        assertEquals("Novo Nome", result.getNome());
        assertEquals("novo@email.com", result.getEmail());
    }

    @Test
    void deleteShouldThrowExceptionIfDataIntegrityViolation() {
        doThrow(DataIntegrityViolationException.class).when(repo).deleteById(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(cliente));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertEquals("Não é possível excluir porque há pedidos relacionados", ex.getMessage());
    }
}
