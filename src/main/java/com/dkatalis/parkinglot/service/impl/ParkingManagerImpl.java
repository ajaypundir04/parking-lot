package com.dkatalis.parkinglot.service.impl;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.model.ParkingSpot;
import com.dkatalis.parkinglot.model.Vehicle;
import com.dkatalis.parkinglot.service.BillCalculator;
import com.dkatalis.parkinglot.service.ParkingManager;
import com.dkatalis.parkinglot.service.ParkingStrategy;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Ajay Singh Pundir
 * It will be used to manage all the parking lot operation.
 */
public class ParkingManagerImpl implements ParkingManager {
    private ParkingStrategy parkingStrategy;
    private BillCalculator billCalculator;
    private Set<ParkingSpot> parkingLot;

    public ParkingManagerImpl(ParkingStrategy parkingStrategy, BillCalculator billCalculator) {
        this.parkingStrategy = parkingStrategy;
        this.billCalculator = billCalculator;
        this.parkingLot = new TreeSet<>();
    }

    /**
     * It will be used to create the parking lot with the initial capacity.
     *
     * @param capacity number of vehicles that can be parked.
     */
    @Override
    public void createParkingLot(int capacity) {
        parkingStrategy.createParkingLot(capacity);

    }

    /**
     * It will used to paerform parking operation
     *
     * @param vehicle we wnt to park at the parking lot.
     * @return slot number assigned to the vehicle.
     */
    @Override
    public int parkVehicle(Vehicle vehicle) {
        int slotNumber = parkingStrategy.parkVehicle(vehicle.getNumberPlate());
        if (slotNumber != ApplicationConstants.INVALID_SLOT) {
            ParkingSpot spot = new ParkingSpot(slotNumber, vehicle);
            parkingLot.add(spot);
        }
        return slotNumber;
    }

    /**
     * It is used to un park the vehicle
     *
     * @param spot        @{@link ParkingSpot} spot of the vehicle to be un-parked.
     * @param timeInHours how long the vehicle parked in the parking lot
     * @return bill charged for parking.
     */
    @Override
    public int unParkVehicle(ParkingSpot spot, float timeInHours) {
        parkingStrategy.unParkVehicle(spot.getSlotNumber());
        parkingLot.remove(spot);
        return billCalculator.calculateBill(timeInHours);
    }

    @Override
    public Set<ParkingSpot> parkingLotStatus() {
        return parkingLot;
    }

    @Override
    public int getSlotNumber(String numberPlate) {
        return parkingStrategy.getSlotNumber(numberPlate);
    }

}
