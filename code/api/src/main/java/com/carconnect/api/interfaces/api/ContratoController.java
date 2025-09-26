package com.carconnect.api.interfaces.api;

import com.carconnect.api.application.ContratoService;
import com.carconnect.api.domain.ContratoAluguel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contratos")
@Tag(name = "Contratos de Aluguel", description = "API para gerenciamento de contratos de aluguel")
public class ContratoController {
    
    @Autowired
    private ContratoService contratoService;
    
    @GetMapping
    @Operation(summary = "Listar todos os contratos")
    public ResponseEntity<List<ContratoAluguel>> listarTodos() {
        List<ContratoAluguel> contratos = contratoService.listarTodos();
        return ResponseEntity.ok(contratos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar contrato por ID")
    public ResponseEntity<ContratoAluguel> buscarPorId(@PathVariable UUID id) {
        return contratoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/pedido/{pedidoId}")
    @Operation(summary = "Buscar contratos por pedido")
    public ResponseEntity<List<ContratoAluguel>> buscarPorPedido(@PathVariable UUID pedidoId) {
        List<ContratoAluguel> contratos = contratoService.buscarPorPedido(pedidoId);
        return ResponseEntity.ok(contratos);
    }
    
    @GetMapping("/proprietario/{proprietarioId}")
    @Operation(summary = "Buscar contratos por proprietário")
    public ResponseEntity<List<ContratoAluguel>> buscarPorProprietario(@PathVariable UUID proprietarioId) {
        List<ContratoAluguel> contratos = contratoService.buscarPorProprietario(proprietarioId);
        return ResponseEntity.ok(contratos);
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar contratos por status")
    public ResponseEntity<List<ContratoAluguel>> buscarPorStatus(@PathVariable ContratoAluguel.StatusContrato status) {
        List<ContratoAluguel> contratos = contratoService.buscarPorStatus(status);
        return ResponseEntity.ok(contratos);
    }
    
    @PostMapping
    @Operation(summary = "Criar novo contrato")
    public ResponseEntity<ContratoAluguel> criar(@RequestBody CriarContratoDTO dto) {
        try {
            ContratoAluguel contrato = contratoService.criarContrato(
                dto.pedidoId(), 
                dto.proprietarioId(), 
                dto.observacoes()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(contrato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contrato")
    public ResponseEntity<ContratoAluguel> atualizar(@PathVariable UUID id, @RequestBody AtualizarContratoDTO dto) {
        try {
            ContratoAluguel contrato = contratoService.atualizarContrato(id, dto.observacoes());
            return ResponseEntity.ok(contrato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/finalizar")
    @Operation(summary = "Finalizar contrato")
    public ResponseEntity<ContratoAluguel> finalizar(@PathVariable UUID id) {
        try {
            ContratoAluguel contrato = contratoService.finalizarContrato(id);
            return ResponseEntity.ok(contrato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar contrato")
    public ResponseEntity<ContratoAluguel> cancelar(@PathVariable UUID id, @RequestBody CancelarContratoDTO dto) {
        try {
            ContratoAluguel contrato = contratoService.cancelarContrato(id, dto.motivo());
            return ResponseEntity.ok(contrato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    public record CriarContratoDTO(
        UUID pedidoId,
        UUID proprietarioId,
        String observacoes
    ) {}
    
    public record AtualizarContratoDTO(
        String observacoes
    ) {}
    
    public record CancelarContratoDTO(
        String motivo
    ) {}
}
