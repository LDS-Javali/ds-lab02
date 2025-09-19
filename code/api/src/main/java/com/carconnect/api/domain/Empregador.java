package com.carconnect.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "empregadores")
@Getter
@Setter
@NoArgsConstructor
public class Empregador {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String razaoSocial;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal rendimentoMensal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Empregador(String razaoSocial, BigDecimal rendimentoMensal){
        this.razaoSocial = Objects.requireNonNull(razaoSocial);
        this.rendimentoMensal = Objects.requireNonNull(rendimentoMensal);
    }
}
