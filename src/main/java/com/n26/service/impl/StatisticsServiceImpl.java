package com.n26.service.impl;

import com.n26.entity.Statistics;
import com.n26.repository.StatisticsRepository;
import com.n26.repository.TransactionRepository;
import com.n26.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private TransactionRepository transactionRepository;

    @Autowired
    public StatisticsServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Statistics getStatistics() {
        long currentTimestamp = Instant.now().toEpochMilli();
        List<StatisticsRepository> statisticsRepository = transactionRepository.getValidTransactionStatistics(currentTimestamp);

        Statistics statistics = new Statistics();

        if (!statisticsRepository.isEmpty()) {
            statisticsRepository.forEach(t -> t.updateStatistics(statistics));
        }
        else {
            statistics.clear();
        }
        return statistics;
    }
}
