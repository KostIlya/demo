package ru.t1.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t1.demo.model.Account;
import ru.t1.demo.model.Transaction;
import ru.t1.demo.model.dto.TransactionDTO;
import ru.t1.demo.repository.AccountRepository;

@Component
public class TransactionMapper {
    @Autowired
    AccountRepository accountRepository;

    public Transaction toEntity(TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId()).orElseThrow(() -> new NullPointerException("Account с id {} не существует." + transactionDTO.getAccountId()));
        return Transaction.builder()
                .account(account)
                .sumTransaction(transactionDTO.getSumTransaction())
                .dateTime(transactionDTO.getDateTime())
                .build();
    }

    public TransactionDTO toDto(Transaction transaction) {
        return TransactionDTO.builder()
                .accountId(transaction.getAccount().getId())
                .sumTransaction(transaction.getSumTransaction())
                .dateTime(transaction.getDateTime())
                .build();
    }
}
