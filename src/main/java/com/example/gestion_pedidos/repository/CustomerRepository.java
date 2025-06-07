package com.example.gestion_pedidos.repository;

import com.example.gestion_pedidos.model.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Client, Long> {
}
