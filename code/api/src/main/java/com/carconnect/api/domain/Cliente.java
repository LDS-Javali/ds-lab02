package com.carconnect.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String rg;
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String profissao;
    
    @Embedded
    private Endereco endereco;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Empregador> empregadores = new ArrayList<>();

    public Cliente(String rg, String cpf, String nome, String profissao, Endereco endereco){
        this.rg = require(rg); 
        this.cpf = require(cpf); 
        this.nome = require(nome);
        this.profissao = require(profissao); 
        this.endereco = Objects.requireNonNull(endereco);
    }
    
    private static String require(String v){
        if(v==null||v.isBlank()) throw new IllegalArgumentException();
        return v;
    }

    public void adicionarEmpregador(Empregador e){
        Objects.requireNonNull(e);
        if(empregadores.size() >= 3) throw new IllegalStateException("Máximo de 3 empregadores");
        e.setCliente(this);
        empregadores.add(e);
    }
}
