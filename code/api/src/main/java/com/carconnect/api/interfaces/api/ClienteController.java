package com.carconnect.api.interfaces.api;

import com.carconnect.api.domain.Endereco;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
   // private final CadastrarClienteHandler cadastrar;
   //  public ClienteController(CadastrarClienteHandler cadastrar){ this.cadastrar = cadastrar; }

    @PostMapping
    public Map<String,String> cadastrar(@RequestBody ClienteCreateDTO dto){
        // var cmd = new CadastrarClienteCommand(dto.rg, dto.cpf, dto.nome, dto.profissao,
                new Endereco(dto.logradouro, dto.numero, dto.complemento, dto.bairro, dto.cidade, dto.estado, dto.cep);
        // var c = cadastrar.handle(cmd);
        // return Map.of("id", c.getId().toString());
        return null;
    }
    public record ClienteCreateDTO(String rg,String cpf,String nome,String profissao,
                                   String logradouro,String numero,String complemento,
                                   String bairro,String cidade,String estado,String cep){}
}
