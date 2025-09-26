package com.carconnect.api.interfaces.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Informações da API", description = "Informações gerais sobre a API")
public class ApiInfoController {
    
    @GetMapping("/info")
    @Operation(summary = "Informações sobre a API")
    public Map<String, String> info() {
        return Map.of(
            "nome", "CarConnect API",
            "versao", "1.0.0",
            "descricao", "Sistema de aluguel de automóveis com avaliação financeira",
            "swagger", "/swagger-ui.html"
        );
    }
    
    @GetMapping("/status")
    @Operation(summary = "Status da API")
    public Map<String, String> status() {
        return Map.of(
            "status", "ATIVO",
            "timestamp", java.time.LocalDateTime.now().toString()
        );
    }
}
