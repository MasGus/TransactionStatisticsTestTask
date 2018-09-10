package com.n26.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.n26.controller.serializer.TransactionStatisticsSerializer;

import java.math.BigDecimal;
import java.math.RoundingMode;

@JsonSerialize(using = TransactionStatisticsSerializer.class)
public class Statistics {

    @JsonProperty
    private BigDecimal sum;

    @JsonProperty
    private BigDecimal avg;

    @JsonProperty
    private BigDecimal max;

    @JsonProperty
    private BigDecimal min;

    @JsonProperty
    private long count;

    public Statistics() {
        reset();
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void reset() {
        this.sum = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        this.avg = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        this.max = BigDecimal.valueOf(Long.MIN_VALUE).setScale(2, RoundingMode.HALF_UP);
        this.min = BigDecimal.valueOf(Long.MAX_VALUE).setScale(2, RoundingMode.HALF_UP);
        this.count = 0;
    }

    public void clear() {
        this.sum = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        this.avg = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        this.max = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        this.min = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        this.count = 0;
    }
}
