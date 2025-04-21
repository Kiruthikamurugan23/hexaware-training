package com.hexaware.cars.exception;

@SuppressWarnings("serial")
public class AgencyNotFoundException extends Exception {
    public AgencyNotFoundException(String message) {
        super(message);
    }
}