package com.carconnect.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, UUID> {
    
    Optional<Automovel> findByMatricula(String matricula);
    
    Optional<Automovel> findByPlaca(String placa);
    
    List<Automovel> findByStatus(Automovel.StatusAutomovel status);
    
    List<Automovel> findByMarcaAndModelo(String marca, String modelo);
    
    List<Automovel> findByProprietarioAndIdProprietario(Automovel.TipoProprietario proprietario, UUID idProprietario);
}
