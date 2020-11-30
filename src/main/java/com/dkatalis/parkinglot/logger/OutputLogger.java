package com.dkatalis.parkinglot.logger;

import com.dkatalis.parkinglot.model.ParkingSpot;

import java.util.Set;

/**
 * @author Ajay Singh Pundir
 *
 * It will handle the output of the parking operation
 */
public interface OutputLogger {

    void printParkingLotCreation(int capacity);

    void printParkResult(int slotNumber);

    void printUnParkResult(String numberPlate, int slotNumber, int billAmount);

    void printParkingLotStatus(Set<ParkingSpot> parkingSpots);

}
