package com.carconnect.api.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

    @Getter
    public class Empregador {
        private final UUID id;
        private final String razaoSocial;
        private final BigDecimal rendimentoMensal;

        public Empregador(UUID id, String razaoSocial, BigDecimal rendimentoMensal){
            this.id = id==null? UUID.randomUUID(): id;
            this.razaoSocial = Objects.requireNonNull(razaoSocial);
            this.rendimentoMensal = Objects.requireNonNull(rendimentoMensal);
        }
    }
