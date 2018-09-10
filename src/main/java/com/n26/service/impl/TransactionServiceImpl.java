package com.n26.service.impl;

import com.n26.entity.Transaction;
import com.n26.repository.TransactionRepository;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        long currentTimestamp = Instant.now().toEpochMilli();
        transactionRepository.addTransaction(transaction, currentTimestamp);
    }

    public void deleteAllTransactions() {
        transactionRepository.clear();
    }

}
