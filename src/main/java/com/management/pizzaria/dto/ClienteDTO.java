package com.management.pizzaria.dto;

import com.management.pizzaria.model.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor //  Gera automaticamente um construtor sem argumentos (vazio) para uma classe Java
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;

    // Construtor especial que recebe a Entidade e copia os dados para o DTO
    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        // Não copiamos telefone nem endereço de propósito! Segurança/Filtro.
    }
}