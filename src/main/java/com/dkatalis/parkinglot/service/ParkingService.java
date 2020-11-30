package com.dkatalis.parkinglot.service;

import com.dkatalis.parkinglot.exception.ParkingException;
import com.dkatalis.parkinglot.model.ParkingSpot;
import com.dkatalis.parkinglot.model.Vehicle;

import java.util.Set;

/**
 * @author Ajay Singh Pundir
 *
 * It will handle all the parking operation.
 */
public interface ParkingService {

    void createParkingLot(int capacity);

    int parkVehicle(Vehicle vehicle) throws ParkingException;

    int unParkVehicle(String numberPlate, float timeInHours);

    Set<ParkingSpot> parkingLotStatus();

    int getSlotNumber(String numberPlate);

}
