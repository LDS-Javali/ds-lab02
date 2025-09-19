package com.carconnect.api.domain;

import lombok.Getter;

import java.util.*;

@Getter
public class Cliente {
    private final UUID id;
    private final String rg;
    private final String cpf;
    private final String nome;
    private final String profissao;
    private final Endereco endereco;
    private final List<Empregador> empregadores = new ArrayList<>();

    public Cliente(UUID id, String rg, String cpf, String nome, String profissao, Endereco endereco){
        this.id = id==null? UUID.randomUUID(): id;
        this.rg = require(rg); this.cpf = require(cpf); this.nome = require(nome);
        this.profissao = require(profissao); this.endereco = Objects.requireNonNull(endereco);
    }
    private static String require(String v){
        if(v==null||v.isBlank()) throw new IllegalArgumentException();
        return v;
    }

    public void adicionarEmpregador(Empregador e){
        Objects.requireNonNull(e);
        if(empregadores.size() >= 3) throw new IllegalStateException("Máximo de 3 empregadores");
        empregadores.add(e);
    }
}
