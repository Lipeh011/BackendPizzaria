package com.management.pizzaria.config;

import com.management.pizzaria.model.*;
import com.management.pizzaria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional; // <--- IMPORTANTE

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Instanciacao implements CommandLineRunner {

    @Autowired private TamanhoRepository tamanhoRepository;
    @Autowired private IngredienteRepository ingredienteRepository;
    @Autowired private PizzaRepository pizzaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ItemPedidoRepository itemPedidoRepository;
    @Autowired private EntregaRepository entregaRepository;
    @Autowired private PagamentoRepository pagamentoRepository;

    @Transactional // <--- O SEGREDO: Mantém a conexão viva o tempo todo
    @Override
    public void run(String... args) throws Exception {

        // Limpa o banco antes de começar (opcional, mas evita duplicar se rodar de novo sem h2-mem)
        // itemPedidoRepository.deleteAll();
        // pagamentoRepository.deleteAll();
        // entregaRepository.deleteAll();
        // pedidoRepository.deleteAll();
        // clienteRepository.deleteAll();
        // pizzaRepository.deleteAll();
        // ingredienteRepository.deleteAll();
        // tamanhoRepository.deleteAll();

        // 1. TAMANHOS
        Tamanho tamM = new Tamanho();
        tamM.setDescricao("Média");
        tamM.setMultiplicadorPreco(1.0);

        Tamanho tamG = new Tamanho();
        tamG.setDescricao("Grande");
        tamG.setMultiplicadorPreco(1.3);

        tamanhoRepository.saveAll(Arrays.asList(tamM, tamG));

        // 2. INGREDIENTES
        Ingrediente ing1 = new Ingrediente(); ing1.setNome("Molho de Tomate");
        Ingrediente ing2 = new Ingrediente(); ing2.setNome("Mussarela");
        Ingrediente ing3 = new Ingrediente(); ing3.setNome("Calabresa");
        Ingrediente ing4 = new Ingrediente(); ing4.setNome("Cebola");
        Ingrediente ing5 = new Ingrediente(); ing5.setNome("Orégano");

        ingredienteRepository.saveAll(Arrays.asList(ing1, ing2, ing3, ing4, ing5));

        // 3. PIZZAS
        Pizza p1 = new Pizza();
        p1.setNome("Mussarela Tradicional");
        p1.setPrecoBase(35.00);
        p1.getIngredientes().addAll(Arrays.asList(ing1, ing2, ing5));

        Pizza p2 = new Pizza();
        p2.setNome("Calabresa Especial");
        p2.setPrecoBase(40.00);
        p2.getIngredientes().addAll(Arrays.asList(ing1, ing2, ing3, ing4, ing5));

        pizzaRepository.saveAll(Arrays.asList(p1, p2));

        // 4. CLIENTES
        Cliente c1 = new Cliente();
        c1.setNome("Dudu da Pizzaria");
        c1.setEmail("dudu@gmail.com");
        c1.setTelefone("11999999999");
        c1.setEndereco("Rua das Pizzas, 123");
        c1.setBairro("Centro");

        clienteRepository.save(c1);

        // 5. PEDIDOS
        Pedido ped1 = new Pedido();
        ped1.setInstante(LocalDateTime.now());
        ped1.setStatus(1);
        ped1.setCliente(c1);

        Pedido ped2 = new Pedido();
        ped2.setInstante(LocalDateTime.now());
        ped2.setStatus(2);
        ped2.setCliente(c1);

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

        // 6. ITENS DE PEDIDO
        ItemPedido ip1 = new ItemPedido(ped1, p1, 2, 35.00);
        ItemPedido ip2 = new ItemPedido(ped1, p2, 1, 40.00);
        ItemPedido ip3 = new ItemPedido(ped2, p1, 1, 35.00);

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

        // 7. PAGAMENTO E ENTREGA
        Pagamento pag1 = new Pagamento();
        pag1.setEstado(2);
        pag1.setPedido(ped1); // Com @Transactional, o ped1 continua "vivo" aqui

        pagamentoRepository.save(pag1);

        Entrega ent1 = new Entrega();
        ent1.setRua("Rua das Flores, 50");
        ent1.setBairro("Jardim");
        ent1.setCep("12345-000");
        ent1.setPedido(ped1); // Com @Transactional, o ped1 continua "vivo" aqui

        entregaRepository.save(ent1);
    }
}