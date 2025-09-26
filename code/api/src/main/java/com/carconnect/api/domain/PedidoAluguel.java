package com.carconnect.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pedidos_aluguel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoAluguel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private UUID clienteId;
    
    @Column(nullable = false)
    private UUID automovelId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status = StatusPedido.CRIADO;
    
    @Column(nullable = false)
    private LocalDate dataInicio;
    
    @Column(nullable = false)
    private LocalDate dataFim;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliacao_id")
    private AvaliacaoFinanceira avaliacao;
    
    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @Column
    private LocalDateTime dataAtualizacao;
    
    @Column
    private String observacoes;

    public PedidoAluguel(UUID clienteId, UUID automovelId, LocalDate dataInicio, LocalDate dataFim) {
        this.clienteId = clienteId;
        this.automovelId = automovelId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public void submeterParaAnalise() {
        ensure(StatusPedido.CRIADO);
        this.status = StatusPedido.EM_ANALISE;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public void avaliar(AvaliacaoFinanceira avaliacao) {
        ensure(StatusPedido.EM_ANALISE);
        this.avaliacao = avaliacao;
        this.status = (avaliacao.getParecer() == Parecer.POSITIVO) ? StatusPedido.APROVADO : StatusPedido.REJEITADO;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public void cancelar() {
        if (status == StatusPedido.CRIADO || status == StatusPedido.EM_ANALISE) {
            this.status = StatusPedido.CANCELADO;
            this.dataAtualizacao = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Não é possível cancelar pedido com status: " + status);
        }
    }

    private void ensure(StatusPedido esperado) {
        if (this.status != esperado) {
            throw new IllegalStateException("Status inválido. Esperado: " + esperado + ", Atual: " + this.status);
        }
    }
}
