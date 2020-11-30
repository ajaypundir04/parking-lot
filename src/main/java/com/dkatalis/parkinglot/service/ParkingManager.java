package com.dkatalis.parkinglot.service;

import com.dkatalis.parkinglot.model.ParkingSpot;
import com.dkatalis.parkinglot.model.Vehicle;

import java.util.Set;

/**
 * @author Ajay Singh Pundir
 *
 * It will manages all the parking operations.
 */
public interface ParkingManager {

    void createParkingLot(int capacity);

    int parkVehicle(Vehicle vehicle);

    int unParkVehicle(ParkingSpot parkingSpot, float timeInHours);

    Set<ParkingSpot> parkingLotStatus();

    int getSlotNumber(String numberPlate);

}
