package com.carconnect.api.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carconnect.api.domain.Cliente;
import com.carconnect.api.domain.ClienteRepository;
import com.carconnect.api.domain.Empregador;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteRepository.findById(id);
    }
    
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void excluir(UUID id) {
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar(UUID id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setRg(clienteAtualizado.getRg());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setProfissao(clienteAtualizado.getProfissao());
        cliente.setEndereco(clienteAtualizado.getEndereco());

        return clienteRepository.save(cliente);
    }

    public Cliente adicionarEmpregador(UUID clienteId, Empregador empregador) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.adicionarEmpregador(empregador);
        return clienteRepository.save(cliente);
    }

    public void removerEmpregador(UUID clienteId, UUID empregadorId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.getEmpregadores().removeIf(e -> e.getId().equals(empregadorId));
        clienteRepository.save(cliente);
    }
}
