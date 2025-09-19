package com.carconnect.api.domain;

import lombok.Getter;

import java.time.Instant;
public class AvaliacaoFinanceira {
    private final Instant dataParecer = Instant.now();
    @Getter
    private final Parecer parecer;
    private final String observacoes;
    public AvaliacaoFinanceira(Parecer parecer, String obs){ this.parecer=parecer; this.observacoes=obs; }
}
