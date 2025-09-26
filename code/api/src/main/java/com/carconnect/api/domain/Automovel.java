package com.carconnect.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "automoveis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Automovel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String matricula;
    
    @Column(nullable = false)
    private Integer ano;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
    private String modelo;
    
    @Column(nullable = false, unique = true)
    private String placa;
    
    @Column(nullable = false)
    private BigDecimal valorDiario;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAutomovel status = StatusAutomovel.DISPONIVEL;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProprietario proprietario;
    
    @Column
    private UUID idProprietario;
    
    public enum StatusAutomovel {
        DISPONIVEL, ALUGADO, MANUTENCAO, INDISPONIVEL
    }
    
    public enum TipoProprietario {
        CLIENTE, EMPRESA, BANCO
    }
}
