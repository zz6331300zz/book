package com.sparta.book.exception;

public class WrongLoanException extends RuntimeException{
    public WrongLoanException(String message) {
        super(message);
    }
}
