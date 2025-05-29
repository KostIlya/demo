package ru.t1.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1.demo.aop.annotation.Cached;
import ru.t1.demo.aop.annotation.Metric;
import ru.t1.demo.util.ClientMapper;
import ru.t1.demo.model.dto.ClientDTO;
import ru.t1.demo.repository.ClientRepository;
import ru.t1.demo.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientMapper clientMapper;

    @Override
    @Metric
    @Cached
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }
}
