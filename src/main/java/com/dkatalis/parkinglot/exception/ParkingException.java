package com.dkatalis.parkinglot.exception;

/**
 * @author Ajay Singh Pundir
 * It will handle all the business level exception
 */
public class ParkingException extends RuntimeException {

    public ParkingException(String message) {
        super(message);
    }

    public ParkingException(String message, Throwable cause) {
        super(message, cause);
    }
}
