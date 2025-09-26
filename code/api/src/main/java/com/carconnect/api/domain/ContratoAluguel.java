package com.carconnect.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "contratos_aluguel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoAluguel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private UUID pedidoId;
    
    @Column(nullable = false)
    private UUID proprietarioId; 
    
    @Column(nullable = false)
    private LocalDate inicioVigencia;
    
    @Column(nullable = false)
    private LocalDate fimVigencia;
    
    @Column(nullable = false)
    private BigDecimal valorTotal;
    
    @Column(nullable = false)
    private BigDecimal valorDiario;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusContrato status = StatusContrato.ATIVO;
    
    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @Column
    private LocalDateTime dataAtualizacao;
    
    @Column
    private String observacoes;
    
    @Column
    private UUID contratoCreditoId;

    public ContratoAluguel(UUID pedidoId, UUID proprietarioId, LocalDate inicioVigencia, LocalDate fimVigencia, BigDecimal valorDiario) {
        this.pedidoId = pedidoId;
        this.proprietarioId = proprietarioId;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.valorDiario = valorDiario;
        this.valorTotal = calcularValorTotal();
    }
    
    private BigDecimal calcularValorTotal() {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(inicioVigencia, fimVigencia);
        return valorDiario.multiply(BigDecimal.valueOf(dias));
    }
    
    public enum StatusContrato {
        ATIVO, FINALIZADO, CANCELADO, SUSPENSO
    }
}
