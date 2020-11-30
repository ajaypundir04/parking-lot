package com.dkatalis.parkinglot.service;

import com.dkatalis.parkinglot.constant.VehicleSize;
import com.dkatalis.parkinglot.model.Car;
import com.dkatalis.parkinglot.model.ParkingSpot;
import com.dkatalis.parkinglot.model.Vehicle;
import com.dkatalis.parkinglot.service.ParkingManager;
import com.dkatalis.parkinglot.service.ParkingService;
import com.dkatalis.parkinglot.service.impl.ParkingServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Set;
import java.util.TreeSet;

public class ParkingServiceTest {

    private ParkingService parkingService;

    @Before
    public void setUp() {
        ParkingManager mockedParkingManager = Mockito.mock(ParkingManager.class);
        parkingService = new ParkingServiceImpl(mockedParkingManager);
        Mockito.when(mockedParkingManager.getSlotNumber(ArgumentMatchers.anyString())).thenReturn(1);
        Set<ParkingSpot> mockedSpot = new TreeSet<>();
        mockedSpot.add(new ParkingSpot(1, new Car("1", "red", VehicleSize.MEDIUM)));
        Mockito.when(mockedParkingManager.parkingLotStatus()).thenReturn(mockedSpot);
        Mockito.doNothing().when(mockedParkingManager).createParkingLot(ArgumentMatchers.anyInt());
        Mockito.when(mockedParkingManager.parkVehicle(ArgumentMatchers.any(Vehicle.class))).thenReturn(1);
        Mockito.when(mockedParkingManager.unParkVehicle(ArgumentMatchers.any(ParkingSpot.class), ArgumentMatchers.anyFloat())).thenReturn(30);
    }

    @Test
    public void parkTest() {
        Car car = new Car("1", "red", VehicleSize.MEDIUM);
        Assert.assertEquals(1, parkingService.parkVehicle(car));
    }

    @Test
    public void unParkTest() {
        Car car = new Car("1", "red", VehicleSize.MEDIUM);
        ParkingSpot spot = new ParkingSpot(1, car);
        parkingService.parkVehicle(car);
        Assert.assertEquals(30, parkingService.unParkVehicle("1", 4));
    }

    @Test
    public void parkLotStatusTest() {
        Car car = new Car("1", "red", VehicleSize.MEDIUM);
        Assert.assertEquals(1, parkingService.parkVehicle(car));
        Set<ParkingSpot> actualParkingSpot = parkingService.parkingLotStatus();
        Set<ParkingSpot> expectedParkingSpot = new TreeSet<>();
        expectedParkingSpot.add(new ParkingSpot(1, car));
        Assert.assertNotNull(actualParkingSpot);
        Assert.assertEquals(expectedParkingSpot, actualParkingSpot);
    }

}
