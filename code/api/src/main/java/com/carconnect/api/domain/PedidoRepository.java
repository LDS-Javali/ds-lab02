package com.carconnect.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoAluguel, UUID> {
    
    List<PedidoAluguel> findByClienteId(UUID clienteId);
    
    List<PedidoAluguel> findByStatus(StatusPedido status);
    
    List<PedidoAluguel> findByAutomovelId(UUID automovelId);
}
