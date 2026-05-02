package com.management.pizzaria.model.pk;

import com.management.pizzaria.model.Pedido;
import com.management.pizzaria.model.Pizza;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode // Essencial para chaves compostas funcionarem, com ele o equals() e hashCode()
@Embeddable // Avisa o JPA: "Isso aqui não é uma tabela, é parte de uma chave"
public class ItemPedidoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

}