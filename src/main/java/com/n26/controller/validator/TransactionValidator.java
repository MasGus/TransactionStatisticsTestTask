package com.n26.controller.validator;

import com.n26.controller.exception.TimestampFormatException;
import com.n26.controller.exception.TimestampFutureException;
import com.n26.controller.exception.TimestampPastException;
import com.n26.entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@Component
public class TransactionValidator implements Validator {

    private static final int VALID_PAST_TIME_INTERVAL = 60000;

    @Override
    public boolean supports(Class clazz) {
        return Transaction.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        Transaction transaction = (Transaction) obj;
        long timestamp;
        long currentTimestamp = Instant.now().toEpochMilli();

        try {
            timestamp = Instant.parse(transaction.getTimestamp()).toEpochMilli();
        } catch (DateTimeParseException ex) {
            throw new TimestampFormatException();
        }

        if (timestamp < currentTimestamp - VALID_PAST_TIME_INTERVAL)
            throw new TimestampPastException();
        else if (timestamp > currentTimestamp)
            throw new TimestampFutureException();
    }

}
