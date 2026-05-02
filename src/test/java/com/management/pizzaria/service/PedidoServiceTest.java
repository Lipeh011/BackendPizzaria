package com.management.pizzaria.service;

import com.management.pizzaria.model.Pedido;
import com.management.pizzaria.repository.PedidoRepository;
import com.management.pizzaria.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @InjectMocks
    private PedidoService service;

    @Mock
    private PedidoRepository repo;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedido = new Pedido();
        pedido.setId(1L);
    }

    @Test
    void findShouldReturnPedidoWhenFound() {
        when(repo.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido result = service.find(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findShouldThrowExceptionWhenNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.find(1L));
    }

    @Test
    void deleteShouldCallRepositoryDelete() {
        when(repo.findById(1L)).thenReturn(Optional.of(pedido));
        doNothing().when(repo).deleteById(1L);

        service.delete(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}
