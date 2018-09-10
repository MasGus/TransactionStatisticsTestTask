package com.n26.service;

import com.n26.entity.Transaction;

public interface TransactionService {
    void addTransaction(Transaction transaction);

    void deleteAllTransactions();
}
