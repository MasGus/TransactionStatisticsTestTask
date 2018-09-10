package com.n26.repository;

import com.n26.entity.Statistics;
import com.n26.entity.Transaction;

public interface StatisticsRepository {

    void addTransactionToStatistics(Transaction transaction);

    void updateStatistics(Statistics result);

    boolean isEmpty();

    long getTimestamp();

    void reset();
}
