package com.carconnect.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "avaliacoes_financeiras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFinanceira {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Parecer parecer;
    
    @Column(nullable = false)
    private String observacoes;
    
    @Column(nullable = false)
    private Instant dataParecer = Instant.now();
    
    @Column
    private UUID avaliadorId; 
    
    public AvaliacaoFinanceira(Parecer parecer, String observacoes) {
        this.parecer = parecer;
        this.observacoes = observacoes;
        this.dataParecer = Instant.now();
    }
    
    public AvaliacaoFinanceira(Parecer parecer, String observacoes, UUID avaliadorId) {
        this.parecer = parecer;
        this.observacoes = observacoes;
        this.avaliadorId = avaliadorId;
        this.dataParecer = Instant.now();
    }
}
