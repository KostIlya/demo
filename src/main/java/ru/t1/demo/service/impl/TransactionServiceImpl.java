package ru.t1.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1.demo.aop.annotation.Cached;
import ru.t1.demo.aop.annotation.Metric;
import ru.t1.demo.exception.NoEntityException;
import ru.t1.demo.util.TransactionMapper;
import ru.t1.demo.model.Transaction;
import ru.t1.demo.model.dto.TransactionDTO;
import ru.t1.demo.repository.TransactionRepository;
import ru.t1.demo.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private  TransactionRepository transactionRepository;
    @Autowired
    private  TransactionMapper transactionMapper;

    @Override
    @Metric
    @Cached
    public List<TransactionDTO> getTransactions() {
        log.info("Getting transactions");
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Metric
    @Cached(key = "id")
    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()->new NoEntityException("Транзакции с id " + id + " не существует"));

        return TransactionDTO.builder()
                .accountId(transaction.getAccount().getId())
                .sumTransaction(transaction.getSumTransaction())
                .dateTime(transaction.getDateTime())
                .build();
    }

    @Override
    @Metric
    @Cached(key = "accountId")
    public TransactionDTO getTransactionByAccountId(Long accountId) {
        Transaction transaction = transactionRepository.findByAccountId(accountId).orElseThrow(()->new NoEntityException("Транзакции с account id " + accountId + " не существует"));

        return TransactionDTO.builder()
                .accountId(transaction.getAccount().getId())
                .sumTransaction(transaction.getSumTransaction())
                .dateTime(transaction.getDateTime())
                .build();
    }

    @Override
    @Metric
    public void createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transactionRepository.save(transaction);
    }

    @Override
    @Metric
    public void deleteTransactionById(Long id) {
        transactionRepository.findById(id).orElseThrow(()->new NoEntityException("Транзакции с id " + id + "  не существует."));
        transactionRepository.deleteById(id);
    }

    @Override
    @Metric
    public void updateTransaction(Long id, TransactionDTO transactionDTO) {
        transactionRepository.findById(id).orElseThrow(()->new NoEntityException("Транзакции с id " + id + "  не существует."));
        Transaction transaction = transactionMapper.toEntity(transactionDTO);

        transactionRepository.save(transaction);
    }
}
