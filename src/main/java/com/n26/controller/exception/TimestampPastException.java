package com.n26.controller.exception;

public class TimestampPastException extends RuntimeException {
    public TimestampPastException() {
        super();
    }

    public TimestampPastException(String message) {
        super(message);
    }
}
