package com.n26.repository.impl;

import com.n26.entity.Statistics;
import com.n26.entity.Transaction;
import com.n26.repository.StatisticsRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

public class StatisticsRepositoryImpl implements StatisticsRepository {

    private Statistics statistics;
    private long timestamp;

    StatisticsRepositoryImpl() {
        this.statistics = new Statistics();
    }

    public void addTransactionToStatistics(Transaction transaction) {
        BigDecimal amount = transaction.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
        if (isEmpty()) {
            this.statistics.setMax(amount);
            this.statistics.setMin(amount);
            this.statistics.setSum(amount);
            this.statistics.setAvg(amount);
            this.statistics.setCount(1);
            this.timestamp = Instant.parse(transaction.getTimestamp()).toEpochMilli();
        } else {
            if (amount.compareTo(this.statistics.getMin()) < 0)
                this.statistics.setMin(amount);
            if (amount.compareTo(this.statistics.getMax()) > 0)
                this.statistics.setMax(amount);

            this.statistics.setSum(this.statistics.getSum().add(amount));
            this.statistics.setCount(this.statistics.getCount() + 1);
            this.statistics.setAvg(this.statistics.getSum().divide(BigDecimal.valueOf(this.statistics.getCount()), RoundingMode.HALF_UP));
        }
    }

    public void updateStatistics(Statistics statistics) {
        BigDecimal min = this.statistics.getMin().setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal max = this.statistics.getMax().setScale(2, BigDecimal.ROUND_HALF_UP);

        if (statistics.getMin().compareTo(min) > 0)
            statistics.setMin(min);
        if (statistics.getMax().compareTo(max) < 0)
            statistics.setMax(max);

        statistics.setSum(statistics.getSum().add(this.statistics.getSum().setScale(2, BigDecimal.ROUND_HALF_UP)));
        statistics.setCount(statistics.getCount() + this.statistics.getCount());
        statistics.setAvg(statistics.getSum().divide(BigDecimal.valueOf(statistics.getCount()), BigDecimal.ROUND_HALF_UP));
    }

    public boolean isEmpty() {
        return this.statistics.getCount() == 0;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void reset() {
        this.statistics.reset();
        this.timestamp = 0;
    }
}
