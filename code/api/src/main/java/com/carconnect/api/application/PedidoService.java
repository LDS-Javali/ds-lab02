package com.carconnect.api.application;

import com.carconnect.api.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private AutomovelRepository automovelRepository;
    
    public List<PedidoAluguel> listarTodos() {
        return pedidoRepository.findAll();
    }
    
    public Optional<PedidoAluguel> buscarPorId(UUID id) {
        return pedidoRepository.findById(id);
    }
    
    public List<PedidoAluguel> buscarPorCliente(UUID clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
    
    public List<PedidoAluguel> buscarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }
    
    public PedidoAluguel criarPedido(UUID clienteId, UUID automovelId, LocalDate dataInicio, LocalDate dataFim, String observacoes) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente não encontrado: " + clienteId);
        }
        
        Automovel automovel = automovelRepository.findById(automovelId)
            .orElseThrow(() -> new IllegalArgumentException("Automóvel não encontrado: " + automovelId));
        
        if (automovel.getStatus() != Automovel.StatusAutomovel.DISPONIVEL) {
            throw new IllegalArgumentException("Automóvel não está disponível para aluguel");
        }
        
        if (dataInicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de início não pode ser no passado");
        }
        
        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("Data de fim deve ser posterior à data de início");
        }
        
        PedidoAluguel pedido = new PedidoAluguel(clienteId, automovelId, dataInicio, dataFim);
        pedido.setObservacoes(observacoes);
        
        return pedidoRepository.save(pedido);
    }
    
    public PedidoAluguel submeterParaAnalise(UUID id) {
        PedidoAluguel pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        
        pedido.submeterParaAnalise();
        return pedidoRepository.save(pedido);
    }
    
    public PedidoAluguel avaliarPedido(UUID id, Parecer parecer, String observacoes) {
        PedidoAluguel pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        
        AvaliacaoFinanceira avaliacao = new AvaliacaoFinanceira(parecer, observacoes);
        pedido.avaliar(avaliacao);
        
        return pedidoRepository.save(pedido);
    }
    
    public PedidoAluguel cancelarPedido(UUID id) {
        PedidoAluguel pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        
        pedido.cancelar();
        return pedidoRepository.save(pedido);
    }
    
    public PedidoAluguel atualizarPedido(UUID id, LocalDate dataInicio, LocalDate dataFim, String observacoes) {
        PedidoAluguel pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        
        if (pedido.getStatus() != StatusPedido.CRIADO) {
            throw new IllegalArgumentException("Só é possível atualizar pedidos com status CRIADO");
        }
        
        if (dataInicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de início não pode ser no passado");
        }
        
        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("Data de fim deve ser posterior à data de início");
        }
        
        pedido.setDataInicio(dataInicio);
        pedido.setDataFim(dataFim);
        pedido.setObservacoes(observacoes);
        
        return pedidoRepository.save(pedido);
    }
    
    public void excluirPedido(UUID id) {
        PedidoAluguel pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        
        if (pedido.getStatus() != StatusPedido.CRIADO) {
            throw new IllegalArgumentException("Só é possível excluir pedidos com status CRIADO");
        }
        
        pedidoRepository.deleteById(id);
    }
}
