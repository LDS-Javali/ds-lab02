package com.carconnect.api.interfaces.api;

import com.carconnect.api.application.ClienteService;
import com.carconnect.api.domain.Cliente;
import com.carconnect.api.domain.Empregador;
import com.carconnect.api.domain.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar cliente por CPF")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo cliente")
    public ResponseEntity<Cliente> cadastrar(@RequestBody ClienteCreateDTO dto) {
        try {
            Endereco endereco = new Endereco(
                dto.logradouro(), 
                dto.numero(), 
                dto.complemento(), 
                dto.bairro(), 
                dto.cidade(), 
                dto.estado(), 
                dto.cep()
            );
            
            Cliente cliente = new Cliente(
                dto.rg(), 
                dto.cpf(), 
                dto.nome(), 
                dto.profissao(), 
                endereco
            );
            
            Cliente clienteSalvo = clienteService.salvar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<Cliente> atualizar(@PathVariable UUID id, @RequestBody ClienteUpdateDTO dto) {
        try {
            Endereco endereco = new Endereco(
                dto.logradouro(), 
                dto.numero(), 
                dto.complemento(), 
                dto.bairro(), 
                dto.cidade(), 
                dto.estado(), 
                dto.cep()
            );
            
            Cliente clienteAtualizado = new Cliente();
            clienteAtualizado.setRg(dto.rg());
            clienteAtualizado.setCpf(dto.cpf());
            clienteAtualizado.setNome(dto.nome());
            clienteAtualizado.setProfissao(dto.profissao());
            clienteAtualizado.setEndereco(endereco);
            
            Cliente cliente = clienteService.atualizar(id, clienteAtualizado);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/empregadores")
    @Operation(summary = "Adicionar empregador ao cliente")
    public ResponseEntity<Cliente> adicionarEmpregador(@PathVariable UUID id, @RequestBody EmpregadorDTO dto) {
        try {
            Empregador empregador = new Empregador(dto.razaoSocial(), dto.rendimentoMensal());
            Cliente cliente = clienteService.adicionarEmpregador(id, empregador);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        try {
            clienteService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    public record ClienteCreateDTO(
        String rg,
        String cpf,
        String nome,
        String profissao,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
    ) {}
    
    public record ClienteUpdateDTO(
        String rg,
        String cpf,
        String nome,
        String profissao,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
    ) {}
    
    public record EmpregadorDTO(
        String razaoSocial,
        BigDecimal rendimentoMensal
    ) {}
}
