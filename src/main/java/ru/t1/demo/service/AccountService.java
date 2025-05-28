package ru.t1.demo.service;

import ru.t1.demo.model.dto.AccountRequestDTO;
import ru.t1.demo.model.dto.AccountResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<AccountResponseDTO> getAccounts();
    AccountResponseDTO getAccountById(Long id);
    AccountResponseDTO getAccountByClientId(UUID clientId);
    void createAccount(AccountRequestDTO account);
    void deleteAccountById(Long id);
    void updateAccount(Long id, AccountRequestDTO accountRequestDTO);
}
