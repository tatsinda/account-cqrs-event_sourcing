package com.demo.AccountAxon.commandApi.exception;

public class NegativeInitialBalanceException extends RuntimeException{
    public NegativeInitialBalanceException(String message) {
        super(message);
    }
}
