package com.management.pizzaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_entrega")
public class Entrega implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Long id; // Note que NÃO TEM @GeneratedValue. O ID não é gerado aqui.

    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String observacao; // Ex: "Interfone quebrado"

    @JsonIgnore // Evita loop infinito no JSON (Entrega chama Pedido que chama Entrega...)
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId // Para mapear uma entidade para outra, compartilhando a mesma chave primária ele serve para 1:1
    private Pedido pedido;
}