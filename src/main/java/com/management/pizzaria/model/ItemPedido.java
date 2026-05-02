package com.management.pizzaria.model;

import com.management.pizzaria.model.pk.ItemPedidoPK;
import com.fasterxml.jackson.annotation.JsonIgnore; // <--- Importante
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {

    @JsonIgnore
    @EqualsAndHashCode.Include
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Integer quantidade;
    private Double preco;

    // Construtores...
    public ItemPedido(Pedido pedido, Pizza pizza, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setPizza(pizza);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPedido() {
    }

    // Métodos auxiliares

    @JsonIgnore // Ignora o Json para ele nao ficar dando loop
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }


    public Pizza getPizza() {
        return id.getPizza();
    }

    public void setPizza(Pizza pizza) {
        id.setPizza(pizza);
    }

    public double getSubTotal() {
        return (preco * quantidade);
    }
}