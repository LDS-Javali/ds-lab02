package com.carconnect.api.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

public class PedidoAluguel {
    @Getter
    private final UUID id;
    private final UUID clienteId;
    private final UUID automovelId;
    @Getter
    private StatusPedido status = StatusPedido.CRIADO;
    private LocalDate inicio; private LocalDate fim;
    private AvaliacaoFinanceira avaliacao;

    public PedidoAluguel(UUID id, UUID clienteId, UUID automovelId, LocalDate inicio, LocalDate fim){
        this.id = id==null? UUID.randomUUID(): id;
        this.clienteId = clienteId; this.automovelId = automovelId;
        this.inicio = inicio; this.fim = fim;
    }

    public void submeterParaAnalise(){ ensure(StatusPedido.CRIADO); this.status = StatusPedido.EM_ANALISE; }
    public void avaliar(AvaliacaoFinanceira a){
        ensure(StatusPedido.EM_ANALISE);
        this.avaliacao = a;
        this.status = (a.getParecer()==Parecer.POSITIVO)? StatusPedido.APROVADO : StatusPedido.REJEITADO;
    }
    public void cancelar(){ if(status==StatusPedido.CRIADO||status==StatusPedido.EM_ANALISE) this.status = StatusPedido.CANCELADO; else throw new IllegalStateException(); }

    private void ensure(StatusPedido esperado){ if(this.status!=esperado) throw new IllegalStateException("Status inválido"); }

}
