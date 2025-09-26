package com.carconnect.api.interfaces.api;

import com.carconnect.api.application.PedidoService;
import com.carconnect.api.domain.PedidoAluguel;
import com.carconnect.api.domain.Parecer;
import com.carconnect.api.domain.StatusPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos de Aluguel", description = "API para gerenciamento de pedidos de aluguel")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar todos os pedidos")
    public ResponseEntity<List<PedidoAluguel>> listarTodos() {
        List<PedidoAluguel> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<PedidoAluguel> buscarPorId(@PathVariable UUID id) {
        return pedidoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Buscar pedidos por cliente")
    public ResponseEntity<List<PedidoAluguel>> buscarPorCliente(@PathVariable UUID clienteId) {
        List<PedidoAluguel> pedidos = pedidoService.buscarPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar pedidos por status")
    public ResponseEntity<List<PedidoAluguel>> buscarPorStatus(@PathVariable StatusPedido status) {
        List<PedidoAluguel> pedidos = pedidoService.buscarPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    @Operation(summary = "Criar novo pedido de aluguel")
    public ResponseEntity<PedidoAluguel> criar(@RequestBody CriarDTO dto) {
        try {
            PedidoAluguel pedido = pedidoService.criarPedido(
                dto.clienteId(), 
                dto.automovelId(), 
                dto.dataInicio(), 
                dto.dataFim(), 
                dto.observacoes()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pedido")
    public ResponseEntity<PedidoAluguel> atualizar(@PathVariable UUID id, @RequestBody AtualizarDTO dto) {
        try {
            PedidoAluguel pedido = pedidoService.atualizarPedido(
                id, 
                dto.dataInicio(), 
                dto.dataFim(), 
                dto.observacoes()
            );
            return ResponseEntity.ok(pedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/submeter")
    @Operation(summary = "Submeter pedido para análise")
    public ResponseEntity<PedidoAluguel> submeterParaAnalise(@PathVariable UUID id) {
        try {
            PedidoAluguel pedido = pedidoService.submeterParaAnalise(id);
            return ResponseEntity.ok(pedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{pedidoId}/avaliar")
    @Operation(summary = "Avaliar pedido")
    public ResponseEntity<PedidoAluguel> avaliar(@PathVariable UUID pedidoId, @RequestBody AvaliarDTO dto) {
        try {
            PedidoAluguel pedido = pedidoService.avaliarPedido(
                pedidoId, 
                dto.parecer(), 
                dto.observacoes()
            );
            return ResponseEntity.ok(pedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar pedido")
    public ResponseEntity<PedidoAluguel> cancelar(@PathVariable UUID id) {
        try {
            PedidoAluguel pedido = pedidoService.cancelarPedido(id);
            return ResponseEntity.ok(pedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pedido")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        try {
            pedidoService.excluirPedido(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public record CriarDTO(
        UUID clienteId, 
        UUID automovelId, 
        LocalDate dataInicio, 
        LocalDate dataFim,
        String observacoes
    ) {}
    
    public record AtualizarDTO(
        LocalDate dataInicio, 
        LocalDate dataFim,
        String observacoes
    ) {}
    
    public record AvaliarDTO(
        Parecer parecer, 
        String observacoes
    ) {}
}
