package com.carconnect.api.interfaces.api;


import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
//    private final EfetuarPedidoHandler efetuar;
//    private final AvaliarPedidoHandler avaliar;

//    public PedidoController(EfetuarPedidoHandler efetuar, AvaliarPedidoHandler avaliar){
//        this.efetuar=efetuar; this.avaliar=avaliar;
//    }

    @PostMapping
    public Map<String,String> criar(@RequestBody CriarDTO dto){
//        var cmd = new EfetuarPedidoCommand(dto.clienteId, dto.automovelId, dto.inicio, dto.fim);
//        var p = efetuar.handle(cmd);
//        return Map.of("id", p.getId().toString(), "status", p.getStatus().name());
        return null;
    }

    @PostMapping("/{pedidoId}/avaliar")
    public Map<String,String> avaliar(@PathVariable UUID pedidoId, @RequestBody AvaliarDTO dto){
//        var cmd = new AvaliarPedidoCommand(pedidoId, dto.parecerPositivo, dto.observacoes, dto.valorCredito);
//        var p = avaliar.handle(cmd, dto.clienteId);
//        return Map.of("id", p.getId().toString(), "status", p.getStatus().name());
        return null;
    }

    public record CriarDTO(UUID clienteId, UUID automovelId, LocalDate inicio, LocalDate fim){}
    public record AvaliarDTO(UUID clienteId, boolean parecerPositivo, String observacoes, java.math.BigDecimal valorCredito){}
}
