package com.carconnect.api.application;

import com.carconnect.api.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContratoService {
    
    @Autowired
    private ContratoRepository contratoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private AutomovelRepository automovelRepository;
    
    public List<ContratoAluguel> listarTodos() {
        return contratoRepository.findAll();
    }
    
    public Optional<ContratoAluguel> buscarPorId(UUID id) {
        return contratoRepository.findById(id);
    }
    
    public List<ContratoAluguel> buscarPorPedido(UUID pedidoId) {
        return contratoRepository.findByPedidoId(pedidoId);
    }
    
    public List<ContratoAluguel> buscarPorProprietario(UUID proprietarioId) {
        return contratoRepository.findByProprietarioId(proprietarioId);
    }
    
    public List<ContratoAluguel> buscarPorStatus(ContratoAluguel.StatusContrato status) {
        return contratoRepository.findByStatus(status);
    }
    
    public ContratoAluguel criarContrato(UUID pedidoId, UUID proprietarioId, String observacoes) {
        PedidoAluguel pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + pedidoId));
        
        if (pedido.getStatus() != StatusPedido.APROVADO) {
            throw new IllegalArgumentException("Pedido deve estar aprovado para criar contrato");
        }
        
        List<ContratoAluguel> contratosExistentes = contratoRepository.findByPedidoId(pedidoId);
        if (!contratosExistentes.isEmpty()) {
            throw new IllegalArgumentException("Já existe contrato para este pedido");
        }
        
        Automovel automovel = automovelRepository.findById(pedido.getAutomovelId())
            .orElseThrow(() -> new IllegalArgumentException("Automóvel não encontrado"));
        
        ContratoAluguel contrato = new ContratoAluguel(
            pedidoId, 
            proprietarioId, 
            pedido.getDataInicio(), 
            pedido.getDataFim(), 
            automovel.getValorDiario()
        );
        
        contrato.setObservacoes(observacoes);
        
        automovel.setStatus(Automovel.StatusAutomovel.ALUGADO);
        automovelRepository.save(automovel);
        
        return contratoRepository.save(contrato);
    }
    
    public ContratoAluguel finalizarContrato(UUID id) {
        ContratoAluguel contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado: " + id));
        
        if (contrato.getStatus() != ContratoAluguel.StatusContrato.ATIVO) {
            throw new IllegalArgumentException("Só é possível finalizar contratos ativos");
        }
        
        contrato.setStatus(ContratoAluguel.StatusContrato.FINALIZADO);
        
        PedidoAluguel pedido = pedidoRepository.findById(contrato.getPedidoId())
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        
        Automovel automovel = automovelRepository.findById(pedido.getAutomovelId())
            .orElseThrow(() -> new IllegalArgumentException("Automóvel não encontrado"));
        
        automovel.setStatus(Automovel.StatusAutomovel.DISPONIVEL);
        automovelRepository.save(automovel);
        
        return contratoRepository.save(contrato);
    }
    
    public ContratoAluguel cancelarContrato(UUID id, String motivo) {
        ContratoAluguel contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado: " + id));
        
        if (contrato.getStatus() != ContratoAluguel.StatusContrato.ATIVO) {
            throw new IllegalArgumentException("Só é possível cancelar contratos ativos");
        }
        
        contrato.setStatus(ContratoAluguel.StatusContrato.CANCELADO);
        contrato.setObservacoes(contrato.getObservacoes() + " | Cancelado: " + motivo);
        
        PedidoAluguel pedido = pedidoRepository.findById(contrato.getPedidoId())
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        
        Automovel automovel = automovelRepository.findById(pedido.getAutomovelId())
            .orElseThrow(() -> new IllegalArgumentException("Automóvel não encontrado"));
        
        automovel.setStatus(Automovel.StatusAutomovel.DISPONIVEL);
        automovelRepository.save(automovel);
        
        return contratoRepository.save(contrato);
    }
    
    public ContratoAluguel atualizarContrato(UUID id, String observacoes) {
        ContratoAluguel contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado: " + id));
        
        contrato.setObservacoes(observacoes);
        return contratoRepository.save(contrato);
    }
}
