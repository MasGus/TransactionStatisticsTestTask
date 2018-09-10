package com.n26.controller.exception;

public class TimestampFormatException extends RuntimeException {
    public TimestampFormatException() {
        super();
    }

    public TimestampFormatException(String message) {
        super(message);
    }
}
