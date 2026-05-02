package com.management.pizzaria.repository;

import com.management.pizzaria.model.ItemPedido;
import com.management.pizzaria.model.pk.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}