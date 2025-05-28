package ru.t1.demo.service;

import ru.t1.demo.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getTransactions();
    TransactionDTO getTransactionById(Long id);
    TransactionDTO getTransactionByAccountId(Long account_id);
    void createTransaction(TransactionDTO transactionDTO);
    void deleteTransactionById(Long id);
    void updateTransaction(Long id, TransactionDTO transaction);
}
