package com.carconnect.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("CarConnect API")
                .description("Sistema de aluguel de automóveis com avaliação financeira. " +
                           "Permite que clientes façam pedidos de aluguel, que são avaliados " +
                           "financeiramente por agentes (empresas e bancos), e após aprovação " +
                           "são criados contratos de aluguel.")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Equipe CarConnect")
                    .email("contato@carconnect.com"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Servidor de Desenvolvimento"),
                new Server()
                    .url("https://api.carconnect.com")
                    .description("Servidor de Produção")
            ));
    }
}
