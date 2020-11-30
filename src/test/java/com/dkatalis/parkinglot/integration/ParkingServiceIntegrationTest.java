package com.dkatalis.parkinglot.integration;

import com.dkatalis.parkinglot.constant.VehicleSize;
import com.dkatalis.parkinglot.model.Car;
import com.dkatalis.parkinglot.service.impl.BillCalculatorImpl;
import com.dkatalis.parkinglot.service.impl.NearestSlotFirstStrategy;
import com.dkatalis.parkinglot.service.impl.ParkingManagerImpl;
import com.dkatalis.parkinglot.service.impl.ParkingServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class ParkingServiceIntegrationTest {

    ParkingServiceImpl parkingService;
    ExecutorService request = Executors.newFixedThreadPool(4);

    @Before
    public void setUp() {
        parkingService = new ParkingServiceImpl(
                new ParkingManagerImpl(
                        new NearestSlotFirstStrategy(), new BillCalculatorImpl()
                )
        );
        parkingService.createParkingLot(4);
    }

    @Test
    public void multipleParkRequest() {
        List<Integer> parkedCars = new CopyOnWriteArrayList<>();
        Arrays.asList(
                new Car("1", "red", VehicleSize.MEDIUM),
                new Car("2", "red", VehicleSize.MEDIUM),
                new Car("3", "red", VehicleSize.MEDIUM),
                new Car("4", "red", VehicleSize.MEDIUM),
                new Car("5", "red", VehicleSize.MEDIUM),
                new Car("6", "red", VehicleSize.MEDIUM)
        ).forEach(c -> request.submit(() -> parkedCars.add(parkingService.parkVehicle(c))));
        request.shutdown();

        while (!request.isTerminated()) {
        }
        Collections.sort(parkedCars);
        assertEquals(Arrays.asList(-1, -1, 0, 1, 2, 3), parkedCars);
    }

    @Test
    public void multipleUnParkRequest() {
        parkingService = new ParkingServiceImpl(
                new ParkingManagerImpl(
                        new NearestSlotFirstStrategy(), new BillCalculatorImpl()
                )
        );
        List<Integer> parkedCars = new CopyOnWriteArrayList<>();
        parkingService.createParkingLot(4);

        Arrays.asList(
                new Car("1", "red", VehicleSize.MEDIUM),
                new Car("2", "red", VehicleSize.MEDIUM),
                new Car("3", "red", VehicleSize.MEDIUM),
                new Car("6", "red", VehicleSize.MEDIUM)
        ).forEach(c -> request.submit(() -> parkedCars.add(parkingService.parkVehicle(c))));
        request.shutdown();

        while (!request.isTerminated()) {
        }
        Collections.sort(parkedCars);
        assertEquals(Arrays.asList(0, 1, 2, 3), parkedCars);
        int billAmount = parkingService.unParkVehicle("1", 4);
        assertEquals(billAmount, 30);
    }
}