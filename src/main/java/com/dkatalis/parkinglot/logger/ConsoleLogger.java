package com.dkatalis.parkinglot.logger;

import com.dkatalis.parkinglot.model.ParkingSpot;

import java.util.Set;

/**
 * @author Ajay Singh Pundir
 * Used to handle the logging of output to console.
 */
public class ConsoleLogger implements OutputLogger {

    /**
     * Used to print the result of create_parking_lot command.
     *
     * @param capacity Parking lot created with this capacity.
     */
    @Override
    public void printParkingLotCreation(int capacity) {
        System.out.println(String.format("Created parking lot with %s slots", capacity));
    }

    /**
     * Used to print the output of the park operation.
     *
     * @param slotNumber assigned to vehicle
     */
    @Override
    public void printParkResult(int slotNumber) {
        if (slotNumber != -1) {
            System.out.println(String.format("Allocated slot number: %s", slotNumber + 1));
        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }

    /**
     * Used to print the output of the leave command.
     *
     * @param numberPlate registration number of vehicle
     * @param slotNumber  parking slot number assigned to the vehicle
     * @param billAmount  bill charged for the parking
     */
    @Override
    public void printUnParkResult(String numberPlate, int slotNumber, int billAmount) {
        if (slotNumber != -1 || billAmount != -1) {
            System.out.println(
                    String.format("Registration number %s with Slot Number %s is free with Charge %s",
                            numberPlate, slotNumber + 1, billAmount));
        } else {
            System.out.println(String.format("Registration number %s not found", numberPlate));
        }

    }

    /**
     * Used to print the parking lot.
     *
     * @param parkingSpots all the spots present at parking lot
     */
    @Override
    public void printParkingLotStatus(Set<ParkingSpot> parkingSpots) {
        System.out.println("Slot No. Registration No.");
        parkingSpots.forEach(parkingSpot ->
                System.out.println(String.format("%s    %s",
                        parkingSpot.getSlotNumber() + 1, parkingSpot.getVehicle().getNumberPlate())));
    }
}
