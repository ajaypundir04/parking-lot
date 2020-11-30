package com.dkatalis.parkinglot.service;

import com.dkatalis.parkinglot.constant.VehicleSize;
import com.dkatalis.parkinglot.model.Car;
import com.dkatalis.parkinglot.model.ParkingSpot;
import com.dkatalis.parkinglot.service.impl.ParkingManagerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Set;
import java.util.TreeSet;


public class ParkingManagerTest {

    private ParkingManager parkingManager;

    @Before
    public void setUp() {
        ParkingStrategy mockedParkingStrategy = Mockito.mock(ParkingStrategy.class);
        BillCalculator mockedBillCalculator = Mockito.mock(BillCalculator.class);
        Mockito.when(mockedBillCalculator.calculateBill(ArgumentMatchers.anyFloat())).thenReturn(30);
        Mockito.when(mockedParkingStrategy.getSlotNumber(ArgumentMatchers.anyString())).thenReturn(1);
        Mockito.when(mockedParkingStrategy.parkVehicle(ArgumentMatchers.anyString())).thenReturn(1);
        Mockito.doNothing().when(mockedParkingStrategy).createParkingLot(ArgumentMatchers.anyInt());
        parkingManager = new ParkingManagerImpl(mockedParkingStrategy, mockedBillCalculator);
    }

    @Test
    public void parkTest() {
        Car car = new Car("1", "red", VehicleSize.MEDIUM);
        Assert.assertEquals(1, parkingManager.parkVehicle(car));
    }

    @Test
    public void unParkTest() {
        Car car = new Car("1", "red", VehicleSize.MEDIUM);
        ParkingSpot spot = new ParkingSpot(1, car);
        parkingManager.parkVehicle(car);
        Assert.assertEquals(30, parkingManager.unParkVehicle(spot, 4));
    }

    @Test
    public void parkLotStatusTest() {
        Car car = new Car("1", "red", VehicleSize.MEDIUM);
        Assert.assertEquals(1, parkingManager.parkVehicle(car));
        Set<ParkingSpot> actualParkingSpot = parkingManager.parkingLotStatus();
        Set<ParkingSpot> expectedParkingSpot = new TreeSet<>();
        expectedParkingSpot.add(new ParkingSpot(1, car));
        Assert.assertNotNull(actualParkingSpot);
        Assert.assertEquals(expectedParkingSpot, actualParkingSpot);
    }

}
