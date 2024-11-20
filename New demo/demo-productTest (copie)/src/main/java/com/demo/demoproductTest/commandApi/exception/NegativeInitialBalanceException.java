package com.demo.demoproductTest.commandApi.exception;

public class NegativeInitialBalanceException extends RuntimeException{
    public NegativeInitialBalanceException(String message) {
        super(message);
    }
}
