package com.management.pizzaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    private Integer estado; // 1 = PENDENTE, 2 = QUITADO, 3 = CANCELADO

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId // Usando o MapsID pois um Pagamento pertence estritamente a um Pedido
    private Pedido pedido;
}