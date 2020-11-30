package com.dkatalis.parkinglot.service;

/**
 * @author Ajay Singh Pundir
 *
 * It will used to decide the strategy for the parking lot assignment.
 */
public interface ParkingStrategy {

    void createParkingLot(int initialCapacity);

    int parkVehicle(String numberPlate);

    void unParkVehicle(int slotNumber);

    int getSlotNumber(String numberPlate);

}
