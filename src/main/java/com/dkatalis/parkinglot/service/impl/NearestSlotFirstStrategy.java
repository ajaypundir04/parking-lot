package com.dkatalis.parkinglot.service.impl;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.exception.ParkingException;
import com.dkatalis.parkinglot.service.ParkingStrategy;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author Ajay Singh Pundir
 * It will used to assign the slot to a vehicle accoding to proximity with the entry gate.
 */
public class NearestSlotFirstStrategy implements ParkingStrategy {

    private static boolean parkingLotCreated = false;
    private String[] parkingSpots;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private AtomicInteger slotCounter;

    @Override
    public void createParkingLot(int initialCapacity) {
        parkingSpots = new String[initialCapacity];
        slotCounter = new AtomicInteger(initialCapacity);
        parkingLotCreated = true;

    }

    /**
     * It will be used to park a vehicle and assign a slot.
     *
     * @param numberPlate takes the vrhilce registration number as input.
     * @return the slot number assigned.
     */
    @Override
    public int parkVehicle(String numberPlate) {
        int index = ApplicationConstants.INVALID_SLOT;
        try {
            reentrantLock.lock();
            checkIfParkingLotCreated();
            if (slotCounter.get() > 0) {
                index = IntStream
                        .range(0, parkingSpots.length)
                        .filter(i -> Objects.isNull(parkingSpots[i]))
                        .findFirst().orElse(ApplicationConstants.INVALID_SLOT);
                if (index >= 0) {
                    parkingSpots[index] = numberPlate;
                    slotCounter.getAndDecrement();
                }
            }
        } finally {
            reentrantLock.unlock();
        }
        return index;
    }

    /**
     * It will be used to un-park the vehicle on the basis of slot number.
     *
     * @param slotNumber we need to pass the slotNumber assigned to vehicle.
     */
    @Override
    public void unParkVehicle(int slotNumber) {
        try {
            reentrantLock.lock();
            checkIfParkingLotCreated();
            parkingSpots[slotNumber] = null;
            slotCounter.getAndIncrement();
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * This method fetches the slot number assigned to vehicle
     *
     * @param numberPlate registration number of the vehicle
     * @return slot number assigned to vehicle.
     */
    @Override
    public int getSlotNumber(String numberPlate) {
        checkIfParkingLotCreated();
        return IntStream
                .range(0, parkingSpots.length)
                .filter(i -> numberPlate.equalsIgnoreCase(parkingSpots[i]))
                .findFirst()
                .orElse(ApplicationConstants.INVALID_SLOT);
    }

    private void checkIfParkingLotCreated() {
        if (!parkingLotCreated)
            throw new ParkingException("Parking Slot is not Created");

    }
}
