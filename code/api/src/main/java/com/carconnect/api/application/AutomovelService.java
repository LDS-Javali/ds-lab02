package com.carconnect.api.application;

import com.carconnect.api.domain.Automovel;
import com.carconnect.api.domain.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutomovelService {
    
    @Autowired
    private AutomovelRepository automovelRepository;
    
    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }
    
    public Optional<Automovel> buscarPorId(UUID id) {
        return automovelRepository.findById(id);
    }
    
    public Optional<Automovel> buscarPorMatricula(String matricula) {
        return automovelRepository.findByMatricula(matricula);
    }
    
    public Optional<Automovel> buscarPorPlaca(String placa) {
        return automovelRepository.findByPlaca(placa);
    }
    
    public List<Automovel> buscarPorStatus(Automovel.StatusAutomovel status) {
        return automovelRepository.findByStatus(status);
    }
    
    public List<Automovel> buscarPorMarcaEModelo(String marca, String modelo) {
        return automovelRepository.findByMarcaAndModelo(marca, modelo);
    }
    
    public List<Automovel> buscarPorProprietario(Automovel.TipoProprietario proprietario, UUID idProprietario) {
        return automovelRepository.findByProprietarioAndIdProprietario(proprietario, idProprietario);
    }
    
    public Automovel salvar(Automovel automovel) {
        if (automovelRepository.findByMatricula(automovel.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já cadastrada: " + automovel.getMatricula());
        }
        if (automovelRepository.findByPlaca(automovel.getPlaca()).isPresent()) {
            throw new IllegalArgumentException("Placa já cadastrada: " + automovel.getPlaca());
        }
        
        return automovelRepository.save(automovel);
    }
    
    public Automovel atualizar(UUID id, Automovel automovelAtualizado) {
        Automovel automovel = automovelRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Automóvel não encontrado: " + id));
        
        Optional<Automovel> automovelComMatricula = automovelRepository.findByMatricula(automovelAtualizado.getMatricula());
        if (automovelComMatricula.isPresent() && !automovelComMatricula.get().getId().equals(id)) {
            throw new IllegalArgumentException("Matrícula já cadastrada: " + automovelAtualizado.getMatricula());
        }
        
        Optional<Automovel> automovelComPlaca = automovelRepository.findByPlaca(automovelAtualizado.getPlaca());
        if (automovelComPlaca.isPresent() && !automovelComPlaca.get().getId().equals(id)) {
            throw new IllegalArgumentException("Placa já cadastrada: " + automovelAtualizado.getPlaca());
        }
        
        automovel.setMatricula(automovelAtualizado.getMatricula());
        automovel.setAno(automovelAtualizado.getAno());
        automovel.setMarca(automovelAtualizado.getMarca());
        automovel.setModelo(automovelAtualizado.getModelo());
        automovel.setPlaca(automovelAtualizado.getPlaca());
        automovel.setValorDiario(automovelAtualizado.getValorDiario());
        automovel.setStatus(automovelAtualizado.getStatus());
        automovel.setProprietario(automovelAtualizado.getProprietario());
        automovel.setIdProprietario(automovelAtualizado.getIdProprietario());
        
        return automovelRepository.save(automovel);
    }
    
    public void excluir(UUID id) {
        if (!automovelRepository.existsById(id)) {
            throw new IllegalArgumentException("Automóvel não encontrado: " + id);
        }
        automovelRepository.deleteById(id);
    }
}
