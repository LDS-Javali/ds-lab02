package com.carconnect.api.domain;

import java.util.*;
public interface PedidoRepository {
    Optional<PedidoAluguel> byId(UUID id);
    PedidoAluguel save(PedidoAluguel p);
}
