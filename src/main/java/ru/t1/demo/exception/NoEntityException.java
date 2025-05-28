package ru.t1.demo.exception;

public class NoEntityException extends RuntimeException {
    public NoEntityException(String message) {
        super(message);
    }
}
