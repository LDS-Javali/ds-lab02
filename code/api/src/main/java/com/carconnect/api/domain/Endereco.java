package com.carconnect.api.domain;

import java.util.Objects;

public record Endereco(String logradouro, String numero, String complemento, String bairro, String cidade,
                       String estado, String cep) {
    public Endereco(String logradouro, String numero, String complemento,
                    String bairro, String cidade, String estado, String cep) {
        this.logradouro = require(logradouro);
        this.numero = require(numero);
        this.complemento = complemento;
        this.bairro = require(bairro);
        this.cidade = require(cidade);
        this.estado = require(estado);
        this.cep = require(cep);
    }

    private static String require(String v) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException();
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco e = (Endereco) o;
        return Objects.equals(logradouro, e.logradouro) && Objects.equals(numero, e.numero)
                && Objects.equals(complemento, e.complemento) && Objects.equals(bairro, e.bairro)
                && Objects.equals(cidade, e.cidade) && Objects.equals(estado, e.estado) && Objects.equals(cep, e.cep);
    }
}
