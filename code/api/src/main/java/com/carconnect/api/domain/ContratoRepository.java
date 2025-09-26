package com.carconnect.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContratoRepository extends JpaRepository<ContratoAluguel, UUID> {
    
    List<ContratoAluguel> findByPedidoId(UUID pedidoId);
    
    List<ContratoAluguel> findByProprietarioId(UUID proprietarioId);
    
    List<ContratoAluguel> findByStatus(ContratoAluguel.StatusContrato status);
}