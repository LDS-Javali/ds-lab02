package com.carconnect.api.domain;

import java.util.*;

public interface ClienteRepository {
    Optional<Cliente> byId(UUID id);
    Cliente save(Cliente cliente);
    void delete(UUID id);
}
