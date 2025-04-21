package com.hexaware.cars.exception;

@SuppressWarnings("serial")
public class VictimNotFoundException extends Exception {
    public VictimNotFoundException(String message) {
        super(message);
    }
}