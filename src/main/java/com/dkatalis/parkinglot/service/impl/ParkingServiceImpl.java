package com.dkatalis.parkinglot.service.impl;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.model.ParkingSpot;
import com.dkatalis.parkinglot.model.Vehicle;
import com.dkatalis.parkinglot.service.ParkingManager;
import com.dkatalis.parkinglot.service.ParkingService;

import java.util.Optional;
import java.util.Set;

/**
 * @author Ajay Singh Pundir
 * It will handle all the parking lot level operation
 */
public class ParkingServiceImpl implements ParkingService {

    private ParkingManager parkingManager;

    public ParkingServiceImpl(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
    }

    /**
     * This method is used to create the parking lot
     *
     * @param capacity initial capacity for the parking lot to be created.
     */
    @Override
    public void createParkingLot(int capacity) {
        parkingManager.createParkingLot(capacity);

    }

    /**
     * It will be used to park a vehicle.
     *
     * @param vehicle vehicle to be parked
     * @return slot number assigned to the vehicle
     */
    @Override
    public int parkVehicle(Vehicle vehicle) {
        return parkingManager.parkVehicle(vehicle);
    }

    /**
     * It will be used to un-park the vehicle.
     *
     * @param numberPlate registration number of the vehicle
     * @param timeInHours number of hours spent in the parking lot
     * @return bill charged for the parking
     */
    @Override
    public int unParkVehicle(String numberPlate, float timeInHours) {
        Optional<ParkingSpot> parkingSpotOptional = parkingManager.parkingLotStatus()
                .stream()
                .filter(parkingSpot -> numberPlate.equalsIgnoreCase(parkingSpot.getVehicle().getNumberPlate()))
                .findFirst();
        if (parkingSpotOptional.isPresent()) {
            return parkingManager.unParkVehicle(parkingSpotOptional.get(), timeInHours);
        } else
            return ApplicationConstants.INVALID_SLOT;
    }

    /**
     * It is used to display the status of the parking lot.
     *
     * @return parking lot
     */
    @Override
    public Set<ParkingSpot> parkingLotStatus() {
        return parkingManager.parkingLotStatus();
    }

    /**
     * It will be used to get the slot number on the basis of vehicle registration number.
     *
     * @param numberPlate registration number of vehicle
     * @return the slot number of the vehicle
     */
    @Override
    public int getSlotNumber(String numberPlate) {
        return parkingManager.getSlotNumber(numberPlate);
    }
}
