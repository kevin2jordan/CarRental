package com.lld.carrental.exceptions;

public class AccountDoesNotExistsException extends Exception {
    public AccountDoesNotExistsException(String message) {
        super(message);
    }
}
