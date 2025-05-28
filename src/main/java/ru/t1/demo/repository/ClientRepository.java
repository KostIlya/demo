package ru.t1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.t1.demo.model.Client;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT * FROM Client c WHERE c.client_id = :clientId",
            nativeQuery = true)
    Client findByClientId(UUID clientId);
}
