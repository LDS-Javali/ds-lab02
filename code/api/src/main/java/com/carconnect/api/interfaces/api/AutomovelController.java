package com.carconnect.api.interfaces.api;

import com.carconnect.api.application.AutomovelService;
import com.carconnect.api.domain.Automovel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/automoveis")
@Tag(name = "Automóveis", description = "API para gerenciamento de automóveis")
public class AutomovelController {
    
    @Autowired
    private AutomovelService automovelService;
    
    @GetMapping
    @Operation(summary = "Listar todos os automóveis")
    public ResponseEntity<List<Automovel>> listarTodos() {
        List<Automovel> automoveis = automovelService.listarTodos();
        return ResponseEntity.ok(automoveis);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar automóvel por ID")
    public ResponseEntity<Automovel> buscarPorId(@PathVariable UUID id) {
        return automovelService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/matricula/{matricula}")
    @Operation(summary = "Buscar automóvel por matrícula")
    public ResponseEntity<Automovel> buscarPorMatricula(@PathVariable String matricula) {
        return automovelService.buscarPorMatricula(matricula)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/placa/{placa}")
    @Operation(summary = "Buscar automóvel por placa")
    public ResponseEntity<Automovel> buscarPorPlaca(@PathVariable String placa) {
        return automovelService.buscarPorPlaca(placa)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar automóveis por status")
    public ResponseEntity<List<Automovel>> buscarPorStatus(@PathVariable Automovel.StatusAutomovel status) {
        List<Automovel> automoveis = automovelService.buscarPorStatus(status);
        return ResponseEntity.ok(automoveis);
    }
    
    @GetMapping("/disponiveis")
    @Operation(summary = "Listar automóveis disponíveis")
    public ResponseEntity<List<Automovel>> buscarDisponiveis() {
        List<Automovel> automoveis = automovelService.buscarPorStatus(Automovel.StatusAutomovel.DISPONIVEL);
        return ResponseEntity.ok(automoveis);
    }
    
    @PostMapping
    @Operation(summary = "Criar novo automóvel")
    public ResponseEntity<Automovel> criar(@RequestBody Automovel automovel) {
        try {
            Automovel automovelSalvo = automovelService.salvar(automovel);
            return ResponseEntity.status(HttpStatus.CREATED).body(automovelSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar automóvel")
    public ResponseEntity<Automovel> atualizar(@PathVariable UUID id, @RequestBody Automovel automovel) {
        try {
            Automovel automovelAtualizado = automovelService.atualizar(id, automovel);
            return ResponseEntity.ok(automovelAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir automóvel")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        try {
            automovelService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
