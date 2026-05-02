package com.management.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_pizza")
public class Pizza implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex: "Portuguesa", "Marguerita"
    private Double precoBase; // Ex: 35.00

    // N:N (Muitos para Muitos) do requesitos
    @ManyToMany
    @JoinTable(
            name = "tb_pizza_ingrediente", // Nome da tabela oculta que vai ser criada
            joinColumns = @JoinColumn(name = "pizza_id"), // Lado da Pizza
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id") // Lado do Ingrediente
    )
    private List<Ingrediente> ingredientes = new ArrayList<>();
}