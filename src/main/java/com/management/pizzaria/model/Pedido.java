package com.management.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime instante; // Data e hora do pedido

    // Enum é melhor, mas vamos usar Integer para simplificar por enquanto (1=Aberto, 2=Pago...)
    private Integer status;

    // Relação N:1 (Muitos pedidos para Um cliente)
    // Cumprindo o Requesito do orlando pede essa relação explicitamente!
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    // Relação 1:N (Um pedido tem Muitos itens)
    @OneToMany(mappedBy = "id.pedido") // Mapeado pelo atributo 'pedido' que está dentro do 'id' do ItemPedido
    private List<ItemPedido> itens = new ArrayList<>();

    // Relação 1:1 (Um pedido tem UMA entrega)
    // cascade = CascadeType.ALL significa: Se eu salvar o Pedido, salva a Entrega junto automaticamente.
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Entrega entrega;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    // Método auxiliar para calcular total
    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido ip : itens) {
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }
}