package com.n26.controller.exception;

public class TimestampFutureException extends RuntimeException {
    public TimestampFutureException() {
        super();
    }

    public TimestampFutureException(String message) {
        super(message);
    }
}
