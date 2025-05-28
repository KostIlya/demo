package ru.t1.demo.service;

import ru.t1.demo.model.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();
}
