package com.n26.repository.impl;

import com.n26.entity.Transaction;
import com.n26.repository.StatisticsRepository;
import com.n26.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionRepositoryImpl implements TransactionRepository {

    private StatisticsRepository[] statisticsRepository;

    private static final int VALID_PAST_TIME_INTERVAL = 60000;
    private static final int REQUEST_INTERVAL = 10;

    private TransactionRepositoryImpl() {}

    @PostConstruct
    private void postConstruct() {
        this.statisticsRepository = new StatisticsRepositoryImpl[VALID_PAST_TIME_INTERVAL / REQUEST_INTERVAL];
        this.initStatisticsRepository();
    }

    @Override
    public void addTransaction(Transaction transaction, long currentTimestamp) {
        StatisticsRepository statisticsRepositoryElement = statisticsRepository[getTransactionIndex(transaction)];

        if (isValidTimestamp(statisticsRepositoryElement.getTimestamp(), currentTimestamp)) {
            statisticsRepositoryElement.addTransactionToStatistics(transaction);
        } else {
            statisticsRepositoryElement.reset();
            statisticsRepositoryElement.addTransactionToStatistics(transaction);
        }
    }

    @Override
    public List<StatisticsRepository> getValidTransactionStatistics(long currentTimestamp) {
        List<StatisticsRepository> validTransactionslist = new ArrayList<>();
        for (StatisticsRepository statisticsRepositoryElement : statisticsRepository){
            if (isValidTimestamp(statisticsRepositoryElement.getTimestamp(), currentTimestamp)){
                validTransactionslist.add(statisticsRepositoryElement);
            }
        }
        return validTransactionslist;
    }

    @Override
    public void clear() {
        this.initStatisticsRepository();
    }

    private boolean isValidTimestamp(long timestamp, long currentTimestamp) {
        return timestamp >= (currentTimestamp - VALID_PAST_TIME_INTERVAL);
    }

    private int getTransactionIndex(Transaction transaction) {
        long timestamp = Instant.parse(transaction.getTimestamp()).toEpochMilli();
        long currentTimestamp = Instant.now().toEpochMilli();

        return (int) ((currentTimestamp - timestamp) / REQUEST_INTERVAL) % (VALID_PAST_TIME_INTERVAL);
    }

    private void initStatisticsRepository() {
        for (int i = 0; i < this.statisticsRepository.length; i++)
            this.statisticsRepository[i] = new StatisticsRepositoryImpl();
    }
}
