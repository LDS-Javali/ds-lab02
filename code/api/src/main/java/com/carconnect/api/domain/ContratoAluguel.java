package com.carconnect.api.domain;

import java.time.LocalDate;
import java.util.UUID;

public class ContratoAluguel {
    private final UUID id;
    private final UUID pedidoId;
    private final UUID proprietarioId; // pode ser cliente/agente/banco
    private final LocalDate inicioVigencia, fimVigencia;

    public ContratoAluguel(UUID id, UUID pedidoId, UUID proprietarioId, LocalDate ini, LocalDate fim){
        this.id = id==null? UUID.randomUUID(): id;
        this.pedidoId = pedidoId; this.proprietarioId = proprietarioId; this.inicioVigencia=ini; this.fimVigencia=fim;
    }
}
