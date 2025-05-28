package ru.t1.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t1.demo.model.Account;
import ru.t1.demo.model.Client;
import ru.t1.demo.model.dto.AccountRequestDTO;
import ru.t1.demo.repository.ClientRepository;

@Component
@Slf4j
public class AccountRequestMapper {
    @Autowired
    ClientRepository clientRepository;

    public Account toEntity(AccountRequestDTO accountRequestDTO) {
        Client client = clientRepository.findByClientId(accountRequestDTO.getClientId());
        return Account.builder()
                .type(accountRequestDTO.getType())
                .balance(accountRequestDTO.getBalance())
                .client(client)
                .build();
    }

}
