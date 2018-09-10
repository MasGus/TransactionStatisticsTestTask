package com.n26.repository;

import com.n26.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    void addTransaction(Transaction transaction, long currentTimestamp);

    List<StatisticsRepository> getValidTransactionStatistics(long currentTimestamp);

    void clear();
}
