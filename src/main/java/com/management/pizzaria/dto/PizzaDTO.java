package com.management.pizzaria.dto;

import com.management.pizzaria.model.Pizza;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PizzaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Double precoBase;

    public PizzaDTO(Pizza obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.precoBase = obj.getPrecoBase();
    }
}